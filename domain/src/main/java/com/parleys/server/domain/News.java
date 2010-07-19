package com.parleys.server.domain;

import java.io.Serializable;

/**
 * This news item is linked to a space and holds free text,
 * time based and can include links.
 *
 * @author Stephan Janssen
 */
public class News extends AbstractEntity {

    static final long serialVersionUID = 7829932764561222800L;

    private String title;

    private String item;

    private boolean isGeneral = false;

    private boolean isPublished = false;

    // The author name of the news item
    private String author;

    public final String getItem() {
        return item;
    }

    public final void setItem(final String value) {
        item = value;
    }

    public final boolean isPublished() {
        return isPublished;
    }

    public final void setPublished(final boolean published) {
        isPublished = published;
    }

    public final String getAuthor() {
        return author;
    }

    public final void setAuthor(final String value) {
        author = value;
    }

    public final String getTitle() {
        return title;
    }

    public final void setTitle(final String value) {
        title = value;
    }

    public boolean isGeneral() {
        return isGeneral;
    }

    public void setGeneral(boolean general) {
        isGeneral = general;
    }
}
