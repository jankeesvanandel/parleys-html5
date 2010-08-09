package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.domain.types.FeaturedType;
import com.parleys.server.dto.ChannelOverviewDTO;
import com.parleys.server.dto.PresentationOverviewDTO;
import com.parleys.server.frontend.domain.Filter;
import com.parleys.server.frontend.service.PresentationsCriteria;
import com.parleys.server.frontend.web.html5.util.JSFUtil;
import com.parleys.server.security.AuthorizationException;
import com.parleys.server.service.exception.ParleysServiceException;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Backing bean for the space detail page.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean @RequestScoped
public class PresentationsBean extends AbstractParleysBean implements Paginable {

    private static final Logger LOGGER = Logger.getLogger(PresentationsBean.class);

    @ManagedProperty("#{presentationsViewBean}")
    private PresentationsViewBean presentationsViewBean;

    public void init() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        gotoPage(getPagingBean().getFilter(), getPagingBean().getIndex(), getPagingBean().getPaging());
    }

    /** {@inheritDoc} */
    @Override
    public void gotoPage(Filter filter, int index, int paging) {
        getPagingBean().setFilter(filter);
        getPagingBean().setIndex(index);
        getPagingBean().setPaging(paging);

        final List<PresentationOverviewDTO> presentations = loadPresentations(getPagingBean().getFilter());
        getPagingBean().setPaginatedList(presentations);

        final Long channelId = getPresentationsViewBean().getChannelId();
        if (channelId != null) {
            try {
                final ChannelOverviewDTO dto = getParleysServiceDelegate().getChannelOverviewDTO(channelId);
                super.initializeChannel(dto);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        } else {
            super.initializeHomepage();
        }
    }

    @SuppressWarnings("unchecked")
    private List<PresentationOverviewDTO> loadPresentations(Filter filter) {
        try {
            final PresentationsCriteria criteria = new PresentationsCriteria();
            criteria.setChannelId(getPresentationsViewBean().getChannelId());
            criteria.setIndex(0);
            criteria.setPaging(200);

            if (filter != null) {
                switch (filter) {
                    case FEATURED:
                        return (List<PresentationOverviewDTO>) getParleysServiceDelegate().getFeatured(FeaturedType.PRESENTATION);
                    case LATEST:
                        return(List<PresentationOverviewDTO>) getParleysServiceDelegate().getLatestPresentationsOverview(criteria);
                    case TOP_RATED:
                        return(List<PresentationOverviewDTO>) getParleysServiceDelegate().getTopRatedPresentationsOverview(criteria);
                    default: // If default, pick MOST_VIEWED
                        return (List<PresentationOverviewDTO>) getParleysServiceDelegate().getMostViewedPresentationsOverview(criteria);
                }
            } else {
                return (List<PresentationOverviewDTO>)getParleysServiceDelegate().getPresentationsOverview(criteria);
            }
        } catch (ParleysServiceException e) {
            LOGGER.error(e);
        } catch (AuthorizationException e) {
            LOGGER.error(e);
        } catch (ClientStatusException e) {
            LOGGER.error(e);
        }

        return new ArrayList<PresentationOverviewDTO>();
    }

    public PresentationsViewBean getPresentationsViewBean() {
        return presentationsViewBean;
    }

    public void setPresentationsViewBean(PresentationsViewBean presentationsViewBean) {
        this.presentationsViewBean = presentationsViewBean;
    }
}
