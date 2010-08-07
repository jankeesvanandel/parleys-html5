package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.frontend.web.html5.util.JSFUtil;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Backing bean for the "register" page.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean @RequestScoped
public class RegisterBean extends AbstractParleysBean {

    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String name;
    private String email;
    private String confirmEmail;

    public void init() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        initializeHomepage();
    }

    public String register() {
        final FacesContext fc = FacesContext.getCurrentInstance();

        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "A confirmation email has been sent to the provided email address", "A confirmation email has been sent to the provided email address"));
        fc.getExternalContext().getFlash().setKeepMessages(true);

        return "index?faces-redirect=true";
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(final String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(final String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }
}
