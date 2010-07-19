package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.domain.News;
import com.parleys.server.domain.types.FeaturedType;
import com.parleys.server.domain.types.NewsType;
import com.parleys.server.dto.AbstractDTO;
import com.parleys.server.dto.ChannelOverviewDTO;
import com.parleys.server.dto.PresentationOverviewDTO;
import com.parleys.server.dto.SpaceOverviewDTO;
import com.parleys.server.frontend.domain.Filter;
import com.parleys.server.frontend.service.PresentationsCriteria;
import com.parleys.server.frontend.web.html5.util.JSFUtil;
import com.parleys.server.security.AuthorizationException;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.Collections;
import java.util.List;

/**
 * Backing bean for the homepage.
 */
@ManagedBean @RequestScoped
public class HomepageBean extends AbstractParleysBean {

    private final transient Log LOG = LogFactory.getLog(getClass());

    private Filter thumbnailsFilter;

    private Filter.Type thumbnailsFilterType;

    @ManagedProperty("#{homepageViewBean}")
    private HomepageViewBean homepageViewBean;

    private long newsId;

    private List<? extends AbstractDTO> thumbnails;

    private SpaceOverviewDTO recommendedSpace;

    private ChannelOverviewDTO recommendedChannel;

    private PresentationOverviewDTO recommendedPresentation;

    @SuppressWarnings("unchecked")
    public void init() {
        if (JSFUtil.fc().getPartialViewContext().isAjaxRequest()) {
            return;
        }

        if (thumbnailsFilter == null || thumbnailsFilterType == null) {
            thumbnailsFilter = Filter.FEATURED;
            thumbnailsFilterType = Filter.Type.PRESENTATION;
        }

        try {
            thumbnails = getParleysServiceDelegate().getFeatured(FeaturedType.PRESENTATION);

            homepageViewBean.setNewsItems(getParleysServiceDelegate().getNews(NewsType.GENERAL, 0, 0, 10).getOverviews());

            if (newsId > 0) {
                int counter = 0;
                for (News newsItem : homepageViewBean.getNewsItems()) {
                    if (newsItem.getId().equals(newsId)) {
                        homepageViewBean.setActiveNewsItemIndex(counter);
                        break;
                    }
                    counter++;
                }
            }
            
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

    public String viewThumbnails(Filter filter, Filter.Type filterType) throws ClientStatusException {
        thumbnails = Collections.emptyList();

        if (filter != null && filterType != null) {
            if (filterType == Filter.Type.PRESENTATION) {
                if (filter == Filter.FEATURED) {
                    thumbnails = getParleysServiceDelegate().getFeatured(FeaturedType.PRESENTATION);
                } else if (filter == Filter.LATEST) {
                    PresentationsCriteria criteria = new PresentationsCriteria();
                    criteria.setPaging(6);
                    thumbnails = getParleysServiceDelegate().getLatestPresentationsOverview(criteria);
                } else if (filter == Filter.TOP_RATED) {
                    PresentationsCriteria criteria = new PresentationsCriteria();
                    criteria.setPaging(6);
                    thumbnails = getParleysServiceDelegate().getTopRatedPresentationsOverview(criteria);
                } else if (filter == Filter.MOST_VIEWED) {
                    PresentationsCriteria criteria = new PresentationsCriteria();
                    criteria.setPaging(6);
                    thumbnails = getParleysServiceDelegate().getMostViewedPresentationsOverview(criteria);
                }
            } else if (filterType == Filter.Type.CHANNEL) {
                thumbnails = getParleysServiceDelegate().getFeatured(FeaturedType.CHANNEL);
            } else if (filterType == Filter.Type.SPACE) {
                thumbnails = getParleysServiceDelegate().getFeatured(FeaturedType.SPACE);
            }
        }

        return null;
    }

    public String gotoNewsItem(Long id) {
        try {
            homepageViewBean.setNewsItems(getParleysServiceDelegate().getNews(NewsType.GENERAL, 0, 0, 10).getOverviews());
        } catch (AuthorizationException e) {
            LOG.error(e);
        } catch (ClientStatusException e) {
            LOG.error(e);
        }
        final List<News> newsItems = homepageViewBean.getNewsItems();
        for (int i = 0; i < newsItems.size(); i++) {
            News newsItem = newsItems.get(i);
            if (newsItem.getId().equals(id)) {
                homepageViewBean.setActiveNewsItemIndex(i);
                break;
            }
        }

        return null;
    }

    public List<? extends AbstractDTO> getThumbnails() {
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

    public void setHomepageViewBean(HomepageViewBean homepageViewBean) {
        this.homepageViewBean = homepageViewBean;
    }

    public HomepageViewBean getHomepageViewBean() {
        return homepageViewBean;
    }
}
