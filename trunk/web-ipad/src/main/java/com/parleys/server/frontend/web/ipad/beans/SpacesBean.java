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

import com.parleys.server.domain.types.FeaturedType;
import com.parleys.server.dto.SpaceOverviewDTO;
import com.parleys.server.frontend.domain.Filter;
import com.parleys.server.frontend.web.jsf.util.JSFUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for the "spaces overview" page.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean
@RequestScoped
public class SpacesBean extends AbstractParleysBean implements Paginable {

    public void init() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        getPagingBean().setPaging(6);
        gotoPage(getPagingBean().getFilter(), getPagingBean().getIndex(), getPagingBean().getPaging());
    }

    /** {@inheritDoc} */
    @Override
    public void gotoPage(final Filter filter, final int index, final int paging) {
        getPagingBean().setFilter(filter);
        getPagingBean().setIndex(index);
        getPagingBean().setPaging(paging);

        final List<SpaceOverviewDTO> spaces = loadSpaces(filter);
        getPagingBean().setPaginatedList(spaces);
    }

    @SuppressWarnings("unchecked")
    private List<SpaceOverviewDTO> loadSpaces(final Filter filter) {
        if (filter != null) {
            return enhanceWithIpadStuff((List<SpaceOverviewDTO>) getParleysService().getFeatured(FeaturedType.SPACE));
        } else {
            return enhanceWithIpadStuff(getParleysService().getSpacesOverview(0, 200).getOverviews());
        }
    }

    private List<SpaceOverviewDTO> enhanceWithIpadStuff(final List<SpaceOverviewDTO> list) {
        for (final SpaceOverviewDTO dto : list) {
            dto.setVisibleOnIpad(UtilBean.determineIpadVisibility(dto));
        }

        return list;
    }
}
