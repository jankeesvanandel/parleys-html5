package com.parleys.server.frontend.domain;

/**
 *
 */
public final class Space {
    private final long id;
    private final String name;
    private final String description;
    private final String thumbnail;

    public Space(long id, String name, String description, String thumbnail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
