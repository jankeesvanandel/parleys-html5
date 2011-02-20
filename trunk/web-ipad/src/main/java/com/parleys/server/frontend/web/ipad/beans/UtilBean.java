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

import com.parleys.server.dto.AbstractDTO;
import com.parleys.server.dto.ChannelOverviewDTO;
import com.parleys.server.dto.PresentationDetailsDTO;
import com.parleys.server.dto.PresentationOverviewDTO;
import com.parleys.server.dto.SpaceOverviewDTO;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Application scoped utility bean with useful functions.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean
@ApplicationScoped
public class UtilBean {

    private String basePath;

    private static final List<Integer> ipadSpaces = Arrays.asList(189, 15594);
    private static final List<Integer> ipadChannels = Arrays.asList(74957, 102906, 102929, 102933);
    private static final List<Integer> ipadPresentations = Arrays.asList(1467, 1470, 1471, 1550, 1571, 1597, 1650, 1759, 1789, 1845, 1881, 1952, 1953, 1957, 1964, 1969, 1973, 1976, 1979, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2031, 2278, 2284, 2285, 2286, 2291, 2293, 2294);

    /**
     * Get the base path to the web application, which is used to lookup resources such as style sheets and scripts.
     *
     * @return The base path.
     */
    public synchronized String getBasePath() {
        if (basePath == null) {
            final FacesContext fc = FacesContext.getCurrentInstance();
            //TODO: fix when deployed online
            basePath = fc.getExternalContext().getRequestContextPath();
//            basePath = "http://www.parleys.com/";
        }
        return basePath;
    }

    public static boolean determineIpadVisibility(final AbstractDTO dto) {
        final boolean visibleOnIpad;
        if (dto instanceof SpaceOverviewDTO) {
            visibleOnIpad = ipadSpaces.contains(dto.getId().intValue());
            ((SpaceOverviewDTO)dto).setVisibleOnIpad(visibleOnIpad);
        } else if (dto instanceof ChannelOverviewDTO) {
            visibleOnIpad = ipadChannels.contains(dto.getId().intValue());
            ((ChannelOverviewDTO)dto).setVisibleOnIpad(visibleOnIpad);
        } else if (dto instanceof PresentationOverviewDTO) {
            visibleOnIpad = ipadPresentations.contains(dto.getId().intValue())
                         || ((PresentationOverviewDTO)dto).getChannelId().equals(102906L);
            ((PresentationOverviewDTO)dto).setVisibleOnIpad(visibleOnIpad);
        } else if (dto instanceof PresentationDetailsDTO) {
            visibleOnIpad = ipadPresentations.contains(dto.getId().intValue());
            ((PresentationDetailsDTO)dto).setVisibleOnIpad(visibleOnIpad);
        } else {
            throw new IllegalArgumentException("dto is not a valid DTO to determine iPad visibility for");
        }
        return visibleOnIpad;
    }

    private static final Random random = new Random();
}
