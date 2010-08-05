package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.frontend.domain.Filter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Stephan Janssen
 */
@ManagedBean @ViewScoped
public class PagingBean implements Serializable {

    private Filter filter;

    private Filter.Type filterType;

    private Integer index = 1;

    private Integer paging = 20;

    private Long totalCount;

    private List<?> list;

    public List<Integer> getPages() {
        final Long totalCount = getTotalCount();
        final Integer index = getIndex();
        int totalPages = (int) Math.ceil( ((double)totalCount) / ((double) paging) );
        List<Integer> ret = new ArrayList<Integer>(totalPages);
        for (int i = 0; i < totalPages; i++) {
            ret.add(i+1);
        }
        return ret;
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

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
