package com.parleys.server.domain;

import com.parleys.server.domain.types.AssetTargetType;
import com.parleys.server.domain.types.AssetType;

/**
 * @author Stephan Janssen
 */
public class Asset extends AbstractEntity {

    static final long serialVersionUID = 8838328467405398342L;

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

    public Asset() {
        super();
    }

    public Asset(final Asset original) {
        super();
        sequence = original.getSequence();
        cuePoint = original.getCuePoint();
        cuePointName = original.getCuePointName();
        type = original.getType();
        slideContent = original.getSlideContent();
        duration = original.getDuration();
        startVal = original.getStartVal();
        endVal = original.getEndVal();
        streamName = original.getStreamName();
        originalWidth = original.getOriginalWidth();
        originalHeight = original.getOriginalHeight();
        target = original.getTarget();
        value = original.getValue();
        id = original.getId();
        setCreatedOn(original.getCreatedOn());
        setModifiedOn(original.getModifiedOn());
        setVersion(original.getVersion());
    }

    public final Integer getSequence() {
        return sequence;
    }

    /**
     * This is a zero based sequence number of the different
     * slides for the related presentation.
     *
     * @param value the slide sequence number
     */
    public final void setSequence(final Integer value) {
        sequence = value;
    }

    /**
     * Get the duration of the talk.
     *
     * @return The total seconds of the presentation.
     */
    public final Float getDuration() {
        return duration;
    }

    /**
     * The total duration of the presentation.
     *
     * @param newValue the duration (in seconds) of the presentation.
     * @throws IllegalArgumentException when sequence is 0
     */
    public final void setDuration(final Float newValue)
            throws IllegalArgumentException {

        if (newValue == 0) {
            throw new IllegalArgumentException("Value can not be zero");
        }

        duration = newValue;
    }

    /**
     * Set the asset type.
     *
     * @param newValue the asset type
     */
    public final void setType(final AssetType newValue) {
        type = newValue;
    }

    public final AssetType getType() {
        return type;
    }

    public final void setCuePoint(final Float newValue) {
        cuePoint = newValue;
    }

    public final Float getCuePoint() {
        return cuePoint;
    }

    public final void setValue(final String newValue) {
        value = newValue;
    }

    public final String getValue() {
        return value;
    }

    public final void setCuePointName(final String newValue) {
        cuePointName = newValue;
    }

    public final String getCuePointName() {
        return cuePointName;
    }

    public AssetTargetType getTarget() {
        return target;
    }

    public void setTarget(final AssetTargetType target) {
        this.target = target;
    }

    /**
     * @param startVal The starting cue point within particular stream file.
     */
    public final void setStartVal(final Float startVal) {
        this.startVal = startVal;
    }

    /**
     * @return The starting cue point within particular stream file
     */
    public final Float getStartVal() {
        return startVal;
    }

    /**
     * @param endVal The ending cue point within particular stream file
     */
    public final void setEndVal(final Float endVal) {
        this.endVal = endVal;
    }

    /**
     * @return The ending cue point within particular stream file
     */
    public final Float getEndVal() {
        return endVal;
    }

    /**
     * @param streamName the streamName to set
     */
    public final void setStreamName(final String streamName) {
        this.streamName = streamName;
    }

    /**
     * @return the streamName
     */
    public final String getStreamName() {
        return streamName;
    }

    /**
     * @param originalWidth the originalWidth to set
     */
    public final void setOriginalWidth(final Integer originalWidth) {
        this.originalWidth = originalWidth;
    }

    /**
     * @return the originalWidth
     */
    public final Integer getOriginalWidth() {
        return originalWidth;
    }

    /**
     * @param originalHeight the originalHeight to set
     */
    public final void setOriginalHeight(final Integer originalHeight) {
        this.originalHeight = originalHeight;
    }

    /**
     * @return the originalHeight
     */
    public final Integer getOriginalHeight() {
        return originalHeight;
    }

    /**
     * @return the slide content (in ASCII)
     */
    public String getSlideContent() {
        return slideContent;
    }

    /**
     * @param slideContent the actual ASCII slide content
     */
    public void setSlideContent(final String slideContent) {
        this.slideContent = slideContent;
    }

    public final String toString() {
        return new StringBuilder().append("id:").append(id).append(", slideContent:").append(slideContent).toString();
    }
}
