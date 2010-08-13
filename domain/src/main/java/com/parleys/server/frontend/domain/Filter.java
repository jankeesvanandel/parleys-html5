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
package com.parleys.server.frontend.domain;

/**
 * @author Jan-Kees van Andel
 */
public enum Filter {

    FEATURED("Featured"),

    LATEST("Latest"),

    TOP_RATED("Top Rated"),

    MOST_VIEWED("Most Viewed");

    private final String description;

    Filter(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * The type of object the filter applies to.
     */
    public static enum Type {
        SPACE,
        CHANNEL,
        PRESENTATION
    }
}
