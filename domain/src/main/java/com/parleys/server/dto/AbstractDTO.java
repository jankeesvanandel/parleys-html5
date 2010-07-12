package com.parleys.server.dto;

import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Stephan Janssen
 */
public class AbstractDTO extends BaseObject {

    /**
     *
     */
    private static final long serialVersionUID = -8568707356029112380L;

    protected Long id;

    private Date createdOn;

    private Date modifiedOn;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
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
    public boolean isNew () {
    	return (id == null) || (id <= 0);
    }
}
