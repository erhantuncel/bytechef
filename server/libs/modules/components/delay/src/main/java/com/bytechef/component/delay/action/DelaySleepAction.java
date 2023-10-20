
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

package com.bytechef.component.delay.action;

import com.bytechef.hermes.component.definition.Context;
import com.bytechef.hermes.component.definition.ComponentDSL.ModifiableActionDefinition;
import com.bytechef.hermes.component.exception.ComponentExecutionException;
import com.bytechef.hermes.component.util.MapUtils;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.bytechef.component.delay.constant.DelayConstants.MILLIS;
import static com.bytechef.component.delay.constant.DelayConstants.SLEEP;
import static com.bytechef.hermes.component.definition.ComponentDSL.action;

import static com.bytechef.hermes.definition.DefinitionDSL.integer;

/**
 * @author Ivica Cardic
 */
public class DelaySleepAction {

    public static final ModifiableActionDefinition ACTION_DEFINITION = action(SLEEP)
        .title("Sleep")
        .description("Delay action execution.")
        .properties(integer(MILLIS)
            .label("Millis")
            .description("Time in milliseconds.")
            .required(true)
            .defaultValue(1))
        .perform(DelaySleepAction::perform);

    protected static Object perform(Map<String, ?> inputParameters, Context context) {
        if (inputParameters.containsKey(MILLIS)) {
            sleep(MapUtils.getLong(inputParameters, MILLIS));
        } else if (inputParameters.containsKey("duration")) {
            Duration duration = MapUtils.getDuration(inputParameters, "duration");

            sleep(duration.toMillis());
        } else {
            sleep(1000);
        }

        return null;
    }

    protected static void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException interruptedException) {
            throw new ComponentExecutionException("Unable to handle delay action", interruptedException);
        }
    }
}
