
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

package com.bytechef.component.webhook.trigger;

import com.bytechef.hermes.component.definition.ComponentDSL.ModifiableTriggerDefinition;
import com.bytechef.hermes.component.definition.OutputSchemaDataSource;
import com.bytechef.hermes.component.definition.TriggerDefinition;
import com.bytechef.hermes.component.definition.TriggerDefinition.StaticWebhookRequestContext;
import com.bytechef.hermes.component.definition.TriggerDefinition.TriggerType;
import com.bytechef.hermes.component.definition.TriggerDefinition.WebhookValidateContext;
import com.bytechef.hermes.component.util.MapUtils;

import java.util.Map;
import java.util.Objects;

import static com.bytechef.component.webhook.constant.WebhookConstants.BODY;
import static com.bytechef.component.webhook.constant.WebhookConstants.CSRF_TOKEN;
import static com.bytechef.component.webhook.constant.WebhookConstants.HEADERS;
import static com.bytechef.component.webhook.constant.WebhookConstants.METHOD;
import static com.bytechef.component.webhook.constant.WebhookConstants.PARAMETERS;
import static com.bytechef.component.webhook.constant.WebhookConstants.X_CRSF_TOKEN;
import static com.bytechef.hermes.component.definition.ComponentDSL.trigger;

import static com.bytechef.hermes.definition.DefinitionDSL.any;
import static com.bytechef.hermes.definition.DefinitionDSL.integer;
import static com.bytechef.hermes.definition.DefinitionDSL.object;
import static com.bytechef.hermes.definition.DefinitionDSL.string;

/**
 * @author Ivica Cardic
 */
public class WebhookAwaitWorkflowAndRespondTrigger {

    public static final ModifiableTriggerDefinition TRIGGER_DEFINITION = trigger("awaitWorkflowAndRespond")
        .title("Await workflow and respond")
        .description(
            "You have the flexibility to set up your preferred response. After a webhook request is received, the webhook trigger enters a waiting state for the workflow's response.")
        .type(TriggerType.STATIC_WEBHOOK)
        .workflowSyncExecution(true)
        .properties(
            string(CSRF_TOKEN)
                .label("CSRF Token")
                .description(
                    "To trigger the workflow successfully, the security token must match the X-Csrf-Token HTTP header value passed by the client."),
            integer("timeout")
                .label("Timeout (ms)")
                .description(
                    "The incoming request will time out after the specified number of milliseconds. The max wait time before a timeout is 5 minutes."))
        .outputSchema(getOutputSchemaFunction())
        .staticWebhookRequest(WebhookAwaitWorkflowAndRespondTrigger::staticWebhookRequest)
        .webhookValidate(WebhookAwaitWorkflowAndRespondTrigger::webhookValidate);

    protected static TriggerDefinition.WebhookOutput staticWebhookRequest(StaticWebhookRequestContext context) {
        TriggerDefinition.WebhookBody webhookBody = context.body();

        return TriggerDefinition.WebhookOutput.map(
            Map.of(
                BODY, webhookBody.content(),
                METHOD, context.method(),
                HEADERS, context.headers(),
                PARAMETERS, context.parameters()));
    }

    protected static boolean webhookValidate(WebhookValidateContext context) {
        return Objects.equals(
            context.headers()
                .firstValue(X_CRSF_TOKEN)
                .orElseThrow(),
            MapUtils.getString(context.inputParameters(), CSRF_TOKEN));
    }

    protected static OutputSchemaDataSource.OutputSchemaFunction getOutputSchemaFunction() {
        // TODO
        return (connection, inputParameters) -> object()
            .properties(
                string(METHOD),
                object(HEADERS),
                object(PARAMETERS),
                any(BODY));
    }
}
