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
package com.parleys.server.frontend.service;

import com.parleys.server.domain.News;
import com.parleys.server.domain.types.FeaturedType;
import com.parleys.server.domain.types.NewsType;
import com.parleys.server.dto.AbstractDTO;
import com.parleys.server.dto.ChannelOverviewDTO;
import com.parleys.server.dto.ExtendedPresentationDetailsDTO;
import com.parleys.server.dto.FilteredOverviewResponseDTO;
import com.parleys.server.dto.OverviewResponseDTO;
import com.parleys.server.dto.PresentationOverviewDTO;
import com.parleys.server.dto.SpaceOverviewDTO;
import com.parleys.server.security.AuthorizationException;
import com.parleys.server.service.exception.ParleysServiceException;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;

import java.util.List;

/**
 * The Parleys service delegate.
 *
 * Depending where it will get deployed it will either trigger an RPC AMF or local method call.
 *
 * TODO Add deployement flag to switch between RPC/IPC/
 *
 * @author Stephan Janssen
 */
public interface ParleysService {

    /**
     * Returns the list of featured content based on the selected type, used on the home page.
     *
     * @param type the feature type we want to filter on
     * @return the list of featured content
     * @throws flex.messaging.io.amf.client.exceptions.ClientStatusException AMF problem occurred
     */
    List<? extends AbstractDTO> getFeatured(FeaturedType type) throws ClientStatusException;

    /**
     * Returns one featured content item for space, channel and presentation (in this order).
     *
     * @return list of featured content
     * @throws flex.messaging.io.amf.client.exceptions.ClientStatusException AMF problem occurred
     */
    List<? extends AbstractDTO> getFeaturedContent() throws ClientStatusException;

    /**
     * Gets public, listed and optionally the private administered spaces for logged in user.
     *
     * @param index  start index.
     * @param paging number of spaces to return form start index.
     * @return overview info about spaces.
     * @throws flex.messaging.io.amf.client.exceptions.ClientStatusException AMF problem occurred
     */
    FilteredOverviewResponseDTO<SpaceOverviewDTO> getSpacesOverview(int index, int paging) throws ClientStatusException;

    /**
     * Gets overview for the particular space.
     *
     * @param spaceId An identifier for target space.
     * @return An overview for the particular space.
     * @throws AuthorizationException User is not authorized
     * @throws flex.messaging.io.amf.client.exceptions.ClientStatusException thrown when AMF problem occurs
     */
    SpaceOverviewDTO getSpaceOverviewDTO(long spaceId) throws AuthorizationException, ClientStatusException;

    /**
     * Returns overview info about channels for particular space.
     * Returns 0 for starting Index and the same for paging.
     *
     * @param spaceId Is an identifier of space to get channels.
     * @return overview     info about channels for particular space.
     * @throws com.parleys.server.security.AuthorizationException  User is not authorized
     * @throws com.parleys.server.service.exception.ParleysServiceException internal error
     * @throws flex.messaging.io.amf.client.exceptions.ClientStatusException AMF problem
     */
    FilteredOverviewResponseDTO<ChannelOverviewDTO> getChannelsOverview(long spaceId)
            throws ParleysServiceException, AuthorizationException, ClientStatusException;

    /**
     * Returns channel overview object for the particular channel.
     *
     * @param channelId An identifier for the target channel/
     * @return channel overview object for the particular channel.
     * @throws AuthorizationException User is not authorized
     * @throws flex.messaging.io.amf.client.exceptions.ClientStatusException AMF problem occurred
     */
    ChannelOverviewDTO getChannelOverviewDTO(long channelId) throws AuthorizationException, ClientStatusException;


    /**
     * Return a complete presentation, used when a viewer wants to see a presentation.
     *
     * @param presentationId the presentation identifier
     * @return the complete presentation info set
     * @throws AuthorizationException user has no authorization to call this method
     * @throws flex.messaging.io.amf.client.exceptions.ClientStatusException AMF problem occurred
     */
    ExtendedPresentationDetailsDTO getPresentationDetails(long presentationId)
            throws AuthorizationException, ClientStatusException;

    /**
     * Returns list with short info about presentations for particular channel.
     *
     * @param criteria The presentation criteria
     * @return overview info about presentations for particular channel.
     * @throws com.parleys.server.service.exception.ParleysServiceException
     *                                when something went wrong
     * @throws AuthorizationException User is not authorized
     * @throws flex.messaging.io.amf.client.exceptions.ClientStatusException AMF problem occurred
     */
    List<? extends AbstractDTO> getPresentationsOverview(PresentationsCriteria criteria)
                throws ParleysServiceException, AuthorizationException, ClientStatusException;

    /**
     * Returns the latest presentations limited by index and paging.
     *
     * @param criteria the presentation criteria
     * @return the list of last published presentations limited by index/paging
     * @throws flex.messaging.io.amf.client.exceptions.ClientStatusException AMF problem occurred
     */
    List<? extends AbstractDTO> getLatestPresentationsOverview(PresentationsCriteria criteria)
            throws ClientStatusException;

    /**
     * Returns the top rated presentations limited by index and paging.
     *
     * @param criteria the presentation criteria
     * @return the list of top rated presentations limited by index/paging
     * @throws flex.messaging.io.amf.client.exceptions.ClientStatusException AMF problem occurred
     */
    List<? extends AbstractDTO> getTopRatedPresentationsOverview(PresentationsCriteria criteria)
            throws ClientStatusException;

    /**
     * Returns the most viewed presentations limited by index and paging.
     *
     * @param criteria the presentation criteria
     * @return the list of most viewed presentations limited by index/paging
     * @throws flex.messaging.io.amf.client.exceptions.ClientStatusException AMF problem occurred
     */
    List<? extends AbstractDTO> getMostViewedPresentationsOverview(PresentationsCriteria criteria)
            throws ClientStatusException;

    /**
     * Return news for the given type. The id is not needed for GENERAL news types.
     *
     * @param newsType the requested news type
     * @param id       the identifier of the space or channel
     * @param index    the starting index
     * @param paging   the paging value
     * @return a list of news items
     * @throws AuthorizationException thrown when parent space is private and user has no permissions
     * @throws flex.messaging.io.amf.client.exceptions.ClientStatusException AMF problem occurred
     */
    OverviewResponseDTO<News> getNews(NewsType newsType, long id, int index, int paging)
            throws AuthorizationException, ClientStatusException;

    // TODO
    List<PresentationOverviewDTO> search(String criteria);
}
