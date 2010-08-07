package com.parleys.server.frontend.web.html5.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * View scoped bean for the channels overview page, holds cross-request state.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean @ViewScoped
public class ChannelsViewBean implements Serializable {

    private static final long serialVersionUID = -6799778239583321914L;

    private long spaceId;

    public long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(final long spaceId) {
        this.spaceId = spaceId;
    }
}
