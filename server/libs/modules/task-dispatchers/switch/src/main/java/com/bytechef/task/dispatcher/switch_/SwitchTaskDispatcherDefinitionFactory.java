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

package com.bytechef.task.dispatcher.switch_;

import static com.bytechef.hermes.task.dispatcher.TaskDispatcherDSL.create;
import static com.bytechef.hermes.task.dispatcher.TaskDispatcherDSL.display;
import static com.bytechef.hermes.task.dispatcher.TaskDispatcherDSL.string;
import static com.bytechef.task.dispatcher.switch_.constants.SwitchTaskDispatcherConstants.EXPRESSION;
import static com.bytechef.task.dispatcher.switch_.constants.SwitchTaskDispatcherConstants.SWITCH;

import com.bytechef.hermes.task.dispatcher.TaskDispatcherDefinitionFactory;
import com.bytechef.hermes.task.dispatcher.definition.TaskDispatcherDefinition;
import org.springframework.stereotype.Component;

/**
 * @author Ivica Cardic
 */
@Component
public class SwitchTaskDispatcherDefinitionFactory implements TaskDispatcherDefinitionFactory {

    private static final TaskDispatcherDefinition TASK_DISPATCHER_DEFINITION = create(SWITCH)
            .display(display("Switch")
                    .description("Executes one and only one branch of execution based on the `expression` value."))
            .display(display("Branch"))
            .inputs(string(EXPRESSION)
                    .label("Expression")
                    .description("Defines expression upon which evaluation the proper branch continues execution."));

    @Override
    public TaskDispatcherDefinition getDefinition() {
        return TASK_DISPATCHER_DEFINITION;
    }
}
