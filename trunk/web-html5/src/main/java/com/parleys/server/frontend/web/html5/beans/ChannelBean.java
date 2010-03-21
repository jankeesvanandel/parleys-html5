package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.frontend.domain.Channel;
import com.parleys.server.frontend.domain.Presentation;
import com.parleys.server.frontend.service.ParleysService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for the space detail page.
 */
@ManagedBean
@RequestScoped
public class ChannelBean {

    private long channelId;

    @ManagedProperty("#{parleysService}")
    private ParleysService parleysService;

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

        channel = parleysService.loadChannel(channelId);
        presentations = parleysService.loadPresentations(channelId, index, paging);
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public ParleysService getParleysService() {
        return parleysService;
    }

    public void setParleysService(ParleysService parleysService) {
        this.parleysService = parleysService;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<Presentation> getPresentations() {
        return presentations;
    }

    public void setPresentations(List<Presentation> presentations) {
        this.presentations = presentations;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getPaging() {
        return paging;
    }

    public void setPaging(Integer paging) {
        this.paging = paging;
    }
}