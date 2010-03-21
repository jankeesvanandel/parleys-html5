package com.parleys.server.frontend.domain;

/**
 *
 */
public final class Speaker {
    private final long id;
    private final String fullName;
    private final String bio;

    public Speaker(long id, String fullName, String bio) {
        this.id = id;
        this.fullName = fullName;
        this.bio = bio;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBio() {
        return bio;
    }
}
