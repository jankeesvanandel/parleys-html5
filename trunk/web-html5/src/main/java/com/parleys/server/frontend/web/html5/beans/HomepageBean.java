package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.domain.News;
import com.parleys.server.domain.Thumbnail;
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
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Backing bean for the homepage.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean @RequestScoped
public class HomepageBean extends AbstractParleysBean {

    private static final Logger LOGGER = Logger.getLogger(HomepageBean.class);

    @ManagedProperty("#{homepageViewBean}")
    private HomepageViewBean homepageViewBean;

    private long newsId;

    private List<Thumbnail> thumbnails;

    private SpaceOverviewDTO recommendedSpace;

    private ChannelOverviewDTO recommendedChannel;

    private PresentationOverviewDTO recommendedPresentation;

    @SuppressWarnings("unchecked")
    public void init() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        if (homepageViewBean.getThumbnailsFilter() == null || homepageViewBean.getThumbnailsFilterType() == null) {
            homepageViewBean.setThumbnailsFilter(Filter.FEATURED);
            homepageViewBean.setThumbnailsFilterType(Filter.Type.PRESENTATION);
        }

        try {
            transformToThumbnails(getParleysServiceDelegate().getFeatured(FeaturedType.PRESENTATION), homepageViewBean.getIndex());

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

            final List<? extends AbstractDTO> featuredContent = getParleysServiceDelegate().getFeaturedContent();

            recommendedSpace = (SpaceOverviewDTO)featuredContent.get(0);
            recommendedChannel = (ChannelOverviewDTO)featuredContent.get(1);
            recommendedPresentation = (PresentationOverviewDTO)featuredContent.get(2);

        } catch (AuthorizationException e) {
            LOGGER.error(e);
        } catch (ClientStatusException e) {
            LOGGER.error(e);
        }

        initializeHomepage();
    }

    public String viewThumbnails(Filter filter, Filter.Type filterType, int index) throws ClientStatusException {
        thumbnails = Collections.emptyList();
        homepageViewBean.setIndex(index);
        homepageViewBean.setThumbnailsFilter(filter);
        homepageViewBean.setThumbnailsFilterType(filterType);

        if (filter != null && filterType != null) {
            if (filterType == Filter.Type.PRESENTATION) {
                final PresentationsCriteria criteria = new PresentationsCriteria();
                criteria.setIndex(0);
                criteria.setPaging(99);
                if (filter == Filter.FEATURED) {
                    transformToThumbnails(getParleysServiceDelegate().getFeatured(FeaturedType.PRESENTATION), index);
                } else if (filter == Filter.LATEST) {
                    transformToThumbnails(getParleysServiceDelegate().getLatestPresentationsOverview(criteria), index);
                } else if (filter == Filter.TOP_RATED) {
                    transformToThumbnails(getParleysServiceDelegate().getTopRatedPresentationsOverview(criteria), index);
                } else if (filter == Filter.MOST_VIEWED) {
                    transformToThumbnails(getParleysServiceDelegate().getMostViewedPresentationsOverview(criteria), index);
                }
            } else if (filterType == Filter.Type.CHANNEL) {
                transformToThumbnails(getParleysServiceDelegate().getFeatured(FeaturedType.CHANNEL), index);
            } else if (filterType == Filter.Type.SPACE) {
                transformToThumbnails(getParleysServiceDelegate().getFeatured(FeaturedType.SPACE), index);
            }
        }

        return null;
    }

    private void transformToThumbnails(final List<? extends AbstractDTO> thumbnailsIn, int index) {
        if (index < 0) {
            index = 0;
        }
        if (index >= thumbnailsIn.size()) {
            index = 0;
        }

        List<? extends AbstractDTO> newThumbnailsIn = thumbnailsIn;
        if (thumbnailsIn.size() > 6) {
            final int endIndex = Math.min(index + 6, newThumbnailsIn.size());
            newThumbnailsIn = newThumbnailsIn.subList(index, endIndex);
            homepageViewBean.setHasPreviousThumbnail(index > 0);
            homepageViewBean.setHasNextThumbnail(index + 6 <= newThumbnailsIn.size());
        }

        List<Thumbnail> ret = new ArrayList<Thumbnail>();

        for (AbstractDTO abstractDTO : newThumbnailsIn) {
            final Thumbnail thumbnail = new Thumbnail();
            thumbnail.setId(abstractDTO.getId());
            if (abstractDTO instanceof PresentationOverviewDTO) {
                thumbnail.setName(((PresentationOverviewDTO) abstractDTO).getTitle());
                thumbnail.setThumbnailUrl(JSFUtil.presentationThumbnail(thumbnail.getId(), ((PresentationOverviewDTO) abstractDTO).getThumbnailURL()));
                thumbnail.setOutcome("presentation");
            } else if (abstractDTO instanceof ChannelOverviewDTO) {
                thumbnail.setName(((ChannelOverviewDTO) abstractDTO).getName());
                thumbnail.setThumbnailUrl(JSFUtil.channelThumbnail(thumbnail.getId(), ((ChannelOverviewDTO) abstractDTO).getThumbnailURL()));
                thumbnail.setOutcome("presentations");
            } else if (abstractDTO instanceof SpaceOverviewDTO) {
                thumbnail.setName(((SpaceOverviewDTO) abstractDTO).getName());
                thumbnail.setThumbnailUrl(JSFUtil.spaceThumbnail(thumbnail.getId(), ((SpaceOverviewDTO) abstractDTO).getThumbnailURL()));
                thumbnail.setOutcome("channels");
            }

            ret.add(thumbnail);
        }

        thumbnails = ret;
    }

    public String gotoNewsItem(Long id) {
        try {
            homepageViewBean.setNewsItems(getParleysServiceDelegate().getNews(NewsType.GENERAL, 0, 0, 10).getOverviews());
        } catch (AuthorizationException e) {
            LOGGER.error(e);
        } catch (ClientStatusException e) {
            LOGGER.error(e);
        }
        final List<News> newsItems = homepageViewBean.getNewsItems();
        for (int i = 0; i < newsItems.size(); i++) {
            final News newsItem = newsItems.get(i);
            if (newsItem.getId().equals(id)) {
                homepageViewBean.setActiveNewsItemIndex(i);
                break;
            }
        }

        return null;
    }

    public List<Thumbnail> getThumbnails() {
        return thumbnails;
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
