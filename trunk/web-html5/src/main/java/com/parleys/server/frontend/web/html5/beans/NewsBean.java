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

    @ManagedProperty("#{parleysService}")
    private ParleysServiceDelegate parleysServiceDelegate;

    public void init() {
        newsItems = parleysServiceDelegate.loadAllNewsItems();
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

    public void setParleysServiceDelegate(ParleysServiceDelegate parleysServiceDelegate) {
        this.parleysServiceDelegate = parleysServiceDelegate;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public long getNewsId() {
        return newsId;
    }

    public NewsItem getActiveNewsItem() {
        return activeNewsItem;
    }

    public void setActiveNewsItem(NewsItem activeNewsItem) {
        this.activeNewsItem = activeNewsItem;
    }

    public List<NewsItem> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(List<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }

    public ParleysServiceDelegate getParleysServiceDelegate() {
        return parleysServiceDelegate;
    }
}
