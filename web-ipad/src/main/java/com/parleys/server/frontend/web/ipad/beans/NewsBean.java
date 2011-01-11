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
import com.parleys.server.domain.types.NewsType;
import com.parleys.server.frontend.web.jsf.util.JSFUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for the homepage.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean
@RequestScoped
public class NewsBean extends AbstractParleysBean {

    private long newsId;

    private News activeNewsItem;

    private List<News> newsItems;

    @ManagedProperty("#{navigationBean}")
    private NavigationBean navigationBean;

    public void init() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        newsItems = getParleysService().getNews(NewsType.GENERAL, 0, 0, 10).getOverviews();

        if (newsId > 0) {
            for (News newsItem : newsItems) {
                if (newsItem.getId() == newsId) {
                    activeNewsItem = newsItem;
                    break;
                }
            }
        }

        navigationBean.initHomepage();
    }

    public void setNewsId(final long newsId) {
        this.newsId = newsId;
    }

    public long getNewsId() {
        return newsId;
    }

    public News getActiveNewsItem() {
        return activeNewsItem;
    }

    public void setActiveNewsItem(final News activeNewsItem) {
        this.activeNewsItem = activeNewsItem;
    }

    public List<News> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(final List<News> newsItems) {
        this.newsItems = newsItems;
    }

    public NavigationBean getNavigationBean() {
        return navigationBean;
    }

    public void setNavigationBean(final NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }
}
