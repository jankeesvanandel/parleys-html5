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
import com.parleys.server.domain.SocialInfo;
import com.parleys.server.domain.types.PresentationState;
import com.parleys.server.domain.types.PresentationType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Representing full presentation details to edit with admin client.
 *
 * @author Stephan Janssen
 */
public class PresentationDetailsDTO extends AbstractDTO {

    private static final long serialVersionUID = 2614509265378596479L;

    private Integer pageId;

    private Integer sequence;

    private String title;

    private String summary;

    private String description;

    private String thumbnailURL;

    private String mp3URL;

    private String streamingURL;

    private String downloadURL;

    private BackgroundImage backgroundImage = new BackgroundImage();

    private Float totalDuration;

    private long totalVotes;

    private double totalVoteValue;

    private int totalViews;

    private int totalDownloads;

    private int totalPodcastDownloads;

    private int totalComments;

    private Date publishedOn;

    private boolean isDownloadable = true;

    private boolean featured;

    private SocialInfo socialInfo = new SocialInfo();

    private PresentationType type = PresentationType.AUDIO_AND_SLIDES;

    private PresentationState state = PresentationState.STAGING;

    private SimpleUserDTO publisher;

    private boolean isIphoneEnabled = false;

    private boolean isPodcastEnabled = true;

    private String ratio = "4:3";

    private String slideRatio = "4:3";

    private List<String> keywordList = new ArrayList<String>();

    private String paymentStatus = "";

    private boolean free;

    /**
     * @return the pageId
     */
    public final Integer getPageId() {
        return pageId;
    }

    /**
     * @param pageId the pageId to set
     */
    public final void setPageId(final Integer pageId) {
        this.pageId = pageId;
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
    public final void setSequence(final Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * @return the title
     */
    public final String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public final void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @return the summary
     */
    public final String getSummary() {
        return summary;
    }

    /**
     * @param summary the summary to set
     */
    public final void setSummary(final String summary) {
        this.summary = summary;
    }

    /**
     * @return the description
     */
    public final String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return the thumbnail
     */
    public final String getThumbnailURL() {
        return thumbnailURL;
    }

    /**
     * @param thumbnailURL the thumbnail to set
     */
    public final void setThumbnailURL(final String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    /**
     * @return the mp3URL
     */
    public final String getMp3URL() {
        return mp3URL;
    }

    /**
     * @param mp3URL the mp3URL to set
     */
    public final void setMp3URL(final String mp3URL) {
        this.mp3URL = mp3URL;
    }

    /**
     * @return the totalDuration
     */
    public final Float getTotalDuration() {
        return totalDuration;
    }

    /**
     * @param totalDuration the totalDuration to set
     */
    public final void setTotalDuration(final Float totalDuration) {
        this.totalDuration = totalDuration;
    }

    /**
     * @return the totalVotes
     */
    public final long getTotalVotes() {
        return totalVotes;
    }

    /**
     * @param totalVotes the totalVotes to set
     */
    public final void setTotalVotes(final long totalVotes) {
        this.totalVotes = totalVotes;
    }

    /**
     * @return the totalVoteValue
     */
    public final double getTotalVoteValue() {
        return totalVoteValue;
    }

    /**
     * @param totalVoteValue the totalVoteValue to set
     */
    public final void setTotalVoteValue(final double totalVoteValue) {
        this.totalVoteValue = totalVoteValue;
    }

    /**
     * @return the totalViews
     */
    public final int getTotalViews() {
        return totalViews;
    }

    /**
     * @param totalViews the totalViews to set
     */
    public final void setTotalViews(final int totalViews) {
        this.totalViews = totalViews;
    }

    /**
     * @return the totalDownloads
     */
    public final int getTotalDownloads() {
        return totalDownloads;
    }

    /**
     * @param totalDownloads the totalDownloads to set
     */
    public final void setTotalDownloads(final int totalDownloads) {
        this.totalDownloads = totalDownloads;
    }

    /**
     * @return the totalComments
     */
    public final int getTotalComments() {
        return totalComments;
    }

    /**
     * @param totalComments the totalComments to set
     */
    public final void setTotalComments(final int totalComments) {
        this.totalComments = totalComments;
    }

    /**
     * @return the publishedOn
     */
    public final Date getPublishedOn() {
        if (publishedOn != null) {
            return (Date) publishedOn.clone();
        } else {
            return null;
        }
    }

    /**
     * @param publishedOn the publishedOn to set
     */
    public final void setPublishedOn(final Date publishedOn) {
        if (publishedOn != null) {
            this.publishedOn = (Date) publishedOn.clone();
        }
    }

    /**
     * @return the isDownloadable
     */
    public final boolean isDownloadable() {
        return isDownloadable;
    }

    /**
     * @param isDownloadable the isDownloadable to set
     */
    public final void setDownloadable(final boolean isDownloadable) {
        this.isDownloadable = isDownloadable;
    }

    /**
     * @return the featured
     */
    public final boolean isFeatured() {
        return featured;
    }

    /**
     * @param featured the featured to set
     */
    public final void setFeatured(final boolean featured) {
        this.featured = featured;
    }

    /**
     * @return the socialInfo
     */
    public final SocialInfo getSocialInfo() {
        return socialInfo;
    }

    /**
     * @param socialInfo the socialInfo to set
     */
    public final void setSocialInfo(final SocialInfo socialInfo) {
        this.socialInfo = socialInfo;
    }

    /**
     * @return the type
     */
    public final PresentationType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public final void setType(final PresentationType type) {
        this.type = type;
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
    public final void setState(final PresentationState state) {
        this.state = state;
    }

    /**
     * @return the publisher
     */
    public final SimpleUserDTO getPublisher() {
        return publisher;
    }

    /**
     * @param publisher the publisher to set
     */
    public final void setPublisher(final SimpleUserDTO publisher) {
        this.publisher = publisher;
    }

    public String getStreamingURL() {
        return streamingURL;
    }

    public void setStreamingURL(final String streamingURL) {
        this.streamingURL = streamingURL;
    }

    /**
     * @param backgroundImage the backgroundImage to set
     */
//    public void setBackgroundImage(BackgroundImage backgroundImage) {
//        this.backgroundImage = backgroundImage;
//    }

    /**
     * @return the backgroundImage
     */
    public BackgroundImage getBackgroundImage() {
        return backgroundImage;
    }

    public boolean isPodcastEnabled() {
        return isPodcastEnabled;
    }

    public void setPodcastEnabled(final boolean podcastEnabled) {
        isPodcastEnabled = podcastEnabled;
    }

    public boolean isIphoneEnabled() {
        return isIphoneEnabled;
    }

    public void setIphoneEnabled(final boolean iphoneEnabled) {
        isIphoneEnabled = iphoneEnabled;
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
    public final void setKeywordList(final List<String> keywordList) {
        this.keywordList = keywordList;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(final String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(final String ratio) {
        this.ratio = ratio;
    }

    /**
     * @return the free
     */
    public final boolean isFree() {
        return free;
    }

    /**
     * @param free the free to set
     */
    public final void setFree(final boolean free) {
        this.free = free;
    }

    public String getSlideRatio() {
        return slideRatio;
    }

    public void setSlideRatio(final String slideRatio) {
        this.slideRatio = slideRatio;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(final String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public int getTotalPodcastDownloads() {
        return totalPodcastDownloads;
    }

    public void setTotalPodcastDownloads(final int totalPodcastDownloads) {
        this.totalPodcastDownloads = totalPodcastDownloads;
    }
}
