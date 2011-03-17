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
public class ChannelOverviewDTO extends AbstractDTO {

    private static final long serialVersionUID = 963569454651137798L;

    private String name;

    /**
     * Need to add to the Channel entity?
     */
    private String thumbnailURL;

    private String spaceName;

    private Long spaceId;

    private long totalPresentationCount;

    private long totalPresentationCountIncludingNonPublic;

    private String description;

    private int totalViewCount;

    private long parentId;

    private String membership;

    private int subscriptionInterval;

    private String subscriptionPeriod;

    private float subscriptionAmount;

    private float payPerTalkAmount;

    // EUR or USD for
    private String paymentCurrency;

    // if isDeleted is true the channel is a holder for deleted presentations.
    // So we can't delete or reparent this channel at all
    private boolean isDeleted = false;

    private boolean isIPhoneEnabled = true;

    private boolean isPodcastEnabled = true;

    private boolean isPublic = true;

    private boolean isListed = true;

    private List<String> keywordList = new ArrayList<String>();

    // Enabled when the user is subscribed to this 'not free' channel.
    private boolean subscribed = false;

    private boolean hostedInPublicSpace = false;

    private boolean visibleOnIpad;

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    @JsonProperty
    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    @JsonProperty
    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    @JsonProperty
    public long getTotalPresentationCount() {
        return totalPresentationCount;
    }

    public void setTotalPresentationCount(long totalPresentationCount) {
        this.totalPresentationCount = totalPresentationCount;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty
    public int getTotalViewCount() {
        return totalViewCount;
    }

    public void setTotalViewCount(int totalViewCount) {
        this.totalViewCount = totalViewCount;
    }

    /**
     * @return the parentId
     */
    @JsonProperty
    public final long getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public final void setParentId(long parentId) {
        this.parentId = parentId;
    }

    @JsonIgnore
    public final boolean getDeleted() {
        return isDeleted;
    }

    @JsonProperty
    public final boolean isDeleted() {
        return isDeleted;
    }

    public final void setDeleted(final boolean aDeleted) {
        isDeleted = aDeleted;
    }

    /**
     * @return the totalPresentationCountIncludingNonPublic
     */
    @JsonProperty
    public final long getTotalPresentationCountIncludingNonPublic() {
        return totalPresentationCountIncludingNonPublic;
    }

    /**
     * @param totalPresentationCountIncludingNonPublic
     *         the totalPresentationCountIncludingNonPublic to set
     */
    public final void setTotalPresentationCountIncludingNonPublic(long totalPresentationCountIncludingNonPublic) {
        this.totalPresentationCountIncludingNonPublic = totalPresentationCountIncludingNonPublic;
    }

    @JsonProperty
    public boolean isIPhoneEnabled() {
        return isIPhoneEnabled;
    }

    public void setIPhoneEnabled(boolean isIPhoneEnabled) {
        this.isIPhoneEnabled = isIPhoneEnabled;
    }

    @JsonProperty
    public boolean isPodcastEnabled() {
        return isPodcastEnabled;
    }

    public void setPodcastEnabled(boolean podcastEnabled) {
        isPodcastEnabled = podcastEnabled;
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
    public final void setKeywordList(List<String> keywordList) {
        this.keywordList = keywordList;
    }

    @JsonProperty
    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    @JsonProperty
    public int getSubscriptionInterval() {
        return subscriptionInterval;
    }

    public void setSubscriptionInterval(int subscriptionInterval) {
        this.subscriptionInterval = subscriptionInterval;
    }

    @JsonProperty
    public float getSubscriptionAmount() {
        return subscriptionAmount;
    }

    public void setSubscriptionAmount(float subscriptionAmount) {
        this.subscriptionAmount = subscriptionAmount;
    }

    @JsonProperty
    public float getPayPerTalkAmount() {
        return payPerTalkAmount;
    }

    public void setPayPerTalkAmount(float payPerTalkAmount) {
        this.payPerTalkAmount = payPerTalkAmount;
    }

    @JsonProperty
    public String getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(String paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    @JsonProperty
    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    @JsonProperty
    public String getSubscriptionPeriod() {
        return subscriptionPeriod;
    }

    public void setSubscriptionPeriod(String subscriptionPeriod) {
        this.subscriptionPeriod = subscriptionPeriod;
    }

    @JsonIgnore
    public final boolean getPublicChannel() {
        return isPublic;
    }

    @JsonProperty
    public final boolean isPublicChannel() {
        return isPublic;
    }

    public final void setPublicChannel(final boolean aPublic) {
        isPublic = aPublic;
    }

    @JsonProperty
    public final boolean isListed() {
        return isListed;
    }

    public final void setListed(final boolean listed) {
        isListed = listed;
    }

    @JsonIgnore
    public final boolean getListed() {
        return isListed;
    }

    @JsonProperty
    public boolean isHostedInPublicSpace() {
        return hostedInPublicSpace;
    }

    public void setHostedInPublicSpace(boolean hostedInPublicSpace) {
        this.hostedInPublicSpace = hostedInPublicSpace;
    }

    @JsonProperty
    public boolean getVisibleOnIpad() {
        return visibleOnIpad;
    }

    public void setVisibleOnIpad(boolean visibleOnIpad) {
        this.visibleOnIpad = visibleOnIpad;
    }
}
