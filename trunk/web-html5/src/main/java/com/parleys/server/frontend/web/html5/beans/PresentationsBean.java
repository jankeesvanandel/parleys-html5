package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.dto.ChannelOverviewDTO;
import com.parleys.server.dto.FilteredOverviewResponseDTO;
import com.parleys.server.dto.PresentationOverviewDTO;
import com.parleys.server.frontend.domain.Filter;
import com.parleys.server.frontend.service.PresentationsCriteria;
import com.parleys.server.security.AuthorizationException;
import com.parleys.server.service.exception.ParleysServiceException;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Backing bean for the space detail page.
 */
@ManagedBean
@RequestScoped
public class PresentationsBean extends AbstractParleysBean {

    private final transient Log LOG = LogFactory.getLog(getClass());
    
    private long channelId;

    private Filter filter;

    private Filter.Type filterType;

    private ChannelOverviewDTO channel;

    private FilteredOverviewResponseDTO<PresentationOverviewDTO> presentations;

    private Integer index;
    private Integer paging;

    public void init() {
        if (index != null) {
            index = Math.max(index, 1000);
        } else {
            index = 0;
        }

        if (paging != null) {
            paging = Math.max(paging, 100);
        } else {
            paging = 20;
        }

        final PresentationsCriteria criteria = new PresentationsCriteria();
        criteria.setChannelId(channelId);
        criteria.setFilter(filter);
        criteria.setIndex(index);
        criteria.setPaging(paging);

        try {
            presentations = getParleysServiceDelegate().getPresentationsOverview(criteria);
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

    public void setChannel(final ChannelOverviewDTO channel) {
        this.channel = channel;
    }

    public FilteredOverviewResponseDTO<PresentationOverviewDTO> getPresentations() {
        return presentations;
    }

    public void setPresentations(final FilteredOverviewResponseDTO<PresentationOverviewDTO> presentations) {
        this.presentations = presentations;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(final Integer index) {
        this.index = index;
    }

    public Integer getPaging() {
        return paging;
    }

    public void setPaging(final Integer paging) {
        this.paging = paging;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(final Filter filter) {
        this.filter = filter;
    }

    public Filter.Type getFilterType() {
        return filterType;
    }

    public void setFilterType(final Filter.Type filterType) {
        this.filterType = filterType;
    }
}
