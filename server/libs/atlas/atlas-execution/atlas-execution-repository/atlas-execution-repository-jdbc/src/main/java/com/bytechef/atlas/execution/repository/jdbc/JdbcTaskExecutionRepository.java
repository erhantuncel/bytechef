
/*
 * Copyright 2021 <your company/name>.
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
 */

package com.bytechef.atlas.execution.repository.jdbc;

import com.bytechef.atlas.execution.domain.TaskExecution;
import com.bytechef.atlas.execution.repository.TaskExecutionRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Ivica Cardic
 */
@Repository
public interface JdbcTaskExecutionRepository
    extends ListPagingAndSortingRepository<TaskExecution, Long>, ListCrudRepository<TaskExecution, Long>,
    TaskExecutionRepository {

    @Override
    List<TaskExecution> findAllByJobIdOrderByCreatedDate(Long jobId);

    @Override
    List<TaskExecution> findAllByJobIdInOrderByCreatedDate(List<Long> jobIds);

    @Override
    List<TaskExecution> findAllByJobIdOrderByTaskNumber(Long jobId);

    @Override
    List<TaskExecution> findAllByParentId(Long parentId);

    @Override
    @Query("SELECT * FROM task_execution WHERE id = :id FOR UPDATE")
    Optional<TaskExecution> findByIdForUpdate(@Param("id") long id);

    TaskExecution save(TaskExecution taskExecution);
}
