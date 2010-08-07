package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.dto.ChannelOverviewDTO;
import com.parleys.server.frontend.domain.Filter;
import com.parleys.server.frontend.web.html5.util.JSFUtil;
import com.parleys.server.security.AuthorizationException;
import com.parleys.server.service.exception.ParleysServiceException;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for the channels overview page.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean @RequestScoped
public class ChannelsBean extends AbstractParleysBean implements Paginable {

    private static final Logger LOGGER = Logger.getLogger(ChannelsBean.class);

    @ManagedProperty("#{channelsViewBean}")
    private ChannelsViewBean channelsViewBean;

    public void init() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        try {
            super.initializeSpace(getParleysServiceDelegate().getSpaceOverviewDTO(channelsViewBean.getSpaceId()));
        } catch (Exception e) {
            LOGGER.error(e);
        }

        gotoPage(getPagingBean().getFilter(), getPagingBean().getIndex(), getPagingBean().getPaging());
    }

    /** {@inheritDoc} */
    @Override
    public void gotoPage(Filter filter, int index, int paging) {
        getPagingBean().setFilter(filter);
        getPagingBean().setIndex(index);
        getPagingBean().setPaging(paging);

        try {
            List<ChannelOverviewDTO> channels = loadChannels();
            getPagingBean().setPaginatedList(channels);
        } catch (AuthorizationException e) {
            LOGGER.error(e);
        } catch (ClientStatusException e) {
            LOGGER.error(e);
        } catch (ParleysServiceException e) {
            LOGGER.error(e);
        }
    }

    private List<ChannelOverviewDTO> loadChannels() throws ParleysServiceException, AuthorizationException, ClientStatusException {
        return getParleysServiceDelegate().getChannelsOverview(channelsViewBean.getSpaceId()).getOverviews();
    }

    public ChannelsViewBean getChannelsViewBean() {
        return channelsViewBean;
    }

    public void setChannelsViewBean(ChannelsViewBean channelsViewBean) {
        this.channelsViewBean = channelsViewBean;
    }
}
