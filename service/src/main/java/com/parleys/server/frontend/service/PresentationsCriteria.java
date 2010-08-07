package com.parleys.server.frontend.service;

/**
 * Holder class for parameters to query presentations lists.
 *
 * @author Jan-Kees van Andel
 */
public class PresentationsCriteria {

    private Long channelId;

    private int index = 0;

    private int paging = 20;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(final Long channelId) {
        this.channelId = channelId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(final int index) {
        this.index = index;
    }

    public int getPaging() {
        return paging;
    }

    public void setPaging(final int paging) {
        this.paging = paging;
    }
}
