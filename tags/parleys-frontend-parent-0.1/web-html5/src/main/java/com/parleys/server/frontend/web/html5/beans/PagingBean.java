/*
 * Copyright (C) 2010 Parleys.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.frontend.domain.Filter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Generic bean which provides generic paging support for any list of values.
 * <p/>
 * This bean is view scoped so it remembers its state across requests within the same page.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean
@ViewScoped
public class PagingBean implements Serializable {

    private static final long serialVersionUID = -2125959936450149984L;

    private Filter filter;

    private Filter.Type filterType;

    private int index = 0;

    private int paging = 20;

    private int totalCount = 0;

    private List<?> list;

    public boolean getIsRendered() {
        return index > 0 || index + paging < totalCount;
    }

    public List<Integer> getPages() {
        final Long total = getTotalCount();
        final int totalPages = (int) Math.ceil(((double) total) / ((double) paging));
        final List<Integer> ret = new ArrayList<Integer>(totalPages);
        for (int i = 0; i < totalPages; i++) {
            ret.add(i + 1);
        }
        return ret;
    }

    public int getIndex() {
        return index;
    }

    /**
     * Set the index. The index is automatically put within the range 0 - 99999 if it exceeds those bounds.
     *
     * @param index The index.
     */
    public void setIndex(final int index) {
        this.index = Math.min(Math.max(index, 0), 99999);
    }

    public int getPaging() {
        return paging;
    }

    /**
     * Set the paging. The paging is automatically put within the range 1 - 50 if it exceeds those bounds.
     *
     * @param paging The paging.
     */
    public void setPaging(final int paging) {
        // Minimum value is 1 to prevent divisions by zero.
        this.paging = Math.min(Math.max(paging, 1), 50);
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

    public long getTotalCount() {
        return totalCount;
    }

    public List<?> getList() {
        return list;
    }

    public void setPaginatedList(List<?> completeList) {
        int listSize = completeList.size();
        if (index > listSize) {
            index = listSize - (listSize % paging);
        }
        final int fromIndex = Math.min(index, listSize);
        final int toIndex = Math.min(fromIndex + paging, listSize);
        final List<?> subList = completeList.subList(fromIndex, toIndex);

        list = new ArrayList<Object>(subList);
        totalCount = listSize;
    }
}
