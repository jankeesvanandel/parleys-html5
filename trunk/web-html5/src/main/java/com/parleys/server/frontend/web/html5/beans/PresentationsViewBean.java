package com.parleys.server.frontend.web.html5.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * View scoped bean for the presentations overview page, holds cross-request state.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean @ViewScoped
public class PresentationsViewBean implements Serializable {

    private static final long serialVersionUID = 2510734974116519755L;

    private Long channelId;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }
}
