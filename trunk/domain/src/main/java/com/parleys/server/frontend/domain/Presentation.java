package com.parleys.server.frontend.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;
import java.util.Set;

/**
 *
 */
@JsonIgnoreProperties(value = {"MP3URL"})
public class Presentation {
    private long id;
    private String summary;
    private double duration;
    private String title;
    private String thumbnail;
    private String keywords;

    // This casing is required by Jackson.
    private String mp3URL;
    private long pageId;
    private int totalVotes;
    private int totalDownloads;
    private String type;
    private Set<Speaker> speakers;

    private List<Asset> assets;

    public Presentation() {
    }

    public Presentation(long id, String summary, double duration, String title, String thumbnail, String keywords, String mp3URL, long pageId, int totalVotes, int totalDownloads, Set<Speaker> speakers) {
        this.id = id;
        this.summary = summary;
        this.duration = duration;
        this.title = title;
        this.thumbnail = thumbnail;
        this.keywords = keywords;
        this.mp3URL = mp3URL;
        this.pageId = pageId;
        this.totalVotes = totalVotes;
        this.totalDownloads = totalDownloads;
        this.speakers = speakers;
    }

//    public void handleUnknown(String key, Object value) {
//        if (key.equals("MP3URL")) {
//            setMp3URL();
//        }
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getMP3URL() {
        return mp3URL;
    }

    public void setMP3URL(String mp3URL) {
        this.mp3URL = mp3URL;
    }

    public long getPageId() {
        return pageId;
    }

    public void setPageId(long pageId) {
        this.pageId = pageId;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }

    public int getTotalDownloads() {
        return totalDownloads;
    }

    public void setTotalDownloads(int totalDownloads) {
        this.totalDownloads = totalDownloads;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Set<Speaker> speakers) {
        this.speakers = speakers;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }
}
