package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.frontend.domain.Filter;

/**
 * @author Stephan Janssen
 */
public class PagingBean extends AbstractParleysBean {

    private Filter filter;

    private Filter.Type filterType;

    private Integer index;

    private Integer paging;

    public Integer getIndex() {
        if (index != null) {
            index = Math.max(index, 1000);
        } else {
            index = 0;
        }
        return index;
    }

    public void setIndex(final Integer index) {
        this.index = index;
    }

    public Integer getPaging() {
        if (paging != null) {
            paging = Math.max(paging, 100);
        } else {
            paging = 20;
        }
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
