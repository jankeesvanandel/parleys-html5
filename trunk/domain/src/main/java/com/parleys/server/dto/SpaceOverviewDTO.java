package com.parleys.server.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stephan Janssen
 */
public class SpaceOverviewDTO extends AbstractDTO {

    /**
     *
     */
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

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getTotalChannelCount() {
        return totalChannelCount;
    }

    public void setTotalChannelCount(final int totalChannelCount) {
        this.totalChannelCount = totalChannelCount;
    }

    public long getTotalPresentationCount() {
        return totalPresentationCount;
    }

    public void setTotalPresentationCount(final long totalPresentationCount) {
        this.totalPresentationCount = totalPresentationCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public long getTotalViewCount() {
        return totalViewCount;
    }

    public void setTotalViewCount(final long totalViewCount) {
        this.totalViewCount = totalViewCount;
    }

    /**
     * @return the channelIds
     */
    public final List<Long> getChannelIds() {
        return channelIds;
    }

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
    public final boolean isPublicSpace() {
        return isPublicSpace;
    }

    /**
     * @param isPublicSpace the isPublicSpace to set
     */
    public final void setPublicSpace(final boolean isPublicSpace) {
        this.isPublicSpace = isPublicSpace;
    }

    public List<Long> getStreamingServerIDs() {
        return streamingServerIDs;
    }

    public void setStreamingServerIDs(final List<Long> streamingServerIDs) {
        this.streamingServerIDs = streamingServerIDs;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(final String keywords) {
        this.keywords = keywords;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(final String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    /**
	 * @return the keywordList
	 */
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
