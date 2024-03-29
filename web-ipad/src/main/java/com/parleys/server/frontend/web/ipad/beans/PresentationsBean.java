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
import com.parleys.server.dto.PresentationOverviewDTO;
import com.parleys.server.frontend.domain.Filter;
import com.parleys.server.frontend.service.PresentationsCriteria;
import com.parleys.server.frontend.web.jsf.util.JSFUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for the space detail page.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean
@RequestScoped
public class PresentationsBean extends AbstractParleysBean implements Paginable {

    @ManagedProperty("#{presentationsViewBean}")
    private PresentationsViewBean presentationsViewBean;

    public void init() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        gotoPage(getPagingBean().getFilter(), getPagingBean().getIndex(), getPagingBean().getPaging());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gotoPage(final Filter filter, final int index, final int paging) {
        getPagingBean().setFilter(filter);
        getPagingBean().setIndex(index);
        getPagingBean().setPaging(paging);

        final List<PresentationOverviewDTO> presentations = loadPresentations(getPagingBean().getFilter());
        getPagingBean().setPaginatedList(presentations);
    }

    @SuppressWarnings("unchecked")
    private List<PresentationOverviewDTO> loadPresentations(final Filter filter) {
        final PresentationsCriteria criteria = new PresentationsCriteria();
        criteria.setChannelId(getPresentationsViewBean().getChannelId());
        criteria.setIndex(0);
        criteria.setPaging(200);

        if (filter != null) {
            switch (filter) {
                case FEATURED:
                    return enhanceWithIpadStuff((List<PresentationOverviewDTO>)
                            getParleysService().getFeatured(FeaturedType.PRESENTATION));
                case LATEST:
                    return enhanceWithIpadStuff((List<PresentationOverviewDTO>)
                            getParleysService().getLatestPresentationsOverview(criteria));
                case TOP_RATED:
                    return enhanceWithIpadStuff((List<PresentationOverviewDTO>)
                            getParleysService().getTopRatedPresentationsOverview(criteria));
                default: // If default, pick MOST_VIEWED
                    return enhanceWithIpadStuff((List<PresentationOverviewDTO>)
                            getParleysService().getMostViewedPresentationsOverview(criteria));
            }
        } else {
            return enhanceWithIpadStuff((List<PresentationOverviewDTO>) getParleysService().getPresentationsOverview(criteria));
        }
    }

    private List<PresentationOverviewDTO> enhanceWithIpadStuff(final List<PresentationOverviewDTO> list) {
        for (final PresentationOverviewDTO dto : list) {
            dto.setVisibleOnIpad(UtilBean.determineIpadVisibility(dto));
        }

        return list;
    }

    public PresentationsViewBean getPresentationsViewBean() {
        return presentationsViewBean;
    }

    public void setPresentationsViewBean(final PresentationsViewBean presentationsViewBean) {
        this.presentationsViewBean = presentationsViewBean;
    }
}
