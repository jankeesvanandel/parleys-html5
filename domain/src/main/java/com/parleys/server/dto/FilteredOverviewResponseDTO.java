package com.parleys.server.dto;

import com.parleys.server.domain.BackgroundImage;
import com.parleys.server.domain.types.PresentationDateRange;
import com.parleys.server.domain.types.PresentationSort;
import com.parleys.server.domain.types.PresentationType;

/**
 * @author Stephan Janssen
 */
public class FilteredOverviewResponseDTO<T> extends OverviewResponseDTO<T> {

    /**
     * The background image URL to use when showing this overview.
     */
     private BackgroundImage backgroundImage = new BackgroundImage();

    /**
     * The type of presentation needed
     */
    private PresentationType type;

    /**
     * Sort by
     */
    private PresentationSort sort;

    /**
     * Date region (TODAY, THIS WEEK, ...)
     */
    private PresentationDateRange date;

    /**
     * Search string to filter (search on presentation title, summary and description, tag name
     * and speaker's bio, first name and last name)
     */
    private String filter;

    /**
     * The type of presentation needed
     *
     * @return The type of presentation needed
     */
    public PresentationType getType() {
        return type;
    }

    public void setType(final PresentationType type) {
        this.type = type;
    }

    /**
     * Sort by
     *
     * @return Sort by
     */
    public PresentationSort getSort() {
        return sort;
    }

    public void setSort(final PresentationSort sort) {
        this.sort = sort;
    }

    /**
     * Date region (TODAY, THIS WEEK, ...)
     *
     * @return Date region (TODAY, THIS WEEK, ...)
     */
    public PresentationDateRange getDate() {
        return date;
    }

    public void setDate(final PresentationDateRange date) {
        this.date = date;
    }

    /**
     * @param filter Search string to filter (search on presentation title, summary and description, tag name
     *               and speaker's bio, first name and last name)
     */
    public void setFilter(final String filter) {
        this.filter = filter;
    }

    /**
     * @return Search string to filter (search on presentation title, summary and description, tag name
     *         and speaker's bio, first name and last name)
     */
    public String getFilter() {
        return filter;
    }

    /**
     * @param backgroundImage the backgroundImage to set
     */
    public void setBackgroundImage(final BackgroundImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * @return the backgroundImage
     */
    public BackgroundImage getBackgroundImage() {
        return backgroundImage;
    }
}
