/*
 * Copyright 2018 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.acme.spring.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class GreeterBean {

    private final MessageProducer messageProducer;
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    @Qualifier("noopFunction")
    StringFunction noopStringFunction;

    @Autowired
    @Qualifier("capitalizeFunction")
    StringFunction capitalizerStringFunction;

    @Value("${greeting.suffix:!}")
    String suffix;

    public GreeterBean(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
        System.out.println("Java Max Memory: " + Runtime.getRuntime().maxMemory());
        System.out.println("Java Total Memory: " + Runtime.getRuntime().totalMemory());
    }

    public Greeting greet(String name) {
        final String initialValue = messageProducer.getPrefix() + " " + name + suffix;
        return new Greeting(counter.getAndIncrement(), noopStringFunction.andThen(capitalizerStringFunction).apply(initialValue));
    }
}
