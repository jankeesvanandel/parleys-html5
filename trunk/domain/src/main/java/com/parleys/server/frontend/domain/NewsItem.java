package com.parleys.server.frontend.domain;

import java.util.Date;

/**
 * $Author$
 * $Revision$
 */
public class NewsItem {
    private long id;

    private String title;

    private Date date;

    private String content;

    public NewsItem() {
    }

    public NewsItem(long id, String title, Date date, String content) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
