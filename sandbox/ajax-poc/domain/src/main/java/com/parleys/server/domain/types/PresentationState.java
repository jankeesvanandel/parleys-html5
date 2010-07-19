package com.parleys.server.domain.types;

/**
 * @author Stephan Janssen
 */
public enum PresentationState {

    /**
     * Presentation is being prepared in staging.
     */
    STAGING,

    /**
     * Presentation is available for review by admins and publishers.
     */
    PREVIEW,

    /**
     * Presentation is public available.
     */
    PUBLIC,

    /**
     * Presentation has been archived.
     */
    ARCHIVED
}
