package com.parleys.server.frontend.domain;

/**
 * $Author$
 * $Revision$
 */
public enum Filter {

    FEATURED("Featured"),

    LATEST("Latest"),

    TOP_RATED("Top Rated"),

    MOST_VIEWED("Most Viewed");

    String description;

    Filter(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static enum Type {
        SPACE,
        CHANNEL,
        PRESENTATION
    }
}
