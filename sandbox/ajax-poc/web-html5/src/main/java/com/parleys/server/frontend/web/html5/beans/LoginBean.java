package com.parleys.server.frontend.web.html5.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Backing bean for the homepage.
 */
@ManagedBean
@RequestScoped
public class LoginBean extends AbstractParleysBean {

    private String username;
    private String password;

    public void init() {
        initializeHomepage();
    }

    public String login() {
        final FacesContext fc = FacesContext.getCurrentInstance();
        if (username.equals("123") && password.equals("456")) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logged in successfully", "Logged in successfully"));
            fc.getExternalContext().getFlash().setKeepMessages(true);

            return "index?faces-redirect=true";
        } else {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wrong credentials entered", "Wrong credentials entered"));
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
