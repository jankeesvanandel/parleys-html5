package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.frontend.domain.Channel;
import com.parleys.server.frontend.domain.Space;
import com.parleys.server.frontend.service.ParleysServiceDelegate;
import com.parleys.server.service.ParleysService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for the space detail page.
 */
@ManagedBean
@RequestScoped
public class ChannelsBean extends AbstractParleysBean {

    private long spaceId;

    private Space space;

    private List<Channel> channels;

    public void init() {
        space = getParleysServiceDelegate().loadSpace(spaceId);
        channels = getParleysServiceDelegate().loadChannels(spaceId);

        super.initializeSpace(space);
    }

    public long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(final long spaceId) {
        this.spaceId = spaceId;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(final Space space) {
        this.space = space;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(final List<Channel> channels) {
        this.channels = channels;
    }
}
