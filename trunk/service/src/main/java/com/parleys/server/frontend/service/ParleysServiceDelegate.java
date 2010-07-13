package com.parleys.server.frontend.service;

import com.parleys.server.dto.ChannelOverviewDTO;
import com.parleys.server.dto.FilteredOverviewResponseDTO;
import com.parleys.server.dto.SpaceOverviewDTO;
import com.parleys.server.frontend.domain.*;
import com.parleys.server.security.AuthorizationException;
import com.parleys.server.service.exception.ParleysServiceException;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;

import java.util.List;

/**
 * Service for Parleys operations.
 */
public interface ParleysServiceDelegate {

    /**
     * Gets public, listed and optionally the private administered spaces for logged in user.
     *
     * @param index  start index.
     * @param paging number of spaces to return form start index.
     * @return overview info about spaces.
     * @throws flex.messaging.io.amf.client.exceptions.ClientStatusException AMF problem occured
     */
    FilteredOverviewResponseDTO<SpaceOverviewDTO> getSpacesOverview(int index, int paging) throws ClientStatusException;

    /**
     * Returns overview info about channels for particular space.
     * It is returns 0 for startingIndex and the same for paging.
     *
     * @param spaceId Is an identifier of space to get channels.
     * @return overview     info about channels for particular space.
     * @throws com.parleys.server.security.AuthorizationException  User is not authorized
     * @throws com.parleys.server.service.exception.ParleysServiceException internal error occured
     */
    FilteredOverviewResponseDTO<ChannelOverviewDTO> getChannelsOverview(long spaceId)
            throws ParleysServiceException, AuthorizationException, ClientStatusException;

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
