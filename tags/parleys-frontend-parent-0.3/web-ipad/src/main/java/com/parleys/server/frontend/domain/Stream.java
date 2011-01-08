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
 * A simple object representing a video stream.
 *
 * @author Jan-Kees van Andel
 */
public class Stream {

    private final String url;
    private final double duration;

    public Stream(String url, double duration) {
        this.url = url;
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public double getDuration() {
        return duration;
    }
}
