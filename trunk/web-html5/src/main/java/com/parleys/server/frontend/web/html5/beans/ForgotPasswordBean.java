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
 * Backing bean for the "forgot password" page.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean
@RequestScoped
public class ForgotPasswordBean extends AbstractParleysBean {

    private static final String MESSAGE = "A temporary password has been sent to the provided email address";

    private String emailOrUsername;

    public void init() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        initializeHomepage();
    }

    public String login() {
        final FacesContext fc = FacesContext.getCurrentInstance();
        if (emailOrUsername.equals("123")) {
            //TODO: send email
            System.out.println("ForgotPasswordBean.login");
        }
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, MESSAGE, MESSAGE));
        fc.getExternalContext().getFlash().setKeepMessages(true);
        return "index?faces-redirect=true";
    }

    public String getEmailOrUsername() {
        return emailOrUsername;
    }

    public void setEmailOrUsername(final String emailOrUsername) {
        this.emailOrUsername = emailOrUsername;
    }
}
