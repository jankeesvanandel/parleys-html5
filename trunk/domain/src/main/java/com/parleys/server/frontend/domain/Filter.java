package com.parleys.server.frontend.domain;

/**
 * $Author$
 * $Revision$
 */
public enum Filter {
    FEATURED,
    LATEST,
    TOP_RATED,
    MOST_VIEWED;

    public static enum Type {
        SPACE,
        CHANNEL,
        PRESENTATION
    }
}
