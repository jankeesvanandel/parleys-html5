package com.parleys.server.domain;

/**
 * Thumbnail to display on the homepage. Used for spaces, channels and presentations.
 *
 * @author Jan-Kees van Andel
 */
public class Thumbnail {
    private long id;
    private String name;
    private String thumbnailUrl;
    private String outcome;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
}
