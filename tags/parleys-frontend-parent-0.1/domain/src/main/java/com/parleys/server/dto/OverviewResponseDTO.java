/*
 * Copyright (C) 2010 Parleys.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.parleys.server.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stephan Janssen
 * @param <T> The type of response object (presentation, channel or space).
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
