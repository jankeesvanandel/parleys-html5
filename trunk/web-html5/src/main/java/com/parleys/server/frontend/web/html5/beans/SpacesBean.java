package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.dto.FilteredOverviewResponseDTO;
import com.parleys.server.dto.SpaceOverviewDTO;
import com.parleys.server.frontend.service.ParleysServiceDelegate;
import com.parleys.server.service.ParleysService;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;

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
    private ParleysServiceDelegate parleysServiceDelegate;

    // private List<Space> spaces;
    private List<SpaceOverviewDTO> spaces;

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

        // spaces = parleysService.loadSpaces(index, paging);
        FilteredOverviewResponseDTO<SpaceOverviewDTO> spacesDTO = null;
        try {
            spacesDTO = parleysServiceDelegate.getSpacesOverview(index, paging);
        } catch (ClientStatusException e) {
            // TODO Add logger
            System.out.println(e.toString());
            
            e.printStackTrace();
        }
        spaces = spacesDTO.getOverviews();
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

    public ParleysServiceDelegate getParleysServiceDelegate() {
        return parleysServiceDelegate;
    }

    public void setParleysServiceDelegate(ParleysServiceDelegate parleysServiceDelegate) {
        this.parleysServiceDelegate = parleysServiceDelegate;
    }

    public List<SpaceOverviewDTO> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<SpaceOverviewDTO> spaces) {
        this.spaces = spaces;
    }
}
