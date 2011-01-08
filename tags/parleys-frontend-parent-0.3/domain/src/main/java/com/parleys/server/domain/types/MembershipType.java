/*
 * Copyright (C) 2010 Parleys.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.parleys.server.domain.types;

/**
 * @author Stephan Janssen
 */
public enum MembershipType {

    // Default
    FREE(0),

    PAY_PER_TALK(1),

    SUBSCRIPTION(2),

    PAY_PER_TALK_AND_SUBSCRIPTION(3);

    private int value;

    MembershipType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
