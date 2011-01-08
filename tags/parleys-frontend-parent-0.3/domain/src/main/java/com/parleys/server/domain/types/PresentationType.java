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
public enum PresentationType {

    /**
     * Audio only talks.
     */
    AUDIO_AND_SLIDES,

    /**
     * Majority of the Parleys talks.
     */
    VIDEO_AND_SLIDES,

    /**
     * Not yet used, will be used in version 1 of
     * the public Community presentations.
     */
    SLIDES_ONLY,

    /**
     * Video only talks = interviews etc.
     */
    VIDEO_ONLY,

    /**
     * This is not a talk but an advertisement which
     * might have an external link.
     */
    ADVERTISEMENT,

    /**
     * This type is for filtering only.
     */
    ALL
}
