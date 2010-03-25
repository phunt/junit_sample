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

import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/**
 * Log each test method call/return. Here we are doing system.out.println
 * but could easily be substituted with log4j log calls.
 */
public class LoggedRunner extends BlockJUnit4ClassRunner {
    public LoggedRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    private static class MyInvokeMethod extends InvokeMethod {
        private String name;

        public MyInvokeMethod(FrameworkMethod testMethod, Object target) {
            super(testMethod, target);
            name = testMethod.getName();
        }

        @Override
        public void evaluate() throws Throwable {
            System.out.println("*RUNNING " + name);
            try {
                super.evaluate();
            } finally {
                System.out.println("*FINISHED " + name);
            }
        }
    }

    @Override
    protected Statement methodInvoker(FrameworkMethod method, Object test) {
        return new MyInvokeMethod(method, test);
    }
}
