
/*
 * Copyright 2016-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Modifications copyright (C) 2021 <your company/name>
 */

package com.bytechef.atlas.coordinator;

import com.bytechef.atlas.execution.domain.Job;
import com.bytechef.atlas.execution.dto.JobParameters;
import com.bytechef.atlas.configuration.repository.WorkflowCrudRepository;
import com.bytechef.atlas.configuration.repository.WorkflowRepository;
import com.bytechef.atlas.execution.repository.jdbc.JdbcContextRepository;
import com.bytechef.atlas.execution.repository.jdbc.JdbcJobRepository;
import com.bytechef.atlas.execution.repository.jdbc.JdbcTaskExecutionRepository;
import com.bytechef.atlas.execution.converter.ExecutionErrorToStringConverter;
import com.bytechef.atlas.execution.converter.StringToExecutionErrorConverter;
import com.bytechef.atlas.execution.converter.StringToWebhooksConverter;
import com.bytechef.atlas.configuration.converter.StringToWorkflowTaskConverter;
import com.bytechef.atlas.execution.converter.WebhooksToStringConverter;
import com.bytechef.atlas.configuration.converter.WorkflowTaskToStringConverter;
import com.bytechef.atlas.configuration.repository.resource.config.ResourceWorkflowRepositoryConfiguration;
import com.bytechef.atlas.execution.service.ContextService;
import com.bytechef.atlas.execution.service.ContextServiceImpl;
import com.bytechef.atlas.execution.service.JobService;
import com.bytechef.atlas.execution.service.JobServiceImpl;
import com.bytechef.atlas.execution.service.TaskExecutionService;
import com.bytechef.atlas.execution.service.TaskExecutionServiceImpl;
import com.bytechef.atlas.configuration.service.WorkflowService;
import com.bytechef.configuration.service.WorkflowServiceImpl;
import com.bytechef.atlas.sync.executor.JobSyncExecutor;
import com.bytechef.atlas.worker.task.handler.TaskHandler;
import com.bytechef.commons.data.jdbc.converter.MapWrapperToStringConverter;
import com.bytechef.commons.data.jdbc.converter.StringToMapWrapperConverter;
import com.bytechef.test.annotation.EmbeddedSql;
import com.bytechef.test.config.jdbc.AbstractIntTestJdbcConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Arik Cohen
 * @author Ivica Cardic
 */
@EmbeddedSql
@SpringBootTest(
    properties = {
        "bytechef.context-repository.provider=jdbc",
        "bytechef.persistence.provider=jdbc",
        "bytechef.workflow-repository.classpath.enabled=true"
    })
public class TaskCoordinatorIntTest {

    @Autowired
    private ContextService contextService;

    @Autowired
    private JobService jobService;

    @Autowired
    private TaskExecutionService taskExecutionService;

    @Autowired
    private WorkflowService workflowService;

    @Test
    public void testPerformWorkflowJson() {
        Job completedJob = executeWorkflow("aGVsbG8x");

        Assertions.assertEquals(Job.Status.COMPLETED, completedJob.getStatus());
    }

    @Test
    public void testPerformWorkflowYaml() {
        Job completedJob = executeWorkflow("aGVsbG8y");

        Assertions.assertEquals(Job.Status.COMPLETED, completedJob.getStatus());
    }

    private Job executeWorkflow(String workflowId) {
        Map<String, TaskHandler<?>> taskHandlerMap = new HashMap<>();

        taskHandlerMap.put("randomHelper/v1/randomInt", taskExecution -> null);

        JobSyncExecutor jobSyncExecutor = JobSyncExecutor.builder()
            .contextService(contextService)
            .eventPublisher(e -> {})
            .jobService(jobService)
            .taskExecutionService(taskExecutionService)
            .taskHandlerRegistry(taskHandlerMap::get)
            .workflowService(workflowService)
            .build();

        return jobSyncExecutor.execute(new JobParameters(workflowId, Collections.singletonMap("yourName", "me")));
    }

    @ComponentScan("com.bytechef.liquibase.config")
    @EmbeddedSql
    @EnableAutoConfiguration
    @Import({
        ResourceWorkflowRepositoryConfiguration.class
    })
    @Configuration
    public static class CoordinatorIntTestConfiguration {

        @Bean
        ContextService contextService(JdbcContextRepository jdbcContextRepository) {
            return new ContextServiceImpl(jdbcContextRepository);
        }

        @Bean
        JobService jobService(JdbcJobRepository jdbcJobRepository) {
            return new JobServiceImpl(jdbcJobRepository);
        }

        @Bean
        TaskExecutionService taskExecutionService(JdbcTaskExecutionRepository jdbcTaskExecutionRepository) {
            return new TaskExecutionServiceImpl(jdbcTaskExecutionRepository);
        }

        @Bean
        WorkflowService workflowService(
            List<WorkflowCrudRepository> workflowCrudRepositories, List<WorkflowRepository> workflowRepositories) {

            return new WorkflowServiceImpl(
                new ConcurrentMapCacheManager(), workflowCrudRepositories, workflowRepositories);
        }

        @EnableJdbcRepositories(basePackages = {
            "com.bytechef.atlas.configuration.repository.jdbc", "com.bytechef.atlas.execution.repository.jdbc"
        })
        public static class CoordinatorIntTestJdbcConfiguration extends AbstractIntTestJdbcConfiguration {
        }

        @Configuration
        public static class JdbcConfiguration extends AbstractJdbcConfiguration {

            private final ObjectMapper objectMapper;

            @SuppressFBWarnings("EI2")
            public JdbcConfiguration(ObjectMapper objectMapper) {
                this.objectMapper = objectMapper;
            }

            @Override
            protected List<?> userConverters() {
                return Arrays.asList(
                    new ExecutionErrorToStringConverter(objectMapper),
                    new MapWrapperToStringConverter(objectMapper),
                    new StringToExecutionErrorConverter(objectMapper),
                    new StringToExecutionErrorConverter(objectMapper),
                    new StringToMapWrapperConverter(objectMapper),
                    new StringToWebhooksConverter(objectMapper),
                    new StringToWorkflowTaskConverter(objectMapper),
                    new WebhooksToStringConverter(objectMapper),
                    new WorkflowTaskToStringConverter(objectMapper));
            }
        }
    }
}
