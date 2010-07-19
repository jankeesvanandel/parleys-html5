package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.domain.types.FeaturedType;
import com.parleys.server.dto.ChannelOverviewDTO;
import com.parleys.server.dto.OverviewResponseDTO;
import com.parleys.server.dto.PresentationOverviewDTO;
import com.parleys.server.frontend.service.PresentationsCriteria;
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
@ManagedBean
@RequestScoped
public class PresentationsBean extends PagingBean {

    private final transient Log LOG = LogFactory.getLog(getClass());
    
    private long channelId;

    private ChannelOverviewDTO channel;

    private List<PresentationOverviewDTO> presentations;

    @SuppressWarnings("unchecked")
    public void init() {

        final PresentationsCriteria criteria = new PresentationsCriteria();
        criteria.setChannelId(getChannelId());
        criteria.setIndex(getIndex());
        criteria.setPaging(getPaging());

        try {
            if (getFilter() != null) {
                switch (getFilter()) {
                    case FEATURED:
                        presentations =
                                (List<PresentationOverviewDTO>)getParleysServiceDelegate()
                                        .getFeatured(FeaturedType.PRESENTATION);
                        break;

                    case LATEST:
                        presentations =
                                (List<PresentationOverviewDTO>)getParleysServiceDelegate()
                                        .getLatestPresentationsOverview(criteria);
                        break;
    
                    case TOP_RATED:
                        presentations =
                                (List<PresentationOverviewDTO>)getParleysServiceDelegate()
                                        .getTopRatedPresentationsOverview(criteria);
                        break;

                    // MOST_VIEWED
                    default:    
                        presentations =
                                (List<PresentationOverviewDTO>)getParleysServiceDelegate()
                                        .getMostViewedPresentationsOverview(criteria);
                }
            } else {
                presentations =
                        (List<PresentationOverviewDTO>)getParleysServiceDelegate()
                                .getPresentationsOverview(criteria);
            }

            if (channelId != 0) {
                    channel = getParleysServiceDelegate().getChannelOverviewDTO(channelId);
                super.initializeChannel(channel);
            } else {
                super.initializeHomepage();
            }
        } catch (ParleysServiceException e) {
            LOG.error(e);
        } catch (AuthorizationException e) {
            LOG.error(e);
        } catch (ClientStatusException e) {
            LOG.error(e);
        }
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(final long channelId) {
        this.channelId = channelId;
    }

    public ChannelOverviewDTO getChannel() {
        return channel;
    }

    public List<PresentationOverviewDTO> getPresentations() {
        return presentations;
    }
}
