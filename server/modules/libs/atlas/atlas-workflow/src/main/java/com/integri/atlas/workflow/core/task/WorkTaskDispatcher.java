/*
 * Copyright 2016-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Modifications copyright (C) 2021 <your company/name>
 */

package com.integri.atlas.workflow.core.task;

import com.integri.atlas.workflow.core.messagebroker.MessageBroker;
import com.integri.atlas.workflow.core.messagebroker.Queues;
import java.util.Objects;
import org.springframework.util.Assert;

public class WorkTaskDispatcher implements TaskDispatcher<TaskExecution>, TaskDispatcherResolver {

    private final MessageBroker messageBroker;

    private static final String DEFAULT_QUEUE = Queues.TASKS;

    public WorkTaskDispatcher(MessageBroker aMessageBroker) {
        messageBroker = Objects.requireNonNull(aMessageBroker);
    }

    @Override
    public void dispatch(TaskExecution aTask) {
        Assert.notNull(messageBroker, "message broker not configured");
        messageBroker.send(calculateRoutingKey(aTask), aTask);
    }

    private String calculateRoutingKey(Task aTask) {
        TaskExecution jtask = (TaskExecution) aTask;
        return jtask.getNode() != null ? jtask.getNode() : DEFAULT_QUEUE;
    }

    @Override
    public TaskDispatcher<TaskExecution> resolve(Task aTask) {
        if (aTask instanceof TaskExecution) {
            return this;
        }
        return null;
    }
}
