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

/**
 * @author Stephan Janssen
 */
public class AssetDTO extends AbstractDTO {

    private static final long serialVersionUID = 557024316892182652L;

    private Integer sequence;

    private Float cuePoint;

    private String cuePointName;

    private String type;

    private String slideContent;

    private Float duration;

    private Float startVal;

    private Float endVal;

    private String streamName;

    private Integer originalWidth;

    private Integer originalHeight;

    private String target;

    private String value;

    /**
     * Base url to combine with "value" field to produce full path.
     */
    private String baseUrl;

    /**
     * The absolute url for getting asset.
     */
    private String assetUrl;

    public final Integer getSequence() {
        return sequence;
    }

    public final void setSequence(final Integer sequence) {
        this.sequence = sequence;
    }

    public final Float getCuePoint() {
        return cuePoint;
    }

    public final void setCuePoint(final Float cuePoint) {
        this.cuePoint = cuePoint;
    }

    public final String getCuePointName() {
        return cuePointName;
    }

    public final void setCuePointName(final String cuePointName) {
        this.cuePointName = cuePointName;
    }

    public final String getType() {
        return type;
    }

    public final void setType(final String type) {
        this.type = type;
    }

    public final String getSlideContent() {
        return slideContent;
    }

    public final void setSlideContent(final String slideContent) {
        this.slideContent = slideContent;
    }

    public final Float getDuration() {
        return duration;
    }

    public final void setDuration(final Float duration) {
        this.duration = duration;
    }

    public final Float getStartVal() {
        return startVal;
    }

    public final void setStartVal(final Float startVal) {
        this.startVal = startVal;
    }

    public final Float getEndVal() {
        return endVal;
    }

    public final void setEndVal(final Float endVal) {
        this.endVal = endVal;
    }

    public final String getStreamName() {
        return streamName;
    }

    public final void setStreamName(final String streamName) {
        this.streamName = streamName;
    }

    public final Integer getOriginalWidth() {
        return originalWidth;
    }

    public final void setOriginalWidth(final Integer originalWidth) {
        this.originalWidth = originalWidth;
    }

    public final Integer getOriginalHeight() {
        return originalHeight;
    }

    public final void setOriginalHeight(final Integer originalHeight) {
        this.originalHeight = originalHeight;
    }

    public final String getTarget() {
        return target;
    }

    public final void setTarget(final String target) {
        this.target = target;
    }

    public final String getValue() {
        return value;
    }

    public final void setValue(final String value) {
        this.value = value;
    }

    public final String getBaseUrl() {
        return baseUrl;
    }

    public final void setBaseUrl(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * @param assetUrl the assetUrl to set
     */
    public final void setAssetUrl(final String assetUrl) {
        this.assetUrl = assetUrl;
    }

    /**
     * @return the assetUrl
     */
    public final String getAssetUrl() {
        return assetUrl;
    }
}
