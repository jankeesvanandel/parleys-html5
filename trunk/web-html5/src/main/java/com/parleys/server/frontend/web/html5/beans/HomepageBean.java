package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.frontend.domain.*;
import com.parleys.server.frontend.service.ParleysService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for the homepage.
 */
@ManagedBean
@RequestScoped
public class HomepageBean {

    private Filter thumbnailsFilter;

    private Filter.Type thumbnailsFilterType;

    private List<Presentation> thumbnails;

    private Long newsId;

    private int activeNewsItemIndex = 0;

    private List<NewsItem> newsItems;

    private Space recommendedSpace;

    private Channel recommendedChannel;

    private Presentation recommendedPresentation;

    @ManagedProperty("#{parleysService}")
    private ParleysService parleysService;

    public void init() {
        if (thumbnailsFilter == null
         || thumbnailsFilterType == null) {
            thumbnailsFilter = Filter.FEATURED;
            thumbnailsFilterType = Filter.Type.PRESENTATION;
        }

        thumbnails = parleysService.loadPresentations(thumbnailsFilter, thumbnailsFilterType, 0, 6);

        newsItems = parleysService.loadAllNewsItems();
        if (newsId != null) {
            for (int i = 0; i < newsItems.size(); i++) {
                NewsItem newsItem =  newsItems.get(i);
                if (newsItem.getId() == newsId) {
                    activeNewsItemIndex = i;
                    break;
                }
            }
        }

        recommendedSpace = parleysService.loadRecommendedSpace();
        recommendedChannel = parleysService.loadRecommendedChannel();
        recommendedPresentation = parleysService.loadRecommendedPresentation();
    }

    public List<Presentation> getThumbnails() {
        return thumbnails;
    }

    public void setParleysService(ParleysService parleysService) {
        this.parleysService = parleysService;
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

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public Long getNewsId() {
        return newsId;
    }

    public int getActiveNewsItemIndex() {
        return activeNewsItemIndex;
    }

    public void setActiveNewsItemIndex(int activeNewsItemIndex) {
        this.activeNewsItemIndex = activeNewsItemIndex;
    }

    public List<NewsItem> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(List<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }

    public Space getRecommendedSpace() {
        return recommendedSpace;
    }

    public void setRecommendedSpace(Space recommendedSpace) {
        this.recommendedSpace = recommendedSpace;
    }

    public Channel getRecommendedChannel() {
        return recommendedChannel;
    }

    public void setRecommendedChannel(Channel recommendedChannel) {
        this.recommendedChannel = recommendedChannel;
    }

    public Presentation getRecommendedPresentation() {
        return recommendedPresentation;
    }

    public void setRecommendedPresentation(Presentation recommendedPresentation) {
        this.recommendedPresentation = recommendedPresentation;
    }
}
