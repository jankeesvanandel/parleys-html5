package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.dto.FilteredOverviewResponseDTO;
import com.parleys.server.dto.SpaceOverviewDTO;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;

import javax.faces.bean.ManagedBean;
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

        FilteredOverviewResponseDTO<SpaceOverviewDTO> spacesDTO = null;
        try {
            spacesDTO = getParleysServiceDelegate().getSpacesOverview(index, paging);
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

    public void setIndex(final Integer index) {
        this.index = index;
    }

    public Integer getPaging() {
        return paging;
    }

    public void setPaging(final Integer paging) {
        this.paging = paging;
    }

    public List<SpaceOverviewDTO> getSpaces() {
        return spaces;
    }

    public void setSpaces(final List<SpaceOverviewDTO> spaces) {
        this.spaces = spaces;
    }
}
