package com.parleys.server.frontend.domain;

/**
 *
 */
public class Speaker {
    private long id;

    private String fullname;

    private String bio;

    public Speaker() {
    }

    public Speaker(long id, String fullname, String bio) {
        this.id = id;
        this.fullname = fullname;
        this.bio = bio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
