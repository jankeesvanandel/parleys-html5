package com.parleys.server.frontend.domain;

import java.util.Set;

/**
 *
 */
public final class Presentation {
    private final long id;
    private String summary;
    private double duration;
    private String title;
    private String thumbnail;
    private Set<String> keywords;
    private String mp3url;
    private long pageId;
    private int totalVotes;
    private int totalDownloads;
    private PresentationType type;
    private Set<Speaker> speakers;

    public Presentation(long id, String summary, double duration, String title, String thumbnail, Set<String> keywords, String mp3url, long pageId, int totalVotes, int totalDownloads, Set<Speaker> speakers) {
        this.id = id;
        this.summary = summary;
        this.duration = duration;
        this.title = title;
        this.thumbnail = thumbnail;
        this.keywords = keywords;
        this.mp3url = mp3url;
        this.pageId = pageId;
        this.totalVotes = totalVotes;
        this.totalDownloads = totalDownloads;
        this.speakers = speakers;
    }

    public long getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public double getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public String getMp3url() {
        return mp3url;
    }

    public long getPageId() {
        return pageId;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public int getTotalDownloads() {
        return totalDownloads;
    }

    public Set<Speaker> getSpeakers() {
        return speakers;
    }
}
