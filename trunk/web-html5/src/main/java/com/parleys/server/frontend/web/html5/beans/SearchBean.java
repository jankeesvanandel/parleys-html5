package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.frontend.domain.Presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for the homepage.
 */
@ManagedBean
@RequestScoped
public class SearchBean extends AbstractParleysBean {

    private String criteria;
    private List<Presentation> results;

    public void init() {
        initializeHomepage();
    }

    public String search() {
        // TODO
        // results = getParleysServiceDelegate().search(criteria);
//        if (results.isEmpty()) {
//            final FacesContext fc = FacesContext.getCurrentInstance();
//            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No results", "No results"));
//        }
        return null;
    }

    public List<Presentation> getResults() {
        return results;
    }

    public void setResults(final List<Presentation> results) {
        this.results = results;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(final String criteria) {
        this.criteria = criteria;
    }
}
