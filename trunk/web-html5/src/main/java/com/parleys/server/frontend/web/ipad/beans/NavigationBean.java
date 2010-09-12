package com.parleys.server.frontend.web.ipad.beans;

import com.parleys.server.dto.ChannelOverviewDTO;
import com.parleys.server.dto.ExtendedPresentationDetailsDTO;
import com.parleys.server.dto.SpaceOverviewDTO;
import com.parleys.server.frontend.web.jsf.util.JSFUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Backing bean for the menu.
 *
 * @author Jan-Kees van Andel
 */
@ManagedBean @RequestScoped
public class NavigationBean extends AbstractParleysBean {

    private boolean isOnHomepage = false;

    private Long id;

    private ExtendedPresentationDetailsDTO presentation;
    private ChannelOverviewDTO channel;
    private Long spaceId;
    private String spaceName;

    public final void initHomepage() {
        isOnHomepage = true;
    }

    public final void initNewsPage() {
        isOnHomepage = true;
    }

    public final void initSpacesPage() {
        isOnHomepage = true;
    }

    public final void initChannelsPage() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        SpaceOverviewDTO space = getParleysService().getSpaceOverviewDTO(id);
        this.spaceId = space.getId();
        this.spaceName = space.getName();
    }

    public final void initPresentationsPage() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        this.channel = getParleysService().getChannelOverviewDTO(id);
        this.spaceId = this.channel.getSpaceId();
        this.spaceName = this.channel.getSpaceName();
    }

    public final void initPresentationPage() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        this.presentation = getParleysService().getPresentationDetails(id);
        this.channel = this.presentation.getChannel();
        this.spaceId = this.channel.getSpaceId();
        this.spaceName = this.channel.getSpaceName();
    }

    public boolean getIsOnHomepage() {
        return isOnHomepage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPresentationId() {
        if (presentation != null) {
            return presentation.getId();
        } else {
            return null;
        }
    }

    public String getPresentationName() {
        if (presentation != null) {
            return presentation.getTitle();
        } else {
            return null;
        }
    }

    public Long getChannelId() {
        if (channel != null) {
            return channel.getId();
        } else {
            return null;
        }
    }

    public String getChannelName() {
        if (channel != null) {
            return channel.getName();
        } else {
            return null;
        }
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public String getSpaceName() {
        return spaceName;
    }
}
