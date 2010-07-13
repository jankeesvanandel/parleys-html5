package com.parleys.server.frontend.service;

import com.parleys.server.domain.News;
import com.parleys.server.domain.types.*;
import com.parleys.server.dto.*;
import com.parleys.server.frontend.domain.*;
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
public interface ParleysServiceDelegate {

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
    public List<? extends AbstractDTO> getFeaturedContent() throws ClientStatusException;

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
    FilteredOverviewResponseDTO<PresentationOverviewDTO> getPresentationsOverview(PresentationsCriteria criteria)
                throws ParleysServiceException, AuthorizationException, ClientStatusException;

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
