package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.frontend.domain.Channel;
import com.parleys.server.frontend.domain.Filter;
import com.parleys.server.frontend.domain.Presentation;
import com.parleys.server.frontend.service.ParleysServiceDelegate;
import com.parleys.server.frontend.service.PresentationsCriteria;
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
public class PresentationsBean extends AbstractParleysBean {

    private long channelId;

    private Filter filter;

    private Filter.Type filterType;

    private Channel channel;

    private List<Presentation> presentations;

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
        presentations = getParleysServiceDelegate().loadPresentationsWithCriteria(criteria);

        if (channelId != 0) {
            channel = getParleysServiceDelegate().loadChannel(channelId);
            super.initializeChannel(channel);
        } else {
            super.initializeHomepage();
        }
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(final long channelId) {
        this.channelId = channelId;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(final Channel channel) {
        this.channel = channel;
    }

    public List<Presentation> getPresentations() {
        return presentations;
    }

    public void setPresentations(final List<Presentation> presentations) {
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
