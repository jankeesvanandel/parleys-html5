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
package com.parleys.server.domain;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This news item is linked to a space and holds free text,
 * time based and can include links.
 *
 * @author Stephan Janssen
 */
public class News extends AbstractEntity {

    private static final long serialVersionUID = 7829932764561222800L;

    private String title;

    private String item;

    private boolean isGeneral = false;

    private boolean isPublished = false;

    // The author name of the news item
    private String author;

    @JsonProperty
    public final String getItem() {
        return item;
    }

    public final void setItem(final String value) {
        item = value;
    }

    @JsonProperty
    public final boolean isPublished() {
        return isPublished;
    }

    public final void setPublished(final boolean published) {
        isPublished = published;
    }

    @JsonProperty
    public final String getAuthor() {
        return author;
    }

    public final void setAuthor(final String value) {
        author = value;
    }

    @JsonProperty
    public final String getTitle() {
        return title;
    }

    public final void setTitle(final String value) {
        title = value;
    }

    @JsonProperty
    public boolean isGeneral() {
        return isGeneral;
    }

    public void setGeneral(final boolean general) {
        isGeneral = general;
    }
}
