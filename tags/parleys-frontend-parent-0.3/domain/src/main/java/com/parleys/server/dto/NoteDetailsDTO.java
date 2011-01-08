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
package com.parleys.server.dto;

/**
 * @author Stephan Janssen
 */
public class NoteDetailsDTO extends AbstractDTO {

    private static final long serialVersionUID = -8059773735770985857L;

    private String text;

    private Integer cuePoint;

    private boolean isPublicNote;

    private SimpleUserDTO user;

    /**
     * @return the text
     */
    public final String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public final void setText(final String text) {
        this.text = text;
    }

    /**
     * @return the cuePoint
     */
    public final Integer getCuePoint() {
        return cuePoint;
    }

    /**
     * @param cuePoint the cuePoint to set
     */
    public final void setCuePoint(final Integer cuePoint) {
        this.cuePoint = cuePoint;
    }

    /**
     * @return the isPublicNote
     */
    public final boolean isPublicNote() {
        return isPublicNote;
    }

    /**
     * @param isPublicNote the isPublicNote to set
     */
    public final void setPublicNote(final boolean isPublicNote) {
        this.isPublicNote = isPublicNote;
    }

    /**
     * @return the user
     */
    public final SimpleUserDTO getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public final void setUser(final SimpleUserDTO user) {
        this.user = user;
    }
}
