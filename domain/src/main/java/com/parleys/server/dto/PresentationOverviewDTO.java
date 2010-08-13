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
package com.parleys.server.dto;

import com.parleys.server.domain.BackgroundImage;
import com.parleys.server.domain.types.PresentationPayment;
import com.parleys.server.domain.types.PresentationState;
import com.parleys.server.domain.types.PresentationType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stephan Janssen
 */
public class PresentationOverviewDTO extends AbstractDTO {

    private static final long serialVersionUID = 1240594852217982760L;

    private String title;

    private String summary;

    /**
     * Need to add to the Space entity?
     */
    private String thumbnailURL;

    private BackgroundImage backgroundImage = new BackgroundImage();

    private int pageId;

    private long totalComments;

    /**
     * Need to add to the Space entity?
     */
    private int totalViews;

    private long totalVotes;

    /**
     * Need to add to the Space entity?
     */
    private long totalDownloads;

    private double totalVoteValue;

    private float totalDuration;

    private List<TagDTO> tags;

    private String mp3URL;

    private List<SpeakerDTO> speakers;

    private PresentationType type;

    private boolean isDownloadable;

    private boolean featured;

    private long totalNotesCount;

    private long totalAssetsCount;

    private Integer sequence;

    private PresentationState state = PresentationState.STAGING;

    private boolean isIphoneEnabled = false;

    private boolean isPodcastEnabled = false;

    private List<String> keywordList = new ArrayList<String>();

    // When the related channel has Subscription or Pay Per Talk enabled
    // then we can use this property to identify if its a free presentation or not.
    private PresentationPayment payment = PresentationPayment.NOT_FREE;

    // If presentation is not free and channel has a subscription or pay per talk enabled then
    // we'll use this boolean to identify if the current user has already paid for this presentation.
    // We need this info here when people use a direct (deep) link to the presentation.
    private boolean paid = false;

    private boolean hostedInFreeChannel = false;

    private boolean hostedInPublicChannel = false;

    private boolean hostedInPublicSpace = false;

    private Long channelId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public float getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(float totalDuration) {
        this.totalDuration = totalDuration;
    }

    public long getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(long totalComments) {
        this.totalComments = totalComments;
    }

    public int getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(int totalViews) {
        this.totalViews = totalViews;
    }

    public long getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(long totalVotes) {
        this.totalVotes = totalVotes;
    }

    public double getTotalVoteValue() {
        return totalVoteValue;
    }

    public void setTotalVoteValue(double totalVoteValue) {
        this.totalVoteValue = totalVoteValue;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagDTO> tags) {
        this.tags = tags;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getMp3URL() {
        return mp3URL;
    }

    public void setMp3URL(String mp3URL) {
        this.mp3URL = mp3URL;
    }

    public List<SpeakerDTO> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<SpeakerDTO> speakers) {
        this.speakers = speakers;
    }

    public PresentationType getType() {
        return type;
    }

    public void setType(PresentationType type) {
        this.type = type;
    }

    public long getTotalDownloads() {
        return totalDownloads;
    }

    public void setTotalDownloads(long totalDownloads) {
        this.totalDownloads = totalDownloads;
    }

    /**
     * Is presentation downloadable or not.
     *
     * @return Is presentation downloadable or not.
     */
    public boolean isDownloadable() {
        return isDownloadable;
    }

    /**
     * Is presentation downloadable or not.
     *
     * @param isDownloadable Is presentation downloadable or not.
     */
    public void setDownloadable(boolean isDownloadable) {
        this.isDownloadable = isDownloadable;
    }

    /**
     * @param featured the featured to set
     */
    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    /**
     * @return the featured
     */
    public boolean isFeatured() {
        return featured;
    }

    /**
     * @return the totalNotesCount
     */
    public final long getTotalNotesCount() {
        return totalNotesCount;
    }

    /**
     * @param totalNotesCount the totalNotesCount to set
     */
    public final void setTotalNotesCount(long totalNotesCount) {
        this.totalNotesCount = totalNotesCount;
    }

    /**
     * @return the totalAssetsCount
     */
    public final long getTotalAssetsCount() {
        return totalAssetsCount;
    }

    /**
     * @param totalAssetsCount the totalAssetsCount to set
     */
    public final void setTotalAssetsCount(long totalAssetsCount) {
        this.totalAssetsCount = totalAssetsCount;
    }

    /**
     * @return the sequence
     */
    public final Integer getSequence() {
        return sequence;
    }

    /**
     * @param sequence the sequence to set
     */
    public final void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * @return the state
     */
    public final PresentationState getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public final void setState(PresentationState state) {
        this.state = state;
    }

    /**
     * @param backgroundImage the backgroundImage to set
     */
    public void setBackgroundImage(BackgroundImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * @return the backgroundImage
     */
    public BackgroundImage getBackgroundImage() {
        return backgroundImage;
    }

    public boolean isIphoneEnabled() {
        return isIphoneEnabled;
    }

    public void setIphoneEnabled(boolean iphoneEnabled) {
        isIphoneEnabled = iphoneEnabled;
    }

    public boolean isPodcastEnabled() {
        return isPodcastEnabled;
    }

    public void setPodcastEnabled(boolean podcastEnabled) {
        isPodcastEnabled = podcastEnabled;
    }

    /**
     * @return the keywordList
     */
    public final List<String> getKeywordList() {
        return keywordList;
    }

    public final String getKeywordsString() {
        String result = "";
        if ((keywordList != null) && (keywordList.size() > 0)) {
            for (String keyword : keywordList) {
                result += result.length() > 0 ? "," : "";
                result += keyword;
            }
        }
        return result;
    }

    /**
     * @param keywordList the keywordList to set
     */
    public final void setKeywordList(List<String> keywordList) {
        this.keywordList = keywordList;
    }

    public PresentationPayment getPayment() {
        return payment;
    }

    public void setPayment(PresentationPayment payment) {
        this.payment = payment;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isHostedInFreeChannel() {
        return hostedInFreeChannel;
    }

    public void setHostedInFreeChannel(boolean hostedInFreeChannel) {
        this.hostedInFreeChannel = hostedInFreeChannel;
    }

    public boolean isHostedInPublicChannel() {
        return hostedInPublicChannel;
    }

    public void setHostedInPublicChannel(boolean hostedInPublicChannel) {
        this.hostedInPublicChannel = hostedInPublicChannel;
    }

    public boolean isHostedInPublicSpace() {
        return hostedInPublicSpace;
    }

    public void setHostedInPublicSpace(boolean hostedInPublicSpace) {
        this.hostedInPublicSpace = hostedInPublicSpace;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }
}
