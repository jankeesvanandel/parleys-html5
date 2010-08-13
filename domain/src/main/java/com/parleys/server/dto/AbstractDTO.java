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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

/**
 * @author Stephan Janssen
 */
public class AbstractDTO extends BaseObject {

    private static final long serialVersionUID = -8568707356029112380L;

    private Long id;

    private Date createdOn;

    private Date modifiedOn;

    public final Long getId() {
        return id;
    }

    public final void setId(final Long id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        if (createdOn != null) {
            return (Date) createdOn.clone();
        } else {
            return null;
        }
    }

    public void setCreatedOn(final Date createdOn) {
        if (createdOn != null) {
            this.createdOn = (Date) createdOn.clone();
        }
    }

    public Date getModifiedOn() {
        if (modifiedOn != null) {
            return (Date) modifiedOn.clone();
        } else {
            return null;
        }
    }

    public void setModifiedOn(final Date modifiedOn) {
        if (modifiedOn != null) {
            this.modifiedOn = (Date) modifiedOn.clone();
        }
    }

    /* (non-Javadoc)
      * @see com.parleys.server.domain.BaseObject#equals(java.lang.Object)
      */

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractDTO that = (AbstractDTO) o;

        return id.equals(that.id);
    }

    /* (non-Javadoc)
      * @see com.parleys.server.domain.BaseObject#hashCode()
      */

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return 0;
        }
    }

    /* (non-Javadoc)
      * @see com.parleys.server.domain.BaseObject#toString()
      */

    @Override
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this,
                ToStringStyle.DEFAULT_STYLE).append("id", this.id);
        return sb.toString();
    }

    /**
     * Checks is it new entity or existing one.
     *
     * @return Checking result.
     */
    public boolean isNew() {
        return (id == null) || (id <= 0);
    }
}
