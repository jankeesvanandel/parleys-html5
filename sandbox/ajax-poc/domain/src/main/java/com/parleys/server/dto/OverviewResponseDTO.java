package com.parleys.server.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stephan Janssen
 */
public class OverviewResponseDTO<T> {
    /**
     * A list of short overviews.
     */
    private List<T> overviews = new ArrayList<T>();
    /**
     * A starting index for paging.
     */
    private int startingIndex;
    /**
     * A number of items per page.
     */
    private int paging;
    /**
     * Total item count.
     */
    private long totalCount;

    /**
     * Contains the name of parent entity.
     * It is needed on client side for deep linking functionality.
     */
    private String parentEntityName;

    private Long parentEntityId;

    /**
     * Get a list of short overviews.
     *
     * @return A list of short overviews.
     */
    public List<T> getOverviews() {
        return overviews;
    }

    public void setOverviews(final List<T> overviews) {
        this.overviews = overviews;
    }

    /**
     * Get a starting index for paging.
     *
     * @return A starting index for paging.
     */
    public int getStartingIndex() {
        return startingIndex;
    }

    public void setStartingIndex(final int startingIndex) {
        this.startingIndex = startingIndex;
    }

    /**
     * Get a number of items per page.
     *
     * @return A number of items per page.
     */
    public int getPaging() {
        return paging;
    }

    public void setPaging(final int paging) {
        this.paging = paging;
    }

    /**
     * Get total item count.
     *
     * @return Total item count.
     */
    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(final long totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * Returns the name of parent entity.
     * It is needed on client side for deep linking functionality.
     *
     * @return the name of parent entity.
     */
    public String getParentEntityName() {
        return parentEntityName;
    }

    /**
     * Sets the name of parent entity.
     * It is needed on client side for deep linking functionality.
     *
     * @param parentEntityName the name of parent entity.
     */
    public void setParentEntityName(final String parentEntityName) {
        this.parentEntityName = parentEntityName;
    }

    /**
     * @return the parentEntityId
     */
    public final Long getParentEntityId() {
        return parentEntityId;
    }

    /**
     * @param parentEntityId the parentEntityId to set
     */
    public final void setParentEntityId(final Long parentEntityId) {
		this.parentEntityId = parentEntityId;
	}
}
