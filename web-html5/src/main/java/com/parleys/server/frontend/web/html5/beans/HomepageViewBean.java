package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.domain.News;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * View scoped bean for cross-request data on the homepage.
 */
@ManagedBean @ViewScoped
public class HomepageViewBean implements Serializable {

    private long activeNewsItemIndex = 0L;

    private List<News> newsItems;

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
}
