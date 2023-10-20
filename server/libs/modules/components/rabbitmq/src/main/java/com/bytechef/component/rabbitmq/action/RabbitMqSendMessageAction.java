
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

package com.bytechef.component.rabbitmq.action;

import com.bytechef.component.rabbitmq.util.RabbitMqUtils;
import com.bytechef.hermes.component.definition.ActionDefinition;
import com.bytechef.hermes.component.definition.Context.Connection;
import com.bytechef.hermes.component.definition.ComponentDSL.ModifiableActionDefinition;
import com.bytechef.hermes.component.definition.OutputSchemaDataSource.OutputSchemaFunction;
import com.bytechef.hermes.component.exception.ComponentExecutionException;
import com.bytechef.hermes.component.util.JsonUtils;
import com.bytechef.hermes.component.util.MapUtils;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.bytechef.component.rabbitmq.constant.RabbitMqConstants.HOSTNAME;
import static com.bytechef.component.rabbitmq.constant.RabbitMqConstants.MESSAGE;
import static com.bytechef.component.rabbitmq.constant.RabbitMqConstants.PASSWORD;
import static com.bytechef.component.rabbitmq.constant.RabbitMqConstants.PORT;
import static com.bytechef.component.rabbitmq.constant.RabbitMqConstants.QUEUE;
import static com.bytechef.component.rabbitmq.constant.RabbitMqConstants.USERNAME;
import static com.bytechef.hermes.component.definition.ComponentDSL.action;

import static com.bytechef.hermes.definition.DefinitionDSL.object;
import static com.bytechef.hermes.definition.DefinitionDSL.string;

/**
 * @author Ivica Cardic
 */
public class RabbitMqSendMessageAction {

    public static final ModifiableActionDefinition ACTION_DEFINITION = action("sendMessage")
        .title("Send Message")
        .description("Send a new RabbitMQ message.")
        .properties(
            string(QUEUE)
                .description("The name of the queue to read from")
                .required(true),
            object(MESSAGE)
                .description("The name of the queue to read from")
                .required(true))
        .outputSchema(getOutputSchemaFunction())
        .perform(RabbitMqSendMessageAction::perform);

    protected static Object perform(Map<String, ?> inputParameters, ActionDefinition.ActionContext context) {
        Connection connection = context.getConnection();

        try (com.rabbitmq.client.Connection rabbitMqConnection = RabbitMqUtils.getConnection(
            MapUtils.getString(connection.getParameters(), HOSTNAME),
            MapUtils.getInteger(connection.getParameters(), PORT, 5672),
            MapUtils.getString(connection.getParameters(), USERNAME),
            MapUtils.getString(connection.getParameters(), PASSWORD))) {

            Channel channel = rabbitMqConnection.createChannel();

            String queueName = MapUtils.getRequiredString(inputParameters, QUEUE);
            String message = JsonUtils.write(MapUtils.getRequired(inputParameters, MESSAGE));

            channel.queueDeclare(queueName, true, false, false, null);
            channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));

            channel.close();
        } catch (Exception e) {
            throw new ComponentExecutionException(e.getMessage(), e);
        }

        return null;
    }

    protected static OutputSchemaFunction getOutputSchemaFunction() {
        // TODO
        return (connection, inputParameters) -> null;
    }
}
