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

import com.parleys.server.domain.SocialInfo;

/**
 * @author Stephan Janssen
 */
public class TagDetailsDTO extends AbstractDTO {

    private static final long serialVersionUID = 4350993889746781001L;

    private String name;

    private SimpleUserDTO user;

    private SocialInfo socialInfo = new SocialInfo();

    private Integer cuePoint;

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public final void setName(final String name) {
        this.name = name;
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

    /**
     * @return the socialInfo
     */
    public final SocialInfo getSocialInfo() {
        return socialInfo;
    }

    /**
     * @param socialInfo the socialInfo to set
     */
    public final void setSocialInfo(final SocialInfo socialInfo) {
        this.socialInfo = socialInfo;
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
}
