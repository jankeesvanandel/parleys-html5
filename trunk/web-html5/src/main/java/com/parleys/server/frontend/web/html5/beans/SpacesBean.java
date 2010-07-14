package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.domain.types.FeaturedType;
import com.parleys.server.dto.SpaceOverviewDTO;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for the spaces overview.
 *
 * @author Jan-Kees Vanandel
 * @author Stephan Janssen
 */
@ManagedBean
@RequestScoped
public class SpacesBean extends PagingBean {

    private final transient Log LOG = LogFactory.getLog(getClass());

    private List<SpaceOverviewDTO> spaces;

    @SuppressWarnings("unchecked")
    public void init() {

        try {
            if (getFilter() != null) {
                spaces = (List<SpaceOverviewDTO>)getParleysServiceDelegate().getFeatured(FeaturedType.SPACE);
            } else {
                spaces = getParleysServiceDelegate().getSpacesOverview(getIndex(), getPaging()).getOverviews();
            }
        } catch (ClientStatusException e) {
            LOG.error(e);
        }
    }

    public List<SpaceOverviewDTO> getSpaces() {
        return spaces;
    }
}
