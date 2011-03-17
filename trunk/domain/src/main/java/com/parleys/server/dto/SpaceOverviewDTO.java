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

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Stephan Janssen
 */
public class SpaceOverviewDTO extends AbstractDTO {

    private static final long serialVersionUID = -1835238806441418000L;

    private String name;

    private int totalChannelCount;

    private long totalPresentationCount;

    /**
     * Need to add to the Space entity?
     */
    private String thumbnailURL;

    private String description;

    private String keywords;

    /**
     * Need to add to the Space entity?
     */
    private long totalViewCount;

    private boolean isPublicSpace;

    private List<Long> channelIds = new ArrayList<Long>();

    private List<Long> streamingServerIDs = new ArrayList<Long>();

    private List<String> keywordList = new ArrayList<String>();

    @JsonProperty
    public boolean getVisibleOnIpad() {
        return visibleOnIpad;
    }

    public void setVisibleOnIpad(boolean visibleOnIpad) {
        this.visibleOnIpad = visibleOnIpad;
    }

    private boolean visibleOnIpad;

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @JsonProperty
    public int getTotalChannelCount() {
        return totalChannelCount;
    }

    public void setTotalChannelCount(final int totalChannelCount) {
        this.totalChannelCount = totalChannelCount;
    }

    @JsonProperty
    public long getTotalPresentationCount() {
        return totalPresentationCount;
    }

    public void setTotalPresentationCount(final long totalPresentationCount) {
        this.totalPresentationCount = totalPresentationCount;
    }
    
    @JsonProperty
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @JsonProperty
    public long getTotalViewCount() {
        return totalViewCount;
    }

    public void setTotalViewCount(final long totalViewCount) {
        this.totalViewCount = totalViewCount;
    }

    /**
     * @return the channelIds
     */
    @JsonProperty
    public final List<Long> getChannelIds() {
        return channelIds;
    }

    @JsonIgnore
    public int getNumberOfChannels() {
        return channelIds.size();
    }

    /**
     * @param channelIds the channelIds to set
     */
    public final void setChannelIds(final List<Long> channelIds) {
        this.channelIds = channelIds;
    }

    public final void addChannelId(final Long channelId) {
        channelIds.add(channelId);
    }

    /**
     * @return the isPublicSpace
     */
    @JsonProperty
    public final boolean isPublicSpace() {
        return isPublicSpace;
    }

    /**
     * @param isPublicSpace the isPublicSpace to set
     */
    public final void setPublicSpace(final boolean isPublicSpace) {
        this.isPublicSpace = isPublicSpace;
    }

    @JsonProperty
    public List<Long> getStreamingServerIDs() {
        return streamingServerIDs;
    }

    public void setStreamingServerIDs(final List<Long> streamingServerIDs) {
        this.streamingServerIDs = streamingServerIDs;
    }

    @JsonProperty
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(final String keywords) {
        this.keywords = keywords;
    }

    @JsonProperty
    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(final String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    /**
     * @return the keywordList
     */
    @JsonProperty
    public final List<String> getKeywordList() {
        return keywordList;
    }

    /**
     * @param keywordList the keywordList to set
     */
    public final void setKeywordList(final List<String> keywordList) {
        this.keywordList = keywordList;
    }
}
