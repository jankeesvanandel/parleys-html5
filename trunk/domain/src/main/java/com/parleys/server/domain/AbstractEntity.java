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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

/**
 * The abstract entity used by all children Entity classes.
 *
 * @author Stephan Janssen
 */
public abstract class AbstractEntity extends BaseObject {

    private static final long serialVersionUID = -8678841958284556626L;

    private Long id;

    private Date createdOn;

    private Date modifiedOn;

    private Integer version;

    public final Long getId() {
        return id;
    }

    public final void setId(final Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }

    /**
     * Value cloned otherwise we may expose internal representation by returning reference to mutable object.
     *
     * @return clone of modification date
     * @link http://findbugs.sourceforge.net/bugDescriptions.html#EI_EXPOSE_REP
     */
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

    /**
     * Value cloned otherwise we may expose internal representation by returning reference to mutable object.
     *
     * @return clone of modification date
     * @link http://findbugs.sourceforge.net/bugDescriptions.html#EI_EXPOSE_REP
     */
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

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractEntity that = (AbstractEntity) o;

        return (id != null) && id.equals(that.id);
    }

    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return 0;
        }
    }

    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("id", this.id);
        return sb.toString();
    }
}
