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
import com.parleys.server.dto.ExtendedPresentationDetailsDTO;
import com.parleys.server.dto.SpaceOverviewDTO;
import com.parleys.server.frontend.service.ParleysService;

import javax.faces.bean.ManagedProperty;

/**
 * Generic base class with functionality required on all pages.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
public abstract class AbstractParleysBean {

    private boolean isOnHomepage = false;

    private SpaceOverviewDTO currentSpace;

    private ChannelOverviewDTO currentChannel;

    private ExtendedPresentationDetailsDTO currentPresentation;

    @ManagedProperty("#{pagingBean}")
    private PagingBean pagingBean;

    @ManagedProperty("#{parleysService}")
    private ParleysService parleysService;

    public ParleysService getParleysService() {
        return parleysService;
    }

    public void setParleysService(final ParleysService parleysService) {
        this.parleysService = parleysService;
    }

    public final void initializeHomepage() {
        isOnHomepage = true;
    }

    public final void initializeSpace(final SpaceOverviewDTO space) {
        currentSpace = space;
    }

    public final void initializeChannel(final ChannelOverviewDTO channel) {
        currentChannel = channel;
        initializeSpace(findSpaceForChannel(channel));
    }

    public final void initializePresentation(final ExtendedPresentationDetailsDTO presentation) {
        currentPresentation = presentation;
        initializeChannel(findChannelForPresentation(presentation));
    }

    private SpaceOverviewDTO findSpaceForChannel(final ChannelOverviewDTO channel) {
        return getParleysService().getSpaceOverviewDTO(channel.getSpaceId());
    }

    private ChannelOverviewDTO findChannelForPresentation(final ExtendedPresentationDetailsDTO presentation) {
        return presentation.getChannel();
    }

    public SpaceOverviewDTO getCurrentSpace() {
        return currentSpace;
    }

    public ChannelOverviewDTO getCurrentChannel() {
        return currentChannel;
    }

    public ExtendedPresentationDetailsDTO getCurrentPresentation() {
        return currentPresentation;
    }

    public boolean getIsOnHomepage() {
        return isOnHomepage;
    }

    public PagingBean getPagingBean() {
        return pagingBean;
    }

    public void setPagingBean(PagingBean pagingBean) {
        this.pagingBean = pagingBean;
    }
}
