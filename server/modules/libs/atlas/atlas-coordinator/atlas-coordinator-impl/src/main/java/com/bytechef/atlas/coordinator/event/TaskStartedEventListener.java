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

package com.bytechef.atlas.coordinator.event;

import com.bytechef.atlas.event.EventListener;
import com.bytechef.atlas.event.Events;
import com.bytechef.atlas.event.WorkflowEvent;
import com.bytechef.atlas.job.JobStatus;
import com.bytechef.atlas.job.domain.Job;
import com.bytechef.atlas.service.job.JobService;
import com.bytechef.atlas.service.task.execution.TaskExecutionService;
import com.bytechef.atlas.task.CancelTask;
import com.bytechef.atlas.task.dispatcher.TaskDispatcher;
import com.bytechef.atlas.task.execution.TaskStatus;
import com.bytechef.atlas.task.execution.domain.SimpleTaskExecution;
import com.bytechef.atlas.task.execution.domain.TaskExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Arik Cohen
 * @since Apt 9, 2017
 */
public class TaskStartedEventListener implements EventListener {

    private final TaskExecutionService taskExecutionService;
    private final TaskDispatcher taskDispatcher;
    private final JobService jobService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public TaskStartedEventListener(
            TaskExecutionService taskExecutionService, TaskDispatcher taskDispatcher, JobService jobService) {
        this.taskExecutionService = taskExecutionService;
        this.taskDispatcher = taskDispatcher;
        this.jobService = jobService;
    }

    @Override
    public void onApplicationEvent(WorkflowEvent workflowEvent) {
        if (Events.TASK_STARTED.equals(workflowEvent.getType())) {
            String taskId = workflowEvent.getString("taskId");
            TaskExecution task = taskExecutionService.getTaskExecution(taskId);
            if (task == null) {
                logger.error("Unkown task: {}", taskId);
                return;
            }

            Job job = jobService.getTaskExecutionJob(taskId);

            if (task.getStatus() == TaskStatus.CANCELLED || job.getStatus() != JobStatus.STARTED) {
                taskDispatcher.dispatch(new CancelTask(task.getJobId(), task.getId()));
            } else {
                SimpleTaskExecution mtask = SimpleTaskExecution.of(task);
                if (mtask.getStartTime() == null && mtask.getStatus() != TaskStatus.STARTED) {
                    mtask.setStartTime(workflowEvent.getCreateTime());
                    mtask.setStatus(TaskStatus.STARTED);
                    taskExecutionService.merge(mtask);
                }
                if (mtask.getParentId() != null) {
                    WorkflowEvent pevent = WorkflowEvent.of(Events.TASK_STARTED, "taskId", mtask.getParentId());
                    onApplicationEvent(pevent);
                }
            }
        }
    }
}
