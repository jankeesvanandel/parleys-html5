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

import com.googlecode.ehcache.annotations.Cacheable;
import com.parleys.server.domain.News;
import com.parleys.server.domain.Thumbnail;
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
import com.parleys.server.dto.SpeakerDTO;
import com.parleys.server.frontend.service.PresentationsCriteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Parleys Service delegate implementation.
 *
 * @author Stephan Janssen
 */
@Service("parleysService")
public class ParleysServiceDelegateImpl extends AbstractParleysServiceDelegateImpl {

    /** {@inheritDoc} */
    @Cacheable(cacheName="featured")
    @Override
    public List<? extends AbstractDTO> getFeatured(final FeaturedType type) {
        return getParleysServiceProxy().getFeatured(type.name());
    }

    /** {@inheritDoc} */
    @Cacheable(cacheName="featured")
    @Override
    public List<? extends AbstractDTO> getFeaturedContent() {
        return getParleysServiceProxy().getFeaturedContent();
    }

    /** {@inheritDoc} */
    @Cacheable(cacheName="spaces")
    @Override
    public FilteredOverviewResponseDTO<SpaceOverviewDTO> getSpacesOverview(final int index, final int paging) {
        return getParleysServiceProxy().getSpacesOverview(index, paging);
    }

    /** {@inheritDoc} */
    @Cacheable(cacheName="channels")
    @Override
    public FilteredOverviewResponseDTO<ChannelOverviewDTO> getChannelsOverview(final long spaceId) {
        return getParleysServiceProxy().getChannelsOverview(spaceId);
    }

    /** {@inheritDoc} */
    @Cacheable(cacheName="spaces")
    @Override
    public SpaceOverviewDTO getSpaceOverviewDTO(final long spaceId) {
        return getParleysServiceProxy().getSpaceOverviewDTO(spaceId);
    }

    /** {@inheritDoc} */
    @Cacheable(cacheName="channels")
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
    @Cacheable(cacheName="presentations")
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
    @Cacheable(cacheName="presentations")
    @Override
    public List<PresentationOverviewDTO> getLatestPresentationsOverview(final PresentationsCriteria criteria) {
        int index = criteria.getIndex();
        int paging = criteria.getPaging();
        return getParleysServiceProxy().getLatestPresentationsOverview(index, paging).getOverviews();
    }

    /** {@inheritDoc} */
    @Cacheable(cacheName="presentations")
    @Override
    public List<PresentationOverviewDTO> getTopRatedPresentationsOverview(final PresentationsCriteria criteria) {
        int index = criteria.getIndex();
        int paging = criteria.getPaging();
        return getParleysServiceProxy().getTopRatedPresentationsOverview(index, paging).getOverviews();
    }

    /** {@inheritDoc} */
    @Cacheable(cacheName="presentations")
    @Override
    public List<PresentationOverviewDTO> getMostViewedPresentationsOverview(final PresentationsCriteria criteria) {
        int index = criteria.getIndex();
        int paging = criteria.getPaging();
        return getParleysServiceProxy().getMostViewedPresentationsOverview(index, paging).getOverviews();
    }

    /** {@inheritDoc} */
    @Cacheable(cacheName="news")
    @Override
    public OverviewResponseDTO<News> getNews(final NewsType newsType,
                                             final long id,
                                             final int index,
                                             final int paging) {
        return getParleysServiceProxy().getNews(newsType.name(), id, index, paging);
    }


    /** {@inheritDoc} */
    @Cacheable(cacheName="presentations")
    @Override
    public List<PresentationOverviewDTO> searchPresentations(String searchText,
                                                                 int startIndex,
                                                                 int resultsCount) {
        return getParleysServiceProxy().searchPresentations(searchText,startIndex,resultsCount).getOverviews();
    }

    /** {@inheritDoc} */
    @Cacheable(cacheName="photoSlideShow")
    @Override
    public List<Thumbnail> loadPhotoSlideShow() {
        List<Long> ids = Arrays.asList(1759L, 1789L, 1571L, 1881L);
        List<Thumbnail> ret = new ArrayList<Thumbnail>();

        for (Long id : ids) {
            ExtendedPresentationDetailsDTO dto = getParleysServiceProxy().getPresentationDetails(id);
            Thumbnail photo = transformToPhotoSlideShowItem(dto);
            if (id == 1759) {
                photo.setPhoto("james");
            } else if (id == 1789) {
                photo.setPhoto("keesjan");
            } else if (id == 1571) {
                photo.setPhoto("cameron");
            } else if (id == 1881) {
                photo.setPhoto("lombok");
            } else {
                throw new AssertionError("Unsupported presentation");
            }
            ret.add(photo);
        }

        return ret;
    }

    private Thumbnail transformToPhotoSlideShowItem(ExtendedPresentationDetailsDTO dto) {
        Thumbnail thumbnail = new Thumbnail();
        thumbnail.setId(dto.getId());
        thumbnail.setName(dto.getTitle());
        List<SpeakerDTO> speakers = dto.getSpeakers();
        String secondLine = (speakers != null && !speakers.isEmpty()) ? speakers.get(0).getName() : "Unknown speaker";
        thumbnail.setSecondLine(secondLine);
        return thumbnail;
    }


}
