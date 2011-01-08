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
package com.parleys.server.frontend.web.ipad.beans;

import com.parleys.server.domain.News;
import com.parleys.server.frontend.domain.Filter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * View scoped bean for cross-request data on the homepage.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean
@ViewScoped
public class HomepageViewBean implements Serializable {

    private static final long serialVersionUID = -5033618962891281882L;

    private long activeNewsItemIndex = 0L;

    private List<News> newsItems;

    private Filter thumbnailsFilter;

    private Filter.Type thumbnailsFilterType;

    public long getActiveNewsItemIndex() {
        return activeNewsItemIndex;
    }

    public void setActiveNewsItemIndex(long activeNewsItemIndex) {
        this.activeNewsItemIndex = activeNewsItemIndex;
    }

    public List<News> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(List<News> newsItems) {
        this.newsItems = newsItems;
    }

    public Filter getThumbnailsFilter() {
        return thumbnailsFilter;
    }

    public void setThumbnailsFilter(Filter thumbnailsFilter) {
        this.thumbnailsFilter = thumbnailsFilter;
    }

    public Filter.Type getThumbnailsFilterType() {
        return thumbnailsFilterType;
    }

    public void setThumbnailsFilterType(Filter.Type thumbnailsFilterType) {
        this.thumbnailsFilterType = thumbnailsFilterType;
    }
}
