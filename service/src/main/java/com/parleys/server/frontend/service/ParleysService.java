package com.parleys.server.frontend.service;

import com.parleys.server.frontend.domain.*;

import java.util.List;

/**
 * Service for Parleys operations.
 */
public interface ParleysService {

    /**
     * Get a list of spaces.
     *
     * @param index The zero based starting index.
     * @param paging The maximum number of spaces per page.
     * @return a list of spaces, never null.
     */
    List<Space> loadSpaces(int index, int paging);

    Space loadSpace(long id);

    List<Channel> loadChannels(long spaceId);

    List<Presentation> loadPresentations(long channelId, int index, int paging);

    Channel loadChannel(long channelId);

    Presentation loadPresentation(long presentationId);

    List<Presentation> loadPresentations(Filter thumbnailsFilter, Filter.Type thumbnailsFilterType, int index, int paging);

    List<NewsItem> loadAllNewsItems();

    Space loadRecommendedSpace();

    Channel loadRecommendedChannel();

    Presentation loadRecommendedPresentation();
}
