
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

package com.bytechef.hermes.component.registrar.oas.task.handler;

import com.bytechef.hermes.component.OpenApiComponentHandler;
import com.bytechef.hermes.component.definition.ActionDefinition;
import com.bytechef.hermes.component.definition.ConnectionDefinition;
import com.bytechef.hermes.component.registrar.task.handler.AbstractComponentTaskHandlerBeanDefinitionLoader;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * @author Ivica Cardic
 */
public class OpenApiComponentTaskHandlerBeanDefinitionLoader
    extends AbstractComponentTaskHandlerBeanDefinitionLoader<OpenApiComponentHandler> {

    public OpenApiComponentTaskHandlerBeanDefinitionLoader() {
        super(OpenApiComponentHandler.class);
    }

    @Override
    protected BeanDefinition getBeanDefinition(
        ActionDefinition actionDefinition, ConnectionDefinition connectionDefinition,
        OpenApiComponentHandler openApiComponentHandler) {

        return BeanDefinitionBuilder.genericBeanDefinition(OpenApiComponentTaskHandler.class)
            .addConstructorArgValue(actionDefinition)
            .addConstructorArgReference("connectionDefinitionService")
            .addConstructorArgReference("connectionService")
            .addConstructorArgReference("eventPublisher")
            .addConstructorArgReference("fileStorageService")
            .addConstructorArgValue(openApiComponentHandler)
            .getBeanDefinition();
    }
}
