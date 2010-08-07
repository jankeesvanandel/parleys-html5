package com.parleys.server.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * A kind of presentation details with child collections.
 *
 * @author Stephan Janssen
 */
public class ExtendedPresentationDetailsDTO extends PresentationDetailsDTO {

    private static final long serialVersionUID = -5618171137615017646L;

    private boolean isEmbeddablePlayerEnabled = false;

    private ChannelOverviewDTO channel;

    private List<SpeakerDTO> speakers = new ArrayList<SpeakerDTO>();

    private List<AssetDTO> assetDTOs = new ArrayList<AssetDTO>();

    private List<VoteDTO> votes = new ArrayList<VoteDTO>();

    private List<CommentDTO> comments = new ArrayList<CommentDTO>();

    private List<TagDetailsDTO> tags = new ArrayList<TagDetailsDTO>();

    private boolean attachmentsAvailable = false;

    // If presentation is not free and channel has a subscription or pay per talk enabled then
    // we'll use this boolean to identify if the current user has already paid for this presentation.
    // We need this info here when people use a direct (deep) link to the presentation.
    private boolean paid = false;

    /**
     * @return the channel
     */
    public final ChannelOverviewDTO getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public final void setChannel(final ChannelOverviewDTO channel) {
        this.channel = channel;
    }

    /**
     * @return the speakers
     */
    public final List<SpeakerDTO> getSpeakers() {
        return speakers;
    }

    /**
     * @param speakers the speakers to set
     */
    public final void setSpeakers(final List<SpeakerDTO> speakers) {
        this.speakers = speakers;
    }

    /**
     * @return the votes
     */
    public final List<VoteDTO> getVotes() {
        return votes;
    }

    /**
     * @param votes the votes to set
     */
    public final void setVotes(final List<VoteDTO> votes) {
        this.votes = votes;
    }

    /**
     * @return the comments
     */
    public final List<CommentDTO> getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public final void setComments(final List<CommentDTO> comments) {
        this.comments = comments;
    }

    /**
     * @return the tags
     */
    public final List<TagDetailsDTO> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public final void setTags(final List<TagDetailsDTO> tags) {
        this.tags = tags;
    }

    public boolean isEmbeddablePlayerEnabled() {
        return isEmbeddablePlayerEnabled;
    }

    public void setEmbeddablePlayerEnabled(final boolean embeddablePlayerEnabled) {
        isEmbeddablePlayerEnabled = embeddablePlayerEnabled;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(final boolean paid) {
        this.paid = paid;
    }

    public boolean isAttachmentsAvailable() {
        return attachmentsAvailable;
    }

    public void setAttachmentsAvailable(final boolean attachmentsAvailable) {
        this.attachmentsAvailable = attachmentsAvailable;
    }

    /**
     * @param assetDTOs the assetDTOs to set
     */
    public final void setAssetDTOs(final List<AssetDTO> assetDTOs) {
        this.assetDTOs = assetDTOs;
    }

    /**
     * @return the assetDTOs
     */
    public final List<AssetDTO> getAssetDTOs() {
        return assetDTOs;
    }
}
