package com.parleys.server.frontend.service.impl;

import com.parleys.server.domain.News;
import com.parleys.server.domain.types.*;
import com.parleys.server.dto.*;
import com.parleys.server.frontend.service.PresentationsCriteria;
import com.parleys.server.security.AuthorizationException;
import com.parleys.server.service.exception.ParleysServiceException;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * The Parleys Service delegate implementation.
 *
 * @author Stephan Janssen
 */
@Service("parleysService")
public class ParleysServiceDelegateImpl extends AbstractParleysServiceDelegateImpl {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends AbstractDTO> getFeatured(final FeaturedType type) throws ClientStatusException {
        return getParleysServiceProxy().getFeatured(type.name());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends AbstractDTO> getFeaturedContent() throws ClientStatusException {
        return getParleysServiceProxy().getFeaturedContent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FilteredOverviewResponseDTO<SpaceOverviewDTO> getSpacesOverview(final int index, final int paging)
            throws ClientStatusException {
        return getParleysServiceProxy().getSpacesOverview(index, paging);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FilteredOverviewResponseDTO<ChannelOverviewDTO> getChannelsOverview(final long spaceId)
            throws ParleysServiceException, AuthorizationException, ClientStatusException {
        return getParleysServiceProxy().getChannelsOverview(spaceId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpaceOverviewDTO getSpaceOverviewDTO(final long spaceId)
            throws AuthorizationException, ClientStatusException {
        return getParleysServiceProxy().getSpaceOverviewDTO(spaceId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChannelOverviewDTO getChannelOverviewDTO(final long channelId)
            throws AuthorizationException, ClientStatusException {
        return getParleysServiceProxy().getChannelOverviewDTO(channelId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExtendedPresentationDetailsDTO getPresentationDetails(final long presentationId)
            throws ClientStatusException, AuthorizationException {
        
        return getParleysServiceProxy().getPresentationDetails(presentationId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends AbstractDTO>
        getPresentationsOverview(final PresentationsCriteria criteria)
                throws ParleysServiceException, AuthorizationException, ClientStatusException {

        return getParleysServiceProxy().getPresentationsOverview(criteria.getChannelId(),
                                                                 criteria.getIndex(),
                                                                 criteria.getPaging(),
                                                                 PresentationType.ALL.name(),
                                                                 PresentationSort.PUBLICATION.name(),
                                                                 PresentationDateRange.ALL_TIME.name()).getOverviews();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PresentationOverviewDTO>
        getLatestPresentationsOverview(final PresentationsCriteria criteria)
            throws ClientStatusException {

        return getParleysServiceProxy()
                    .getLatestPresentationsOverview(criteria.getIndex(), criteria.getPaging()).getOverviews();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PresentationOverviewDTO>
        getTopRatedPresentationsOverview(final PresentationsCriteria criteria) throws ClientStatusException {

        return getParleysServiceProxy()
                .getTopRatedPresentationsOverview(criteria.getIndex(), criteria.getPaging()).getOverviews();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PresentationOverviewDTO>
        getMostViewedPresentationsOverview(final PresentationsCriteria criteria) throws ClientStatusException {

        return getParleysServiceProxy()
                .getMostViewedPresentationsOverview(criteria.getIndex(), criteria.getPaging()).getOverviews();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OverviewResponseDTO<News> getNews(final NewsType newsType, 
                                             final long id,
                                             final int index,
                                             final int paging) throws AuthorizationException, ClientStatusException {
        return getParleysServiceProxy().getNews(newsType.name(), id, index, paging);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PresentationOverviewDTO> search(final String criteria) {
        return null;
    }
}
