package com.parleys.server.frontend.service;

import com.parleys.server.frontend.domain.Filter;

public class PresentationsCriteria {
    private long channelId;
    private int index = 0;
    private int paging = 20;
    private Filter filter;

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPaging() {
        return paging;
    }

    public void setPaging(int paging) {
        this.paging = paging;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
