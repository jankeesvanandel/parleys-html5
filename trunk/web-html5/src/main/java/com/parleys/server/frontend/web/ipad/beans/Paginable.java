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
package com.parleys.server.frontend.web.ipad.beans;

import com.parleys.server.frontend.domain.Filter;

/**
 * This interface is used for beans that are used for paging in a list of values.
 * <p/>
 * Jan-Kees van Andel
 */
public interface Paginable {

    /**
     * Go to a certain page within the total list of values.
     *
     * @param filter The type of query that is displayed.
     * @param index  The index, starting at zero.
     * @param paging The paging is the amount of entries displayed on one page.
     */
    void gotoPage(Filter filter, int index, int paging);
}
