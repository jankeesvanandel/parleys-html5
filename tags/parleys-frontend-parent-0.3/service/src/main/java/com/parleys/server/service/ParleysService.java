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
package com.parleys.server.service;

import com.parleys.server.domain.News;
import com.parleys.server.domain.types.FavoritesType;
import com.parleys.server.domain.types.SpaceSort;
import com.parleys.server.dto.AbstractDTO;
import com.parleys.server.dto.ChannelOverviewDTO;
import com.parleys.server.dto.ExtendedPresentationDetailsDTO;
import com.parleys.server.dto.FilteredOverviewResponseDTO;
import com.parleys.server.dto.OverviewResponseDTO;
import com.parleys.server.dto.PresentationOverviewDTO;
import com.parleys.server.dto.SpaceOverviewDTO;

import java.util.List;

/**
 * This is the public Parleys service used by the different Parleys "Players".
 *
 * @author Stephan Janssen
 */
public interface ParleysService {

//    /**
//     * This method allows the current authenticated user to add a favorite space, channel or presentation
//     * to his/her favorite list.
//     *
//     * @param typeId the typeId would be the id of either space, channel or presentation
//     * @param type   defining the type (space, channel or presentation)
//     */
//    void addToFavorites(long typeId, FavoritesType type);
//
//    /**
//     * This method allows the current authenticated user to remove a favorite space, channel or presentation
//     * from his/her favorite list.
//     *
//     * @param typeId the typeId would be the id of either space, channel or presentation
//     * @param type   defining the type (space, channel or presentation)
//     */
//    void removeFromFavorites(long typeId, FavoritesType type);

    /**
     * Retrieves the favorites for the current authenticated user and type.
     *
     * @param type defining the type (space, channel or presentation)
     * @return list of either Space, Channel or Presentation Overview DTO's
     */
    List<? extends AbstractDTO> getFavorites(FavoritesType type);

//    /**
//     * This method allows the authenticated user to add a space, channel or presentation watch.
//     *
//     * @param typeId the typeId would be the id of either space, channel or presentation
//     * @param type   defining the watch type (space, channel or presentation)
//     */
//    void addWatch(long typeId, WatchType type);
//
//    /**
//     * This method allows the current authenticated user to remove a space, channel or presentation watch.
//     *
//     * @param typeId the typeId would be the id of either space, channel or presentation
//     * @param type   defining the type (space, channel or presentation)
//     */
//    void removeWatch(long typeId, WatchType type);
//
//    /**
//     * Retrieves the watches for the current authenticated user and type.
//     *
//     * @param type defining the watch type (space, channel or presentation)
//     * @return list of either Space, Channel or Presentation Overview DTO's
//     */
//    List<? extends AbstractDTO> getWatches(WatchType type);
//

    /**
     * Return news for the given type. The id is not needed for GENERAL news types.
     *
     * @param newsType the requested news type
     * @param id       the identifier of the space or channel
     * @param index    the starting index
     * @param paging   the paging value
     * @return a list of news items
     */
    OverviewResponseDTO<News> getNews(String newsType, long id, int index, int paging);

//    /**
//     * Return the comments for the given presentation id.
//     *
//     * @param presentationId the presentation identifier
//     * @return a list of comments
//     * @throws AuthorizationException user has no authorization to call this method
//     */
//    List<CommentDTO> getCommentOverviews(long presentationId) throws AuthorizationException;

    /**
     * Return a complete presentation, used when a viewer wants to see a presentation.
     *
     * @param presentationId the presentation identifier
     * @return the complete presentation info set
     */
    ExtendedPresentationDetailsDTO getPresentationDetails(long presentationId);

//    /**
//     * Has the current logged in user already voted for the given presentation ?
//     *
//     * @param presentationId the presentation identifier
//     * @return true if the userId has already voted for this talk
//     */
//    boolean hasVoted(long presentationId);
//
//    /**
//     * Set a rating value for the  given presentation for current logged in user.
//     *
//     * @param presentationId the presentation identifier
//     * @param rateValue      the rate value
//     * @return the actual vote value.
//     * @throws java.io.IOException IO error occured
//     */
//    double setVote(long presentationId, VoteType rateValue) throws IOException;
//
//    /**
//     * Create or update a comment.
//     * Authentication is required.
//     *
//     * @param presentationId the presentation identifier
//     * @param comment        the updated or new comment
//     * @return comment identifier
//     * @throws IOException             IO error occured
//     * @throws ParleysServiceException Internal error occured
//     */
//    long setComment( long presentationId, CommentDTO comment) throws IOException, ParleysServiceException;
//
//    /**
//     * Allow an authenticated user to increment the comment spam counter.
//     *
//     * @param presentationId the related presentation
//     * @param commentId      the related comment identifier
//     * @return the total spam votes
//     * @throws AuthorizationException
//     */
//    int increaseCommentSpamCount(long presentationId, long commentId) throws AuthorizationException;
//
//    /**
//     * Create or update a tag.
//     * Authentication is required.
//     *
//     * @param presentationId the presentation identifier
//     * @param tag            the updated or new tag
//     * @throws IOException             IO error occured
//     * @throws ParleysServiceException Internal error occured
//     */
//    void setTag( long presentationId, TagDetailsDTO tag) throws IOException, ParleysServiceException;
//
//    /**
//     * Create or update a note.
//     * Authentication is required.
//     *
//     * @param presentationId the presentation identifier
//     * @param note           the updated or new note
//     * @throws IOException             IO error occured
//     * @throws ParleysServiceException Internal error occured
//     */
//    void setNote( long presentationId, Note note) throws IOException, ParleysServiceException;

    /**
     * Gets public, listed and optionally the private administered spaces for logged in user.
     *
     * @param index  start index.
     * @param paging number of spaces to return form start index.
     * @return overview info about spaces.
     */
    FilteredOverviewResponseDTO<SpaceOverviewDTO> getSpacesOverview(int index, int paging);

//    /**
//     * Gets public, listed and optionally the private administered spaces for logged in user as bread crumbs.
//     *
//     * @param index  start index.
//     * @param paging number of spaces to return form start index.
//     * @return overview info about spaces.
//     */
//    FilteredOverviewResponseDTO<BreadCrumbDTO> getBreadCrumbSpaces(int index, int paging);

    /**
     * Gets public, listed and optionally the private administered spaces for logged in user.
     *
     * @param index  start index.
     * @param paging number of spaces to return form start index.
     * @param sortBy sort spaces by
     * @return overview info about spaces.
     */
    FilteredOverviewResponseDTO<SpaceOverviewDTO> getSpacesOverview(int index, int paging, SpaceSort sortBy);

    /**
     * Gets public spaces that include iphone enabled channels.
     *
     * @param index  start index.
     * @param paging number of spaces to return form start index.
     * @return overview info about spaces.
     */
//    FilteredOverviewResponseDTO<SpaceOverviewDTO> getIPhoneSpacesOverview(int index, int paging);

    /**
     * Gets overview for the particular space.
     *
     * @param spaceId An identifier for target space.
     * @return An overview for the particular space.
     */
    SpaceOverviewDTO getSpaceOverviewDTO(long spaceId);

    /**
     * Returns overview info about channels for particular space.
     * It is returns 0 for startingIndex and the same for paging.
     *
     * @param spaceId Is an identifier of space to get channels.
     * @return overview     info about channels for particular space.
     */
    FilteredOverviewResponseDTO<ChannelOverviewDTO> getChannelsOverview(long spaceId);

//    /**
//     * Returns overview info about channels for particular space.
//     *
//     * @param index   start index.
//     * @param paging  number of channels to return form start index.
//     * @param spaceId Is an identifier of space to get channels.
//     * @return overview info about channels for particular space.
//     * @throws com.parleys.server.common.exception.ParleysServiceException
//     *                                paging was too high
//     * @throws AuthorizationException User is not authorized
//     */
//    FilteredOverviewResponseDTO<ChannelOverviewDTO> getChannelsOverview(long spaceId, int index, int paging)
//            throws ParleysServiceException, AuthorizationException;
//
//    /**
//     * Returns lightweight breadcrumb dto used by the player breadcrumb drop-down list.
//     *
//     * @param index   start index.
//     * @param paging  number of channels to return form start index.
//     * @param spaceId Is an identifier of space to get channels.
//     * @return overview info about channels for particular space.
//     * @throws com.parleys.server.common.exception.ParleysServiceException
//     *                                paging was too high
//     * @throws AuthorizationException User is not authorized
//     */
//    FilteredOverviewResponseDTO<BreadCrumbDTO> getBreadCrumbChannels(long spaceId, int index, int paging)
//            throws ParleysServiceException, AuthorizationException;
//
//    /**
//     * Returns overview info about channels for particular space.
//     *
//     * @param index   start index.
//     * @param paging  number of channels to return form start index.
//     * @param sortBy  sort channels by...
//     * @param spaceId Is an identifier of space to get channels.
//     * @return overview info about channels for particular space.
//     * @throws com.parleys.server.common.exception.ParleysServiceException
//     *                                paging was too high
//     * @throws AuthorizationException User is not authorized
//     */
//    FilteredOverviewResponseDTO<ChannelOverviewDTO> getChannelsOverview(long spaceId,
//                                                                        int index,
//                                                                        int paging,
//                                                                        ChannelSort sortBy)
//            throws ParleysServiceException, AuthorizationException;

    /**
     * Returns channel overview object for the particular channel.
     *
     * @param channelId An identifier for the target channel/
     * @return channel overview object for the particular channel.
     */
    ChannelOverviewDTO getChannelOverviewDTO(long channelId);

//    /**
//     * Returns list with breadcrum presentations dtos for particular channel.
//     *
//     * @param channelId Is an identifier of channel to get presentations.
//     * @param index     start index.
//     * @param paging    number of presentations to return form start index.
//     * @param type      the type of presentation needed
//     * @param sort      sort by
//     * @param date      and date region (TODAY, THIS WEEK, ...)
//     * @return overview info about presentations for particular channel.
//     * @throws com.parleys.server.common.exception.ParleysServiceException
//     *                                when something went wrong
//     * @throws AuthorizationException User is not authorized
//     *                                <p/>
//     * TODO Change misspelled method getBreadCrumPresentations to getBreadCrumbPresentations with a 'b'
//     */
//    FilteredOverviewResponseDTO<BreadCrumbDTO> getBreadCrumPresentations(long channelId,
//                                                                         int index, int paging,
//                                                                         PresentationType type,
//                                                                         PresentationSort sort,
//                                                                         PresentationDateRange date)
//            throws ParleysServiceException, AuthorizationException;

    /**
     * Returns list with short info about presentations for particular channel.
     *
     * @param channelId Is an identifier of channel to get presentations.
     * @param index     start index.
     * @param paging    number of presentations to return form start index.
     * @param type      the type of presentation needed
     * @param sort      sort by
     * @param date      and date region (TODAY, THIS WEEK, ...)
     * @return overview info about presentations for particular channel.
     * @throws com.parleys.server.common.exception.ParleysServiceException
     *                                when something went wrong
     */
    FilteredOverviewResponseDTO<PresentationOverviewDTO> getPresentationsOverview(
            long channelId, int index, int paging, String type, String sort, String date);

//    /**
//     * Returns list with short info about presentations for particular channel.
//     *
//     * @param channelId Is an identifier of channel to get presentations.
//     * @param index     start index.
//     * @param paging    number of presentations to return form start index.
//     * @param type      the type of presentation needed
//     * @param sort      sort by
//     * @param date      and date region (TODAY, THIS WEEK, ...)
//     * @param filter    Search string to filter (search on presentation title, summary and description, tag name
//     *                  and speaker's bio, first name and last name)
//     * @return overview info about presentations for particular channel.
//     * @throws com.parleys.server.common.exception.ParleysServiceException
//     *                                when something went wrong
//     * @throws AuthorizationException User is not authorized
//     */
//    FilteredOverviewResponseDTO<PresentationOverviewDTO> getPresentationsOverview(long channelId,
//                                                                                  int index, int paging,
//                                                                                  PresentationType type,
//                                                                                  PresentationSort sort,
//                                                                                  PresentationDateRange date,
//                                                                                  String filter)
//            throws ParleysServiceException, AuthorizationException;
//
//    /**
//     * Returns list with short info about presentations for particular channel.
//     *
//     * @param channelId Is an identifier of channel to get presentations.
//     * @param index     start index.
//     * @param paging    number of presentations to return form start index.
//     * @param type      the type of presentation needed
//     * @param sort      sort by
//     * @param date      and date region (TODAY, THIS WEEK, ...)
//     * @param filter    Search string to filter (search on presentation title, summary and description, tag name
//     *                  and speaker's bio, first name and last name)
//     * @param target    the target presentation device
//     * @return overview info about presentations for particular channel.
//     * @throws com.parleys.server.common.exception.ParleysServiceException
//     *                                when something went wrong
//     * @throws AuthorizationException User is not authorized
//     */
//    FilteredOverviewResponseDTO<PresentationOverviewDTO> getPresentationsOverview(long channelId,
//                                                                                  int index, int paging,
//                                                                                  PresentationType type,
//                                                                                  PresentationSort sort,
//                                                                                  PresentationDateRange date,
//                                                                                  String filter,
//                                                                                  PresentationTargetDevice target)
//            throws ParleysServiceException, AuthorizationException;
//
//    /**
//     * Returns list with short info about presentations for particular channel.
//     *
//     * @param channelId Is an identifier of channel to get presentations.
//     * @param index     start index.
//     * @param paging    number of presentations to return form start index.
//     * @param state     Is set of presentation states. If null PUBLIC state used. It is set of strings with
//                        states enum names.
//     * @param type      the type of presentation needed
//     * @param sort      sort by
//     * @param date      and date region (TODAY, THIS WEEK, ...)
//     * @param filter    Search string to filter (search on presentation title, summary and description, tag name
//     *                  and speaker's bio, first name and last name)
//     * @return overview info about presentations for particular channel.
//     * @throws ParleysServiceException when something went wrong
//     * @throws AuthorizationException  User is not authorized
//     */
//    FilteredOverviewResponseDTO<PresentationOverviewDTO> getPresentationsOverview(long channelId,
//                                                                                  int index, int paging,
//                                                                                  List<String> state,
//                                                                                  PresentationType type,
//                                                                                  PresentationSort sort,
//                                                                                  PresentationDateRange date,
//                                                                                  String filter)
//            throws ParleysServiceException, AuthorizationException;
//
//    /**
//     * Retrieves all the tags of all presentations within the given channel.
//     *
//     * @param channelId Channel to retrieve tags from.
//     * @return all the tags of all presentations within the given channel.
//     * @throws AuthorizationException User is not authorized
//     */
//    List<TagDTO> getAllTagDTOByChannel(Long channelId) throws AuthorizationException;
//
//    /**
//     * Returns the list of tags by particular user.
//     *
//     * @param userId Is the target user.
//     * @return Tags list grouped by presentation and user.
//     */
//    List<UserAnnotationsForPresentationDTO<TagDTO>> getUserTags( Long userId);
//
//    /**
//     * Returns the list of tags by current logged in user.
//     *
//     * @return Tags list grouped by presentation and user.
//     */
//    List<UserAnnotationsForPresentationDTO<TagDTO>> getUserTags();
//
//    /**
//     * Returns the list of comments by particular user.
//     *
//     * @param userId Is the target user.
//     * @return Comment list grouped by presentation and user.
//     */
//    List<UserAnnotationsForPresentationDTO<CommentDTO>> getUserComments( Long userId);
//
//    /**
//     * Returns the list of comments by current logged inuser.
//     *
//     * @return Comment list grouped by presentation and user.
//     */
//    List<UserAnnotationsForPresentationDTO<CommentDTO>> getUserComments();
//
//    /**
//     * Returns tag cloud for channel.
//     *
//     * @param channelId Channel to retrieve tags from.
//     * @return tag cloud for channel.
//     * @throws AuthorizationException User is not authorized
//     */
//    List<TagValueDTO> getTagCloudByChannel(Long channelId) throws AuthorizationException;
//
//    /**
//     * Returns tag cloud for space.
//     *
//     * @param spaceId Space to retrieve tags from.
//     * @return tag cloud for space.
//     * @throws AuthorizationException User is not authorized
//     */
//    List<TagValueDTO> getTagCloudBySpace(Long spaceId) throws AuthorizationException;
//
//    /**
//     * Returns tag cloud for the current user.
//     *
//     * @return tag cloud for user.
//     */
//    List<TagValueDTO> getTagCloudByUser();
//
//    /**
//     * Returns channel keyword cloud for given space.
//     *
//     * @param spaceId Space to retrieve channel keywords
//     * @param size    number of tags to return
//     * @return channel  keyword cloud for space
//     * @throws AuthorizationException User is not authorized
//     * @throws java.io.IOException    thrown when related lucene index is missing
//     */
//    List<TagValueDTO> getKeywordCloudBySpace(Long spaceId, int size) throws AuthorizationException, IOException;
//
//    /**
//     * Returns presentation keyword cloud for given channel.
//     *
//     * @param channelId Channel to retrive presentation keywords
//     * @param size      number of tags to return
//     * @return presentation keyword cloud for channel
//     * @throws AuthorizationException User is not authorized
//     * @throws java.io.IOException    thrown when related lucene index is missing
//     */
//    List<TagValueDTO> getKeywordCloudByChannel(Long channelId, int size) throws AuthorizationException, IOException;

    /**
     * Returns the list of featured content based on the selected type.
     *
     * @param type the feature type we want to filter on
     * @return the list of featured content
     */
    List<? extends AbstractDTO> getFeatured(String type);

//    OverviewResponseDTO<? extends AbstractDTO> getFeatured(FeaturedType type, int index, int paging);

    /**
     * Returns one featured content item for space, channel and presentation (in this order).
     * This method will replace @see getFeatured(FeaturedType type).
     *
     * @return list of featured content
     */
    List<? extends AbstractDTO> getFeaturedContent();

    /**
     * Returns the list of top rated presentations limited by parameter.
     *
     * @param index  the starting index
     * @param paging the paging value
     * @return the list of top rated presentations
     */
    OverviewResponseDTO<PresentationOverviewDTO> getTopRatedPresentationsOverview(int index, int paging);

    /**
     * Returns the last presentations made public using index and paging.
     *
     * @param index  the starting index
     * @param paging the paging value
     * @return the list of the last presentations made public available
     */
    OverviewResponseDTO<PresentationOverviewDTO> getLatestPresentationsOverview(int index, int paging);

//    /**
//     * The most downloaded presentations.
//     *
//     * @param index  start index
//     * @param paging number of elements
//     * @return list of most downloaded presentations
//     */
//    OverviewResponseDTO<PresentationOverviewDTO> getMostDownloadedPresentationsOverview(int index, int paging);

    /**
     * Returns a list of most viewed presentations.
     *
     * @param index  start index
     * @param paging paging value
     * @return paged list of most viewed public presentations
     */
    OverviewResponseDTO<PresentationOverviewDTO> getMostViewedPresentationsOverview(int index, int paging);

//
//    /**
//     * Gets the list of channels where current user has rights to publish to.
//     *
//     * @return List of channels where current user has rights to publish to
//     */
//    List<ChannelOverviewDTO> getAvailableChannelsOverviews();
//
//    /**
//     * Returns average vote value for the particular presentation.
//     *
//     * @param presentationId Target presentation id.
//     * @return Average vote.
//     * @throws AuthorizationException User is not authorized
//     */
//    double getAverageVote(Long presentationId) throws AuthorizationException;
//
//    /**
//     * Removes tag.
//     *
//     * @param tagId Is tag to remove.
//     * @throws AuthorizationException thrown when user is not authorized
//     */
//    void removeTag( Long tagId) throws AuthorizationException;
//
//    /**
//     * Removes comment.
//     *
//     * @param commentId Is comment to remove.
//     * @throws AuthorizationException thrown when user is not authorized
//     */
//    void removeComment( Long commentId) throws AuthorizationException;
//
//    /**
//     * Search the lucene indexes, this can also be used for finding related talks.
//     *
//     * @param type         the search scope
//     * @param searchText   the text to search for
//     * @param startIndex   start index
//     * @param resultsCount number of elements to return
//     * @return search results
//     */
//    SearchResults search(SearchScope type, String searchText, int startIndex, int resultsCount);
//
//    /**
//     * Search the lucene indexes for spaces.
//     *
//     * @param searchText   the text to search for
//     * @param startIndex   start index
//     * @param resultsCount number of elements to return
//     * @return search results
//     */
//    OverviewResponseDTO<SpaceOverviewDTO> searchSpaces(String searchText, int startIndex, int resultsCount);
//
//    /**
//     * Search the lucene indexes for channels.
//     *
//     * @param searchText   the text to search for
//     * @param startIndex   start index
//     * @param resultsCount number of elements to return
//     * @return search results
//     */
//    OverviewResponseDTO<ChannelOverviewDTO> searchChannels(String searchText, int startIndex, int resultsCount);
//
    /**
     * Search the lucene indexes for presentations.
     *
     * @param searchText   the text to search for
     * @param startIndex   start index
     * @param resultsCount number of elements to return
     * @return search results
     */
    OverviewResponseDTO<PresentationOverviewDTO> searchPresentations(String searchText, int startIndex,
                                                                     int resultsCount);
//
//    /**
//     * Search the lucene indexes for presentations but searching only using tags.
//     *
//     * @param searchText   the text to search for
//     * @param startIndex   start index
//     * @param resultsCount number of elements to return
//     * @return search results
//     */
//    OverviewResponseDTO<PresentationOverviewDTO> searchPresentationsByTags(String searchText, int startIndex,
//                                                                           int resultsCount);
//
//    /**
//     * This will increment the presentation download counter triggered by the standalone Parleys client.
//     *
//     * @param presentationId the presentation identifier
//     */
//    void incrementDownloadCount(Long presentationId);
//
//    /**
//     * Returns all the keywords.
//     *
//     * @return The list of all available keywords.
//     */
//    List<String> getAllKeywords();
//
//    /**
//     * Returns spaces contained the same keyword.
//     *
//     * @param keyword      Is keyword to search spaces for (exact match).
//     * @param startIndex   Start index.
//     * @param resultsCount Number of elements to return.
//     * @return Resulting spaces list.
//     */
//    List<SpaceOverviewDTO> getRelatedSpacesByKeyword(String keyword, int startIndex, int resultsCount);
//
//    /**
//     * Returns channels contained the same keyword.
//     *
//     * @param keyword      Is keyword to search channels for (exact match).
//     * @param startIndex   Start index.
//     * @param resultsCount Number of elements to return.
//     * @return Resulting spaces list.
//     */
//    List<ChannelOverviewDTO> getRelatedChannelsByKeyword(String keyword, int startIndex, int resultsCount);
//
      /**
      * Returns presentations contained the same keyword.
      *
      * @param keyword      Is keyword to search presentations for (exact match).
      * @param startIndex   Start index.
      * @param resultsCount Number of elements to return.
      * @return Resulting spaces list.
      */
      List<PresentationOverviewDTO> getRelatedPresentationsByKeyword(String keyword, int startIndex, int resultsCount);

//    /**
//     * Has user paid for this non-free presentation?
//     *
//     * @param presentationId the presentation identifier
//     * @return true if user can view this presentation.
//     */
//    boolean hasCurrentUserPaidForPresentation(Long presentationId);
//
//    /**
//     * Returns the subscribed channels for current user.
//     *
//     * @return list of subscribed channels
//     * @throws com.parleys.server.common.exception.AuthorizationException
//     *          when user is not logged in
//     */
//    List<ChannelSubscriptionDTO> getSubscribedChannels() throws AuthorizationException;
//
//    /**
//     * Returns the list of paid presentations for current user.
//     *
//     * @return list of paid presentations.
//     * @throws com.parleys.server.common.exception.AuthorizationException
//     *          when user is not logged in
//     */
//    List<PresentationOverviewDTO> getPurchasedPresentations() throws AuthorizationException;
//
//    /**
//     * Returns the space home page template.
//     *
//     * @param spaceId the space identifier
//     * @return the related home page template
//     * @throws AuthorizationException thrown when user is not authenticated
//     */
//    TemplateDTO getSpaceTemplate(Long spaceId) throws AuthorizationException;
//
//    /**
//     * Add a home page template for the given space.
//     *
//     * @param spaceId  the space identifier to link the home page template to
//     * @param template the new template
//     * @return identifier when new template was stored
//     * @throws AuthorizationException thrown when user is not authenticated
//     * @throws java.io.IOException    thrown by user domain object
//     */
//    Long storeSpaceTemplate(Long spaceId, TemplateDTO template) throws AuthorizationException, IOException;
//
//    /**
//     * Remove template for the given space.
//     *
//     * @param spaceId the space identifier
//     * @throws AuthorizationException thrown when user is not authenticated
//     * @throws java.io.IOException    thrown when something went wrong with saving user
//     */
//    void removeSpaceTemplate(Long spaceId) throws AuthorizationException, IOException;
//
//    /**
//     * Create a channel in the Sandbox space, used by Rent-A-Space wizard.
//     *
//     * @param name the channel name
//     * @return the new channel id
//     * @throws IOException            when channel was not saved
//     * @throws AuthorizationException when user is not authenticated
//     */
//    Long createSandboxChannel(String name) throws IOException, AuthorizationException;
//
//    /**
//     * Store company information linked to authenticated user and validates VAT number.
//     *
//     * @param companyDTO the company dto
//     * @return the identifier of the newly created company
//     * @throws com.parleys.server.common.exception.AuthorizationException
//     *                             when user is not logged in
//     * @throws java.io.IOException something went wrong when saving user
//     */
//    Long storeCompany(CompanyDTO companyDTO) throws AuthorizationException, IOException;
//
//    /**
//     * Get the company related DTO for the authenticated user.
//     *
//     * @return the company dto
//     * @throws com.parleys.server.common.exception.AuthorizationException
//     *          when user is not logged in
//     */
//    CompanyDTO getCompany() throws AuthorizationException;
//
//    /**
//     * Retrieves list of supported payment merchants which are activated.
//     *
//     * @return list of merchant dtos
//     */
//    List<MerchantDTO> getMerchants();
//
//    /**
//     * Convenient method to check if current user can view the space or channel.
//     * The parleys player needs this method to support the view permissions logic.
//     *
//     * @param id            the space or channel id
//     * @param validateSpace when true validate view rights for space otherwise validate for channel
//     * @return true when user can view this space
//     * @throws AuthorizationException when user is not authenticated
//     */
//    boolean hasUserViewRights(long id, boolean validateSpace) throws AuthorizationException;
//
//    /**
//     * Retrieves lsit of presentation attachments.
//     *
//     * @param presentationId the presentation id
//     * @return list of attachment dtos
//     */
//    List<AttachmentDTO> getAttachments(long presentationId);

    /**
     * Retrieves the user identifier.
     *
     * @return the user identifier
     */
    Long getUserId();
}
