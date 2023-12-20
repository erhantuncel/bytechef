/*
 * Copyright 2023-present ByteChef Inc.
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

package com.bytechef.helios.coordinator;

import com.bytechef.commons.util.MapUtils;
import com.bytechef.commons.util.OptionalUtils;
import com.bytechef.helios.configuration.service.ProjectInstanceWorkflowService;
import com.bytechef.hermes.configuration.domain.WorkflowConnection;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public abstract class AbstractDispatcherPreSendProcessor {

    private final ProjectInstanceWorkflowService projectInstanceWorkflowService;

    protected AbstractDispatcherPreSendProcessor(ProjectInstanceWorkflowService projectInstanceWorkflowService) {
        this.projectInstanceWorkflowService = projectInstanceWorkflowService;
    }

    protected Map<String, Long> getConnectionIdMap(
        Long projectInstanceId, String workflowId, List<WorkflowConnection> workflowConnections) {

        return MapUtils.toMap(
            workflowConnections, WorkflowConnection::getKey,
            workflowConnection -> getConnectionId(projectInstanceId, workflowId, workflowConnection));
    }

    private Long getConnectionId(Long projectInstanceId, String workflowId, WorkflowConnection workflowConnection) {
        Long connectionId = null;

        if (projectInstanceId != null) {
            connectionId = projectInstanceWorkflowService.getProjectInstanceWorkflowConnectionId(
                projectInstanceId, workflowId, workflowConnection.getOperationName(), workflowConnection.getKey());
        }

        if (connectionId == null) {
            connectionId = OptionalUtils.orElse(workflowConnection.getId(), null);
        }

        return connectionId;
    }
}
