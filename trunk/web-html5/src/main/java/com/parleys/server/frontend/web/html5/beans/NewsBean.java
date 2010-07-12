package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.frontend.domain.NewsItem;
import com.parleys.server.frontend.service.ParleysServiceDelegate;
import com.parleys.server.service.ParleysService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for the homepage.
 */
@ManagedBean
@RequestScoped
public class NewsBean extends AbstractParleysBean {

    private long newsId;

    private NewsItem activeNewsItem;

    private List<NewsItem> newsItems;

    public void init() {
        newsItems = getParleysServiceDelegate().loadAllNewsItems();
        if (newsId > 0) {
            for (NewsItem newsItem : newsItems) {
                if (newsItem.getId() == newsId) {
                    activeNewsItem = newsItem;
                    break;
                }
            }
        }

        initializeHomepage();
    }

    public void setNewsId(final long newsId) {
        this.newsId = newsId;
    }

    public long getNewsId() {
        return newsId;
    }

    public NewsItem getActiveNewsItem() {
        return activeNewsItem;
    }

    public void setActiveNewsItem(final NewsItem activeNewsItem) {
        this.activeNewsItem = activeNewsItem;
    }

    public List<NewsItem> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(final List<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }
}
