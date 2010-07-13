package com.parleys.server.dto;

import com.parleys.server.domain.types.AssetTargetType;
import com.parleys.server.domain.types.AssetType;

/**
 * @author Stephan Janssen
 */
public class AssetDTO extends AbstractDTO {

    private static final long serialVersionUID = 557024316892182652L;

    private Integer sequence;

    private Float cuePoint;

    private String cuePointName;

    private AssetType type;

    private String slideContent;

    private Float duration;

    private Float startVal;

    private Float endVal;

    private String streamName;

    private Integer originalWidth;

    private Integer originalHeight;

    private AssetTargetType target = AssetTargetType.SLIDE_PANEL;

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

    public final AssetType getType() {
        return type;
    }

    public final void setType(final AssetType type) {
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

    public final AssetTargetType getTarget() {
        return target;
    }

    public final void setTarget(final AssetTargetType target) {
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
