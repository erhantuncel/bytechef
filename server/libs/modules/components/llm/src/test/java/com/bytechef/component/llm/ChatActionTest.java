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

package com.bytechef.component.llm;

import static com.bytechef.component.llm.constant.LLMConstants.MESSAGES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.bytechef.component.definition.Parameters;
import com.bytechef.component.definition.TypeReference;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;

/**
 * @author Marko Kriskovic
 */
public class ChatActionTest extends AbstractActionTest {

    private static final String ANSWER = "ANSWER";

    @Test
    public void testGetResponse() {
        when(mockedParameters.getList(eq(MESSAGES), any(TypeReference.class)))
            .thenReturn(List.of(new Chat.Message("QUESTION", null, "user")));

        Chat mockedChat = spy(new MockChat());
        ChatModel mockedChatModel = mock(ChatModel.class);

        when(mockedChat.createChatModel(mockedParameters, mockedParameters)).thenReturn(mockedChatModel);

        when(mockedChatModel.call(any(Prompt.class))).thenReturn(
            new ChatResponse(List.of(new Generation(new AssistantMessage(ANSWER)))));

        Object response = mockedChat.getResponse(mockedParameters, mockedParameters, mockedActionContext);

        assertEquals(ANSWER, response);
    }

    private static class MockChat implements Chat {

        @Override
        public ChatModel createChatModel(Parameters inputParameters, Parameters connectionParameters) {
            return null;
        }
    }
}
