package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.frontend.service.ParleysServiceDelegate;
import com.parleys.server.service.ParleysService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Backing bean for the homepage.
 */
@ManagedBean
@RequestScoped
public class ForgotPasswordBean extends AbstractParleysBean {

    private String emailOrUsername;

    @ManagedProperty("#{parleysService}")
    private ParleysServiceDelegate parleysServiceDelegate;

    public void init() {
        initializeHomepage();
    }

    public String login() {
        final FacesContext fc = FacesContext.getCurrentInstance();
        if (emailOrUsername.equals("123")) {
            //TODO: send email
        }
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "A temporary password has been sent to the provided email address", "A temporary password has been sent to the provided email address"));
        fc.getExternalContext().getFlash().setKeepMessages(true);
        return "index?faces-redirect=true";
    }

    public void setParleysServiceDelegate(ParleysServiceDelegate parleysServiceDelegate) {
        this.parleysServiceDelegate = parleysServiceDelegate;
    }

    public ParleysServiceDelegate getParleysServiceDelegate() {
        return parleysServiceDelegate;
    }

    public String getEmailOrUsername() {
        return emailOrUsername;
    }

    public void setEmailOrUsername(String emailOrUsername) {
        this.emailOrUsername = emailOrUsername;
    }
}
