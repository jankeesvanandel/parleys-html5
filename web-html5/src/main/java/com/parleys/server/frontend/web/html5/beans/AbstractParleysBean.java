package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.dto.ChannelOverviewDTO;
import com.parleys.server.dto.ExtendedPresentationDetailsDTO;
import com.parleys.server.dto.SpaceOverviewDTO;
import com.parleys.server.frontend.service.ParleysServiceDelegate;
import com.parleys.server.security.AuthorizationException;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedProperty;

/**
 * Generic base class with functionality required on all pages.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
public abstract class AbstractParleysBean {

    private static final Logger LOGGER = Logger.getLogger(AbstractParleysBean.class);

    private boolean isOnHomepage = false;

    private SpaceOverviewDTO currentSpace;

    private ChannelOverviewDTO currentChannel;

    private ExtendedPresentationDetailsDTO currentPresentation;

    @ManagedProperty("#{pagingBean}")
    private PagingBean pagingBean;

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
            LOGGER.error(e);
        } catch (ClientStatusException e) {
            LOGGER.error(e);
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

    public PagingBean getPagingBean() {
        return pagingBean;
    }

    public void setPagingBean(PagingBean pagingBean) {
        this.pagingBean = pagingBean;
    }
}
