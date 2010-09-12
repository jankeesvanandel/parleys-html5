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

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

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
}
