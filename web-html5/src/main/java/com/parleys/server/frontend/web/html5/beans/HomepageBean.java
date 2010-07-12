package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.frontend.domain.*;
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
public class HomepageBean extends AbstractParleysBean {

    private Filter thumbnailsFilter;

    private Filter.Type thumbnailsFilterType;

    private List<Presentation> thumbnails;

    private Long newsId;

    private int activeNewsItemIndex = 0;

    private List<NewsItem> newsItems;

    private Space recommendedSpace;

    private Channel recommendedChannel;

    private Presentation recommendedPresentation;

    public void init() {
        if (thumbnailsFilter == null
                || thumbnailsFilterType == null) {
            thumbnailsFilter = Filter.FEATURED;
            thumbnailsFilterType = Filter.Type.PRESENTATION;
        }

        thumbnails = getParleysServiceDelegate().loadPresentations(thumbnailsFilter, thumbnailsFilterType, 0, 6);

        newsItems = getParleysServiceDelegate().loadAllNewsItems();
        if (newsId != null) {
            for (int i = 0; i < newsItems.size(); i++) {
                NewsItem newsItem = newsItems.get(i);
                if (newsItem.getId() == newsId) {
                    activeNewsItemIndex = i;
                    break;
                }
            }
        }

        recommendedSpace = getParleysServiceDelegate().loadRecommendedSpace();
        recommendedChannel = getParleysServiceDelegate().loadRecommendedChannel();
        recommendedPresentation = getParleysServiceDelegate().loadRecommendedPresentation();

        initializeHomepage();
    }

    public List<Presentation> getThumbnails() {
        return thumbnails;
    }

    public Filter getThumbnailsFilter() {
        return thumbnailsFilter;
    }

    public void setThumbnailsFilter(final Filter thumbnailsFilter) {
        this.thumbnailsFilter = thumbnailsFilter;
    }

    public Filter.Type getThumbnailsFilterType() {
        return thumbnailsFilterType;
    }

    public void setThumbnailsFilterType(final Filter.Type thumbnailsFilterType) {
        this.thumbnailsFilterType = thumbnailsFilterType;
    }

    public void setNewsId(final Long newsId) {
        this.newsId = newsId;
    }

    public Long getNewsId() {
        return newsId;
    }

    public int getActiveNewsItemIndex() {
        return activeNewsItemIndex;
    }

    public void setActiveNewsItemIndex(final int activeNewsItemIndex) {
        this.activeNewsItemIndex = activeNewsItemIndex;
    }

    public List<NewsItem> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(final List<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }

    public Space getRecommendedSpace() {
        return recommendedSpace;
    }

    public void setRecommendedSpace(final Space recommendedSpace) {
        this.recommendedSpace = recommendedSpace;
    }

    public Channel getRecommendedChannel() {
        return recommendedChannel;
    }

    public void setRecommendedChannel(final Channel recommendedChannel) {
        this.recommendedChannel = recommendedChannel;
    }

    public Presentation getRecommendedPresentation() {
        return recommendedPresentation;
    }

    public void setRecommendedPresentation(final Presentation recommendedPresentation) {
        this.recommendedPresentation = recommendedPresentation;
    }
}
