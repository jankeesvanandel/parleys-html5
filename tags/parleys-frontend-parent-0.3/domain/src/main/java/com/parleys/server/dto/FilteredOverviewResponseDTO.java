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

import com.parleys.server.domain.BackgroundImage;
import com.parleys.server.domain.types.PresentationDateRange;
import com.parleys.server.domain.types.PresentationSort;
import com.parleys.server.domain.types.PresentationType;

/**
 * @author Stephan Janssen
 * @param <T> The type of response object (presentation, channel or space).
 */
public class FilteredOverviewResponseDTO<T> extends OverviewResponseDTO<T> {

    /*
     * The background image URL to use when showing this overview.
     */
    private BackgroundImage backgroundImage = new BackgroundImage();

    /*
     * The type of presentation needed.
     */
    private PresentationType type;

    /*
     * Sort by.
     */
    private PresentationSort sort;

    /*
     * Date region (TODAY, THIS WEEK, ...).
     */
    private PresentationDateRange date;

    /*
     * Search string to filter (search on presentation title, summary and description, tag name
     * and speaker's bio, first name and last name).
     */
    private String filter;

    /**
     * The type of presentation needed.
     *
     * @return The type of presentation needed.
     */
    public PresentationType getType() {
        return type;
    }

    /**
     * Set the type.
     * @param type The type.
     */
    public void setType(final PresentationType type) {
        this.type = type;
    }

    /**
     * Sort by.
     *
     * @return Sort by.
     */
    public PresentationSort getSort() {
        return sort;
    }

    /**
     * Set the sort.
     * @param sort The sort.
     */
    public void setSort(final PresentationSort sort) {
        this.sort = sort;
    }

    /**
     * Date region (TODAY, THIS WEEK, ...).
     *
     * @return Date region (TODAY, THIS WEEK, ...).
     */
    public PresentationDateRange getDate() {
        return date;
    }

    /**
     * Set the date.
     * @param date The date.
     */
    public void setDate(final PresentationDateRange date) {
        this.date = date;
    }

    /**
     * Set the filter.
     * @param filter Search string to filter (search on presentation title, summary and description, tag name
     *               and speaker's bio, first name and last name).
     */
    public void setFilter(final String filter) {
        this.filter = filter;
    }

    /**
     * @return Search string to filter (search on presentation title, summary and description, tag name
     *         and speaker's bio, first name and last name).
     */
    public String getFilter() {
        return filter;
    }

    /**
     * @param backgroundImage the backgroundImage to set.
     */
    public void setBackgroundImage(final BackgroundImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * Return the backgroundImage.
     * @return the backgroundImage.
     */
    public BackgroundImage getBackgroundImage() {
        return backgroundImage;
    }
}
