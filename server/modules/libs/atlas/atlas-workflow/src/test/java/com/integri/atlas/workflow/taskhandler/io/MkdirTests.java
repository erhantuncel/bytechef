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

package com.integri.atlas.workflow.taskhandler.io;

import com.integri.atlas.workflow.core.task.SimpleTaskExecution;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MkdirTests {

    @Test
    public void test1() throws IOException {
        Mkdir mkdir = new Mkdir();
        SimpleTaskExecution task = new SimpleTaskExecution();
        String tempDir = System.getProperty("java.io.tmpdir") + "/" + RandomStringUtils.randomAlphabetic(10);
        task.set("path", tempDir);
        mkdir.handle(task);
        Assertions.assertTrue(new File(tempDir).exists());
    }

    @Test
    public void test2() throws IOException {
        Assertions.assertThrows(
            FileSystemException.class,
            () -> {
                Mkdir mkdir = new Mkdir();
                SimpleTaskExecution task = new SimpleTaskExecution();
                task.set("path", "/no/such/thing");
                mkdir.handle(task);
            }
        );
    }
}
