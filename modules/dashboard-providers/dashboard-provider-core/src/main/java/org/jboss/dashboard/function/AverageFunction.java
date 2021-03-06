/**
 * Copyright (C) 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.dashboard.function;

import org.jboss.dashboard.profiler.CodeBlockTrace;

import java.util.Collection;

import org.jboss.dashboard.annotation.Install;

/**
 * It calculates the average value of a set of numbers.
 */
@Install
public class AverageFunction extends SumFunction {

    /**
     * The code of the function.
     */
    public static final String CODE = "average";
    
    public AverageFunction() {
        super();
    }

    public String getCode() {
        return CODE;
    }

    public double scalar(Collection values) {
        double ret;
        CodeBlockTrace trace = new ScalarFunctionTrace(CODE, values).begin();
        try {
            if (values == null || values.isEmpty()) return 0;
            double average = super.scalar(values) / values.size();
            ret = round(average, precission);
        } finally {
            trace.end();
        }
        // Adjust to the specified precission.
        return ret;
    }

}