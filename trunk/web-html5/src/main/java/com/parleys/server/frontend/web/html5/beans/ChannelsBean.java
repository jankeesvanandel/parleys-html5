package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.dto.ChannelOverviewDTO;
import com.parleys.server.dto.FilteredOverviewResponseDTO;
import com.parleys.server.frontend.domain.Channel;
import com.parleys.server.frontend.domain.Space;
import com.parleys.server.frontend.service.ParleysServiceDelegate;
import com.parleys.server.security.AuthorizationException;
import com.parleys.server.service.ParleysService;
import com.parleys.server.service.exception.ParleysServiceException;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;

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

    private List<ChannelOverviewDTO> channels;

    public void init() throws AuthorizationException, ParleysServiceException, ClientStatusException {
        space = getParleysServiceDelegate().loadSpace(spaceId);

        channels = getParleysServiceDelegate().getChannelsOverview(spaceId).getOverviews();

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

    public List<ChannelOverviewDTO> getChannels() {
        return channels;
    }

    public void setChannels(final List<ChannelOverviewDTO> channels) {
        this.channels = channels;
    }
}
