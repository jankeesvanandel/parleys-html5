package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.domain.types.FeaturedType;
import com.parleys.server.dto.SpaceOverviewDTO;
import com.parleys.server.frontend.domain.Filter;
import com.parleys.server.frontend.web.html5.util.JSFUtil;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for the "spaces overview" page.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean @RequestScoped
public class SpacesBean extends AbstractParleysBean implements Paginable {

    private static final Logger LOGGER = Logger.getLogger(SpacesBean.class);

    public void init() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        getPagingBean().setPaging(6);
        gotoPage(getPagingBean().getFilter(), getPagingBean().getIndex(), getPagingBean().getPaging());
    }

    public void gotoPage(Filter filter, int index, int paging) {
        getPagingBean().setFilter(filter);
        getPagingBean().setIndex(index);
        getPagingBean().setPaging(paging);

        try {
            List<SpaceOverviewDTO> spaces = loadSpaces(filter);
            getPagingBean().setPaginatedList(spaces);
        } catch (ClientStatusException e) {
            LOGGER.error(e);
        }
    }

    @SuppressWarnings("unchecked")
    private List<SpaceOverviewDTO> loadSpaces(Filter filter) throws ClientStatusException {
        if (filter != null) {
            return (List<SpaceOverviewDTO>) getParleysServiceDelegate().getFeatured(FeaturedType.SPACE);
        } else {
            return getParleysServiceDelegate().getSpacesOverview(0, 200).getOverviews();
        }
    }

}
