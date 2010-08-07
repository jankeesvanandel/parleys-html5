package com.parleys.server.frontend.web.html5.beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * Application scoped utility bean with useful functions.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean @ApplicationScoped
public class UtilBean {

    private String basePath;

    /**
     * Get the base path to the web application, which is used to lookup resources such as style sheets and scripts.
     * @return The base path.
     */
    public synchronized String getBasePath() {
        if (basePath == null) {
            final FacesContext fc = FacesContext.getCurrentInstance();
            //TODO: fix when deployed online
//            basePath = fc.getExternalContext().getRequestContextPath()
            basePath = "http://www.parleys.com/";
        }
        return basePath;
    }
}
