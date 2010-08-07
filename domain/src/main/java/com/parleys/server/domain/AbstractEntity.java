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

    protected Long id;

    private Date createdOn;

    private Date modifiedOn;

    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
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
