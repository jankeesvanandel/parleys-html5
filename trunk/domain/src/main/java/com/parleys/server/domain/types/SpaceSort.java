package com.parleys.server.domain.types;

/**
 * @author Stephan Janssen
 */
public enum SpaceSort {

    // Sort on creation date ascending (default).
    CREATION_DATE,

    // Sort on creation date ascending.
    CREATION_DATE_DESC,

    // Sort on number of channels ascending.
    TOTAL_CHANNELS_ASC,

    // Sort on number of channels descending.
    TOTAL_CHANNELS_DESC,

    // Sort on total number of views asscending.
    TOTAL_VIEWS_ASC,

    // Sort on total number of views descending.
    TOTAL_VIEWS_DESC,

    // Sort on total number of talks ascending.
    TOTAL_TALKS_ASC,

    // Sort on total number of talks descending.
    TOTAL_TALKS_DESC
}
