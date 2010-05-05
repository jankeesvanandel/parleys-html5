package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.frontend.domain.Space;
import com.parleys.server.frontend.service.ParleysService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for the spaces overview.
 */
@ManagedBean
@RequestScoped
public class SpacesBean extends AbstractParleysBean {

    private Integer index;
    private Integer paging;

    @ManagedProperty("#{parleysService}")
    private ParleysService parleysService;

    private List<Space> spaces;

    public void init() {
        if (index != null) {
            index = Math.max(index, 1000);
        } else {
            index = 0;
        }

        if (paging != null) {
            paging = Math.max(paging, 100);
        } else {
            paging = 20;
        }

        spaces = parleysService.loadSpaces(index, paging);
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getPaging() {
        return paging;
    }

    public void setPaging(Integer paging) {
        this.paging = paging;
    }

    public ParleysService getParleysService() {
        return parleysService;
    }

    public void setParleysService(ParleysService parleysService) {
        this.parleysService = parleysService;
    }

    public List<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<Space> spaces) {
        this.spaces = spaces;
    }
}
