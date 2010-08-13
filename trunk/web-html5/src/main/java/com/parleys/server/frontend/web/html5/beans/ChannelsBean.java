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
package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.dto.ChannelOverviewDTO;
import com.parleys.server.frontend.domain.Filter;
import com.parleys.server.frontend.web.html5.util.JSFUtil;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for the channels overview page.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean
@RequestScoped
public class ChannelsBean extends AbstractParleysBean implements Paginable {

    private static final Logger LOGGER = Logger.getLogger(ChannelsBean.class);

    @ManagedProperty("#{channelsViewBean}")
    private ChannelsViewBean channelsViewBean;

    public void init() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        try {
            super.initializeSpace(getParleysService().getSpaceOverviewDTO(channelsViewBean.getSpaceId()));
        } catch (Exception e) {
            LOGGER.error(e);
        }

        gotoPage(getPagingBean().getFilter(), getPagingBean().getIndex(), getPagingBean().getPaging());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gotoPage(Filter filter, int index, int paging) {
        getPagingBean().setFilter(filter);
        getPagingBean().setIndex(index);
        getPagingBean().setPaging(paging);

        try {
            final List<ChannelOverviewDTO> channels = loadChannels();
            getPagingBean().setPaginatedList(channels);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private List<ChannelOverviewDTO> loadChannels() throws Exception {
        return getParleysService().getChannelsOverview(channelsViewBean.getSpaceId()).getOverviews();
    }

    public ChannelsViewBean getChannelsViewBean() {
        return channelsViewBean;
    }

    public void setChannelsViewBean(ChannelsViewBean channelsViewBean) {
        this.channelsViewBean = channelsViewBean;
    }
}
