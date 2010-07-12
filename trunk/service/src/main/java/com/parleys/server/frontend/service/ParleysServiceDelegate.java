package com.parleys.server.frontend.service;

import com.parleys.server.dto.FilteredOverviewResponseDTO;
import com.parleys.server.dto.SpaceOverviewDTO;
import com.parleys.server.frontend.domain.*;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;

import java.util.List;

/**
 * Service for Parleys operations.
 */
public interface ParleysServiceDelegate {

    FilteredOverviewResponseDTO<SpaceOverviewDTO> getSpacesOverview(int index, int paging) throws ClientStatusException;
    
    /**
     * Get a list of spaces.
     *
     * @param index  The zero based starting index.
     * @param paging The maximum number of spaces per page.
     * @return a list of spaces, never null.
     */
    List<Space> loadSpaces(int index, int paging);

    List<Space> loadAllSpaces();

    Space loadSpace(long id);

    List<Channel> loadChannels(long spaceId);

    List<Presentation> loadPresentationsWithCriteria(PresentationsCriteria presentationsCriteria);

    Channel loadChannel(long channelId);

    Presentation loadPresentation(long presentationId);

    List<Presentation> loadPresentations(Filter thumbnailsFilter, Filter.Type thumbnailsFilterType, int index, int paging);

    List<NewsItem> loadAllNewsItems();

    Space loadRecommendedSpace();

    Channel loadRecommendedChannel();

    Presentation loadRecommendedPresentation();

    List<Presentation> search(String criteria);
}
