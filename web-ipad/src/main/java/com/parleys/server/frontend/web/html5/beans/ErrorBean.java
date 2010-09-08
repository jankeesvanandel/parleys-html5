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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Backing bean for the error page.
 */
@ManagedBean(name = ErrorBean.NAME) @RequestScoped
public class ErrorBean extends AbstractParleysBean {

    public static final String NAME = "errorBean";

    private String summary;

    private String detail;

    /**
     * Used by JSF.
     */
    public ErrorBean() {
    }

    /**
     * Used by exception handler.
     *
     * @param summary The error summary.
     * @param detail The detail message.
     */
    public ErrorBean(String summary, String detail) {
        this.summary = summary;
        this.detail = detail;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
