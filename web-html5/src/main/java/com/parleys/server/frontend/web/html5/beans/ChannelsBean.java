package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.dto.ChannelOverviewDTO;
import com.parleys.server.dto.SpaceOverviewDTO;
import com.parleys.server.security.AuthorizationException;
import com.parleys.server.service.exception.ParleysServiceException;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for the space detail page.
 *
 * @author Jan-Kees Vanandel
 * @author Stephan Janssen
 */
@ManagedBean @RequestScoped
public class ChannelsBean extends AbstractParleysBean {

    private final transient Log LOG = LogFactory.getLog(getClass());

    private long spaceId;

    private SpaceOverviewDTO space;

    private List<ChannelOverviewDTO> channels;

    public void init() {
        try {
            space = getParleysServiceDelegate().getSpaceOverviewDTO(spaceId);

            channels = getParleysServiceDelegate().getChannelsOverview(spaceId).getOverviews();

            super.initializeSpace(space);

        } catch (AuthorizationException e) {
            LOG.error(e);
        } catch (ClientStatusException e) {
            LOG.error(e);
        } catch (ParleysServiceException e) {
            LOG.error(e);
        }
    }

    public long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(final long spaceId) {
        this.spaceId = spaceId;
    }

    public SpaceOverviewDTO getSpace() {
        return space;
    }

    public List<ChannelOverviewDTO> getChannels() {
        return channels;
    }
}
