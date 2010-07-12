package com.parleys.server.domain.types;

/**
 * @author Stephan Janssen
 */
public enum PresentationType {
    
    /**
     * Audio only talks.
     */
    AUDIO_AND_SLIDES,

    /**
     * Majority of the Parleys talks.
     */
    VIDEO_AND_SLIDES,

    /**
     * Not yet used, will be used in version 1 of
     * the public Community presentations.
     */
    SLIDES_ONLY,

    /**
     * Video only talks = interviews etc.
     */
    VIDEO_ONLY,

    /**
     * This is not a talk but an advertisement which
     * might have an external link.
     */
    ADVERTISEMENT,

    /**
     * This type is for filtering only.
     */
    ALL
}
