package com.parleys.server.frontend.web.html5.beans;

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
@ManagedBean @ViewScoped
public class HomepageViewBean implements Serializable {

    private static final long serialVersionUID = -5033618962891281882L;

    private long activeNewsItemIndex = 0L;

    private List<News> newsItems;

    private Filter thumbnailsFilter;

    private Filter.Type thumbnailsFilterType;

    private int index = 0;

    private boolean hasPreviousThumbnail = false;

    private boolean hasNextThumbnail = false;

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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean getHasPreviousThumbnail() {
        return hasPreviousThumbnail;
    }

    public void setHasPreviousThumbnail(boolean hasPreviousThumbnail) {
        this.hasPreviousThumbnail = hasPreviousThumbnail;
    }

    public boolean getHasNextThumbnail() {
        return hasNextThumbnail;
    }

    public void setHasNextThumbnail(boolean hasNextThumbnail) {
        this.hasNextThumbnail = hasNextThumbnail;
    }
}
