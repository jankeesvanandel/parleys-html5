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
public enum SpaceSort {

    // Sort on creation date ascending (default).
    CREATION_DATE,

    // Sort on creation date ascending.
    CREATION_DATE_DESC,

    // Sort on number of channels ascending.
    TOTAL_CHANNELS_ASC,

    // Sort on number of channels descending.
    TOTAL_CHANNELS_DESC,

    // Sort on total number of views asscending.
    TOTAL_VIEWS_ASC,

    // Sort on total number of views descending.
    TOTAL_VIEWS_DESC,

    // Sort on total number of talks ascending.
    TOTAL_TALKS_ASC,

    // Sort on total number of talks descending.
    TOTAL_TALKS_DESC
}
