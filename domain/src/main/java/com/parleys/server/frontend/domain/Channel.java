package com.parleys.server.frontend.domain;

/**
 *
 */
public class Channel {
    private long id;
    private String name;
    private String description;
    private String thumbnail;
    private String mediaBaseURL;
    private String assetBaseURL;

    public Channel() {
    }

    public Channel(long id, String name, String description, String thumbnail, String mediaBaseURL, String assetBaseURL) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.mediaBaseURL = mediaBaseURL;
        this.assetBaseURL = assetBaseURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Channel)) return false;

        Channel channel = (Channel) o;

        return id == channel.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getMediaBaseURL() {
        return mediaBaseURL;
    }

    public void setMediaBaseURL(String mediaBaseURL) {
        this.mediaBaseURL = mediaBaseURL;
    }

    public String getAssetBaseURL() {
        return assetBaseURL;
    }

    public void setAssetBaseURL(String assetBaseURL) {
        this.assetBaseURL = assetBaseURL;
    }
}
