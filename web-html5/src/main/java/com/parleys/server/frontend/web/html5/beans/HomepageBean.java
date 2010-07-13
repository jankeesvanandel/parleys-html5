package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.domain.News;
import com.parleys.server.domain.types.FeaturedType;
import com.parleys.server.domain.types.NewsType;
import com.parleys.server.dto.AbstractDTO;
import com.parleys.server.dto.ChannelOverviewDTO;
import com.parleys.server.dto.PresentationOverviewDTO;
import com.parleys.server.dto.SpaceOverviewDTO;
import com.parleys.server.frontend.domain.Filter;
import com.parleys.server.security.AuthorizationException;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for the homepage.
 */
@ManagedBean
@RequestScoped
public class HomepageBean extends AbstractParleysBean {

    private final transient Log LOG = LogFactory.getLog(getClass());

    private Filter thumbnailsFilter;

    private Filter.Type thumbnailsFilterType;

    private Long newsId;

    private int activeNewsItemIndex = 0;

    private List<PresentationOverviewDTO> thumbnails;

    private List<News> newsItems;

    private SpaceOverviewDTO recommendedSpace;

    private ChannelOverviewDTO recommendedChannel;

    private PresentationOverviewDTO recommendedPresentation;

    @SuppressWarnings("unchecked")
    public void init() {
        if (thumbnailsFilter == null || thumbnailsFilterType == null) {
            thumbnailsFilter = Filter.FEATURED;
            thumbnailsFilterType = Filter.Type.PRESENTATION;
        }

        try {
            thumbnails = (List<PresentationOverviewDTO>) getParleysServiceDelegate().getFeatured(FeaturedType.PRESENTATION);

            newsItems = getParleysServiceDelegate().getNews(NewsType.GENERAL, 0, 0, 10).getOverviews();

            List<? extends AbstractDTO> featuredContent = getParleysServiceDelegate().getFeaturedContent();

            recommendedSpace = (SpaceOverviewDTO)featuredContent.get(0);
            recommendedChannel = (ChannelOverviewDTO)featuredContent.get(1);
            recommendedPresentation = (PresentationOverviewDTO)featuredContent.get(2);

        } catch (AuthorizationException e) {
            LOG.error(e);
        } catch (ClientStatusException e) {
            LOG.error(e);
        }

        initializeHomepage();
    }

    public List<PresentationOverviewDTO> getThumbnails() {
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

    public List<News> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(final List<News> newsItems) {
        this.newsItems = newsItems;
    }

    public SpaceOverviewDTO getRecommendedSpace() {
        return recommendedSpace;
    }

    public void setRecommendedSpace(final SpaceOverviewDTO recommendedSpace) {
        this.recommendedSpace = recommendedSpace;
    }

    public ChannelOverviewDTO getRecommendedChannel() {
        return recommendedChannel;
    }

    public void setRecommendedChannel(final ChannelOverviewDTO recommendedChannel) {
        this.recommendedChannel = recommendedChannel;
    }

    public PresentationOverviewDTO getRecommendedPresentation() {
        return recommendedPresentation;
    }

    public void setRecommendedPresentation(final PresentationOverviewDTO recommendedPresentation) {
        this.recommendedPresentation = recommendedPresentation;
    }
}
