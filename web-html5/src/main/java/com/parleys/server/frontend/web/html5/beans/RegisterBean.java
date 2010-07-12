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
public class RegisterBean extends AbstractParleysBean {

    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String name;
    private String email;
    private String confirmEmail;

    @ManagedProperty("#{parleysService}")
    private ParleysServiceDelegate parleysServiceDelegate;

    public void init() {
        initializeHomepage();
    }

    public String register() {
        final FacesContext fc = FacesContext.getCurrentInstance();

        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "A confirmation email has been sent to the provided email address", "A confirmation email has been sent to the provided email address"));
        fc.getExternalContext().getFlash().setKeepMessages(true);

        return "index?faces-redirect=true";
    }

    public void setParleysServiceDelegate(ParleysServiceDelegate parleysServiceDelegate) {
        this.parleysServiceDelegate = parleysServiceDelegate;
    }

    public ParleysServiceDelegate getParleysServiceDelegate() {
        return parleysServiceDelegate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }
}
