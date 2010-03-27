package com.parleys.server.frontend.web.html5.beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 */
@ManagedBean
@ApplicationScoped
public class UtilBean {
    private String basePath;

    public synchronized String getBasePath() {
        if (basePath == null) {
            final FacesContext fc = FacesContext.getCurrentInstance();
            //TODO: fix when deploy online
//            basePath = fc.getExternalContext().getRequestContextPath()
            basePath = "http://www.parleys.com/";
        }
        return basePath;
    }
}
