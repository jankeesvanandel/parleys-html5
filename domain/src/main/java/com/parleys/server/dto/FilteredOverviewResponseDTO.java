package com.parleys.server.dto;

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
    private String backgroundLink;

    /**
     * The background image position based on the enum value of BackgroundImagePositionType.
     */
    private String backgroundPosition;

    /**
     * Should the presentations inherit the parent channel background image?
     */
    private boolean isBackgroundInherited;

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

    public String getBackgroundLink() {
        return backgroundLink;
    }

    public void setBackgroundLink(final String backgroundLink) {
        this.backgroundLink = backgroundLink;
    }

    public String getBackgroundPosition() {
        return backgroundPosition;
    }

    public void setBackgroundPosition(final String backgroundPosition) {
        this.backgroundPosition = backgroundPosition;
    }

    public boolean isBackgroundInherited() {
        return isBackgroundInherited;
    }

    public void setBackgroundInherited(final boolean backgroundInherited) {
        isBackgroundInherited = backgroundInherited;
    }
}
