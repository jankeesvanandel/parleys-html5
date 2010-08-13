/*
 * Copyright (C) 2010 Parleys.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.parleys.server.frontend.service.impl;

import com.parleys.server.domain.News;
import com.parleys.server.domain.types.FeaturedType;
import com.parleys.server.domain.types.NewsType;
import com.parleys.server.domain.types.PresentationDateRange;
import com.parleys.server.domain.types.PresentationSort;
import com.parleys.server.domain.types.PresentationType;
import com.parleys.server.dto.AbstractDTO;
import com.parleys.server.dto.ChannelOverviewDTO;
import com.parleys.server.dto.ExtendedPresentationDetailsDTO;
import com.parleys.server.dto.FilteredOverviewResponseDTO;
import com.parleys.server.dto.OverviewResponseDTO;
import com.parleys.server.dto.PresentationOverviewDTO;
import com.parleys.server.dto.SpaceOverviewDTO;
import com.parleys.server.frontend.service.PresentationsCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Parleys Service delegate implementation.
 *
 * @author Stephan Janssen
 */
@Service("parleysService")
public class ParleysServiceDelegateImpl extends AbstractParleysServiceDelegateImpl {

    /** {@inheritDoc} */
    @Override
    public List<? extends AbstractDTO> getFeatured(final FeaturedType type) {
        return getParleysServiceProxy().getFeatured(type.name());
    }

    /** {@inheritDoc} */
    @Override
    public List<? extends AbstractDTO> getFeaturedContent() {
        return getParleysServiceProxy().getFeaturedContent();
    }

    /** {@inheritDoc} */
    @Override
    public FilteredOverviewResponseDTO<SpaceOverviewDTO> getSpacesOverview(final int index, final int paging) {
        return getParleysServiceProxy().getSpacesOverview(index, paging);
    }

    /** {@inheritDoc} */
    @Override
    public FilteredOverviewResponseDTO<ChannelOverviewDTO> getChannelsOverview(final long spaceId) {
        return getParleysServiceProxy().getChannelsOverview(spaceId);
    }

    /** {@inheritDoc} */
    @Override
    public SpaceOverviewDTO getSpaceOverviewDTO(final long spaceId) {
        return getParleysServiceProxy().getSpaceOverviewDTO(spaceId);
    }

    /** {@inheritDoc} */
    @Override
    public ChannelOverviewDTO getChannelOverviewDTO(final long channelId) {
        return getParleysServiceProxy().getChannelOverviewDTO(channelId);
    }

    /** {@inheritDoc} */
    @Override
    public ExtendedPresentationDetailsDTO getPresentationDetails(final long presentationId) {
        return getParleysServiceProxy().getPresentationDetails(presentationId);
    }

    /** {@inheritDoc} */
    @Override
    public List<? extends AbstractDTO> getPresentationsOverview(final PresentationsCriteria criteria) {
        return getParleysServiceProxy().getPresentationsOverview(criteria.getChannelId(),
                criteria.getIndex(),
                criteria.getPaging(),
                PresentationType.ALL.name(),
                PresentationSort.PUBLICATION.name(),
                PresentationDateRange.ALL_TIME.name()).getOverviews();
    }

    /** {@inheritDoc} */
    @Override
    public List<PresentationOverviewDTO> getLatestPresentationsOverview(final PresentationsCriteria criteria) {
        int index = criteria.getIndex();
        int paging = criteria.getPaging();
        return getParleysServiceProxy().getLatestPresentationsOverview(index, paging).getOverviews();
    }

    /** {@inheritDoc} */
    @Override
    public List<PresentationOverviewDTO> getTopRatedPresentationsOverview(final PresentationsCriteria criteria) {
        int index = criteria.getIndex();
        int paging = criteria.getPaging();
        return getParleysServiceProxy().getTopRatedPresentationsOverview(index, paging).getOverviews();
    }

    /** {@inheritDoc} */
    @Override
    public List<PresentationOverviewDTO> getMostViewedPresentationsOverview(final PresentationsCriteria criteria) {
        int index = criteria.getIndex();
        int paging = criteria.getPaging();
        return getParleysServiceProxy().getMostViewedPresentationsOverview(index, paging).getOverviews();
    }

    /** {@inheritDoc} */
    @Override
    public OverviewResponseDTO<News> getNews(final NewsType newsType,
                                             final long id,
                                             final int index,
                                             final int paging) {
        return getParleysServiceProxy().getNews(newsType.name(), id, index, paging);
    }

    /** {@inheritDoc} */
    @Override
    public List<PresentationOverviewDTO> search(final String criteria) {
        return null;
    }
}
