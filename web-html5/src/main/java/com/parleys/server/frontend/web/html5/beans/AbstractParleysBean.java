package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.dto.*;
import com.parleys.server.frontend.domain.Filter;
import com.parleys.server.frontend.service.ParleysServiceDelegate;
import com.parleys.server.security.AuthorizationException;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.bean.ManagedProperty;

/**
 * Bean that holds process information.
 */
public abstract class AbstractParleysBean {

    private final transient Log LOG = LogFactory.getLog(getClass());

    private boolean isOnHomepage = false;

    private SpaceOverviewDTO currentSpace;

    private ChannelOverviewDTO currentChannel;

    private ExtendedPresentationDetailsDTO currentPresentation;

    @ManagedProperty("#{parleysService}")
    private ParleysServiceDelegate parleysServiceDelegate;

    public ParleysServiceDelegate getParleysServiceDelegate() {
       return parleysServiceDelegate;
    }

    public void setParleysServiceDelegate(final ParleysServiceDelegate parleysServiceDelegate) {
        this.parleysServiceDelegate = parleysServiceDelegate;
    }

    public final void initializeHomepage() {
        isOnHomepage = true;
    }

    public final void initializeSpace(final SpaceOverviewDTO space) {
        currentSpace = space;
    }

    public final void initializeChannel(final ChannelOverviewDTO channel) {
        currentChannel = channel;
        initializeSpace(findSpaceForChannel(channel));
    }

    public final void initializePresentation(final ExtendedPresentationDetailsDTO presentation) {
        currentPresentation = presentation;
        initializeChannel(findChannelForPresentation(presentation));
    }

    private SpaceOverviewDTO findSpaceForChannel(final ChannelOverviewDTO channel) {
        try {
            return getParleysServiceDelegate().getSpaceOverviewDTO(channel.getSpaceId());
        } catch (AuthorizationException e) {
            LOG.error(e);
        } catch (ClientStatusException e) {
            LOG.error(e);
        }
        throw new IllegalArgumentException("Space does not exist");
    }

    private ChannelOverviewDTO findChannelForPresentation(final ExtendedPresentationDetailsDTO presentation) {
        return presentation.getChannel();
    }

    public SpaceOverviewDTO getCurrentSpace() {
        return currentSpace;
    }

    public ChannelOverviewDTO getCurrentChannel() {
        return currentChannel;
    }

    public ExtendedPresentationDetailsDTO getCurrentPresentation() {
        return currentPresentation;
    }

    public boolean getIsOnHomepage() {
        return isOnHomepage;
    }           
}
