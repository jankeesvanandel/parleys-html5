package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.frontend.domain.Presentation;
import com.parleys.server.frontend.service.ParleysService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Backing bean for the homepage.
 */
@ManagedBean
@RequestScoped
public class SearchBean extends AbstractParleysBean {

    private String criteria;
    private List<Presentation> results;

    @ManagedProperty("#{parleysService}")
    private ParleysService parleysService;

    public void init() {
        initializeHomepage();
    }

    public String search() {
        results = parleysService.search(criteria);
        if (results.isEmpty()) {
            final FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No results", "No results"));
        }
        return null;
    }

    public void setParleysService(ParleysService parleysService) {
        this.parleysService = parleysService;
    }

    public ParleysService getParleysService() {
        return parleysService;
    }

    public List<Presentation> getResults() {
        return results;
    }

    public void setResults(List<Presentation> results) {
        this.results = results;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }
}
