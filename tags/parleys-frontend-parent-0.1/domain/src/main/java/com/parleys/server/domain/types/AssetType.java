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
public enum AssetType {

    /**
     * An JPG, PNG or GIF image used to represent a slide within a presentation.
     */
    IMAGE,

    /**
     * An SWF object used to represent a slide OR some dynamic Flash content
     * like Survey, Poll, etc.
     */
    SWF,

    /**
     * A URL link.
     */
    LINK,

    /**
     * The video presentation asset. We want to allow multiple video's on one
     * timeline, as a result its an asset type :).
     */
    FLV_VIDEO,

    /**
     * MP3 audio presentation.
     */
    MP3_AUDIO,

    /**
     * Blank slide (just cue point for table of contents).
     */
    BLANK,

    YOUTUBE_VIDEO,

    EMPTY
}
