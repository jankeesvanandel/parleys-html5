package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.dto.PresentationOverviewDTO;
import com.parleys.server.frontend.web.html5.util.JSFUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for the "search" page.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean @RequestScoped
public class SearchBean extends AbstractParleysBean {

    private String criteria;

    private List<PresentationOverviewDTO> results;

    public void init() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

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

    public List<PresentationOverviewDTO> getResults() {
        return results;
    }

    public void setResults(final List<PresentationOverviewDTO> results) {
        this.results = results;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(final String criteria) {
        this.criteria = criteria;
    }
}
