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

import com.parleys.server.domain.types.VoteType;

/**
 * @author Stephan Janssen
 */
public class VoteDTO extends AbstractDTO {

    private static final long serialVersionUID = 7513460844503696497L;

    private VoteType value;

    private SimpleUserDTO user;

    /**
     * @return the value
     */
    public final VoteType getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public final void setValue(final VoteType value) {
        this.value = value;
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
