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

import com.parleys.server.frontend.service.ParleysService;

import javax.faces.bean.ManagedProperty;

/**
 * Generic base class with functionality required on all pages.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
public abstract class AbstractParleysBean {

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

    public PagingBean getPagingBean() {
        return pagingBean;
    }

    public void setPagingBean(final PagingBean pagingBean) {
        this.pagingBean = pagingBean;
    }
}
