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

import com.parleys.server.frontend.web.html5.util.JSFUtil;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Backing bean for the homepage.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean
@RequestScoped
public class LoginBean extends AbstractParleysBean {

    private String username;
    private String password;
    private static final String SUCCESS_MESSAGE = "Logged in successfully";
    private static final String ERROR_MESSAGE = "Wrong credentials entered";

    public void init() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        initializeHomepage();
    }

    public String login() {
        final FacesContext fc = FacesContext.getCurrentInstance();
        if (username.equals("123") && password.equals("456")) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, SUCCESS_MESSAGE, SUCCESS_MESSAGE));
            fc.getExternalContext().getFlash().setKeepMessages(true);

            return "index?faces-redirect=true";
        } else {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ERROR_MESSAGE, ERROR_MESSAGE));
            return null;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
