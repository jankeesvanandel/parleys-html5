package com.parleys.server.domain.types;

/**
 * @author Stephan Janssen
 */
public enum AssetType {

     /**
     * An JPG, PNG or GIF image used to represent a slide within a presentation.
     */
    IMAGE,

    /**
     * An SWF object used to represent a slide OR some dynamic Flash content
     * like Survey, Poll, etc.
     */
    SWF,

    /**
     * A URL link.
     */
    LINK,

    /**
     * The video presentation asset. We want to allow multiple video's on one
     * timeline, as a result its an asset type :)
     */
    FLV_VIDEO,

    /**
     * MP3 audio presentation.
     */
    MP3_AUDIO,

    /**
     * Blank slide (just cue point for table of contents)
     */
    BLANK,

    YOUTUBE_VIDEO,

    EMPTY
}
