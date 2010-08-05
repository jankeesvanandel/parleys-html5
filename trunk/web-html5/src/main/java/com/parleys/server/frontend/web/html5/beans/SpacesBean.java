package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.domain.types.FeaturedType;
import com.parleys.server.dto.AbstractDTO;
import com.parleys.server.dto.FilteredOverviewResponseDTO;
import com.parleys.server.dto.SpaceOverviewDTO;
import com.parleys.server.frontend.domain.Filter;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for the spaces overview.
 *
 * @author Jan-Kees Vanandel
 * @author Stephan Janssen
 */
@ManagedBean @RequestScoped
public class SpacesBean extends AbstractParleysBean {

    private final transient Log LOG = LogFactory.getLog(getClass());

    public void init() {
        getPagingBean().setPaging(6);
        internalGotoPage(getPagingBean().getFilter(), getPagingBean().getIndex(), getPagingBean().getPaging());
    }

    public void gotoPage(Filter filter, int index, int paging) {
        getPagingBean().setFilter(filter);
        getPagingBean().setIndex(index);
        getPagingBean().setPaging(paging);
        internalGotoPage(filter, index, paging);
    }

    @SuppressWarnings("unchecked")
    private void internalGotoPage(Filter filter, int index, int paging) {
        try {
            if (filter != null) {
                final List<? extends AbstractDTO> spaces = getParleysServiceDelegate().getFeatured(FeaturedType.SPACE);
                getPagingBean().setList(spaces);
                getPagingBean().setTotalCount((long) spaces.size());
            } else {
                final FilteredOverviewResponseDTO<SpaceOverviewDTO> dto = getParleysServiceDelegate().getSpacesOverview(index, paging);
                getPagingBean().setList(dto.getOverviews());
                getPagingBean().setTotalCount(dto.getTotalCount());
            }
        } catch (ClientStatusException e) {
            LOG.error(e);
        }
    }

}
