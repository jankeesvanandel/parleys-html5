package com.parleys.server.frontend.service;

public class PresentationsCriteria {

    private long channelId;

    private int index = 0;

    private int paging = 20;

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(final long channelId) {
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
