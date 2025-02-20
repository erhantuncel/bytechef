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

package com.bytechef.platform.configuration.instance.accessor;

import com.bytechef.platform.constant.ModeType;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.Validate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author Ivica Cardic
 */
@Component
public class PrincipalAccessorRegistry {

    private final Map<ModeType, PrincipalAccessor> principalAccessorMap;

    public PrincipalAccessorRegistry(List<PrincipalAccessor> principalAccessors) {
        this.principalAccessorMap = principalAccessors
            .stream()
            .collect(
                Collectors.toMap(
                    PrincipalAccessor::getType, instanceWorkflowAccessor -> instanceWorkflowAccessor));
    }

    public PrincipalAccessor getPrincipalAccessor(@NonNull ModeType type) {
        return Validate.notNull(principalAccessorMap.get(type), "principalAccessor");
    }
}
