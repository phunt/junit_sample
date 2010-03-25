/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.phunt.junit_sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runner.RunWith;
import org.junit.runners.model.FrameworkMethod;

/**
 * Log the start/end/success/failure of each test. This wraps the
 * after/test/before phases. Here we are doing system.out.println
 * but could easily be substituted with log4j log calls.
 */
@RunWith(LoggedRunner.class)
public class LoggedTest {
    public String testName;

    @Rule
    public MethodRule watchman = new TestWatchman() {
        @Override
        public void failed(Throwable e, FrameworkMethod method) {
            System.out.println("*failed " + testName + " " + e);
        }

        @Override
        public void finished(FrameworkMethod method) {
            System.out.println("*finished " + testName);
        }

        @Override
        public void starting(FrameworkMethod method) {
            testName = method.getName();
            System.out.println("*starting " + testName);
        }

        @Override
        public void succeeded(FrameworkMethod method) {
            System.out.println("*success " + testName);
        }

    };

    @Before
    public void setup() {
        System.out.println("setup " + testName);
    }
    @After
    public void teardown() {
        System.out.println("teardown " + testName);
    }
    @Test
    public void foo() {
        System.out.println("test: foo");
    }
    @Test
    public void fooFail() {
        System.out.println("test: fooFail");
        throw new RuntimeException();
    }
}
