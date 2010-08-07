package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.frontend.domain.Filter;

/**
 * This interface is used for beans that are used for paging in a list of values.
 *
 * Jan-Kees van Andel
 */
public interface Paginable {

    /**
     * Go to a certain page within the total list of values.
     *
     * @param filter The type of query that is displayed.
     * @param index The index, starting at zero.
     * @param paging The paging is the amount of entries displayed on one page.
     */
    void gotoPage(Filter filter, int index, int paging);
}
