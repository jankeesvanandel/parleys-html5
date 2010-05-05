package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.frontend.domain.Channel;
import com.parleys.server.frontend.domain.Presentation;
import com.parleys.server.frontend.domain.Space;
import com.parleys.server.frontend.service.ParleysService;
import com.parleys.server.frontend.service.PresentationsCriteria;

import java.util.List;

/**
 * Bean that holds process information.
 */
public abstract class AbstractParleysBean {

    private boolean isOnHomepage = false;

    private Space currentSpace;

    private Channel currentChannel;

    private Presentation currentPresentation;

    public abstract ParleysService getParleysService();

    public final void initializeHomepage() {
        this.isOnHomepage = true;
    }

    public final void initializeSpace(Space space) {
        this.currentSpace = space;
    }

    public final void initializeChannel(Channel channel) {
        this.currentChannel = channel;
        initializeSpace(findSpaceForChannel(channel));
    }

    public final void initializePresentation(Presentation presentation) {
        this.currentPresentation = presentation;
        initializeChannel(findChannelForPresentation(presentation));
    }

    private Space findSpaceForChannel(Channel channel) {
        final List<Space> spaces = getParleysService().loadAllSpaces();
        for (Space space : spaces) {
            final List<Channel> channels = getParleysService().loadChannels(space.getId());
            for (Channel c : channels) {
                if (c.equals(channel)) {
                    return space;
                }
            }
        }
//        throw new IllegalStateException("Given channel does not belong to a space");
        //TODO: revert this
        return new Space();
    }

    private Channel findChannelForPresentation(Presentation presentation) {
        final List<Space> spaces = getParleysService().loadAllSpaces();
        for (Space space : spaces) {
            final List<Channel> channels = getParleysService().loadChannels(space.getId());
            for (Channel c : channels) {
//                if (c.getId() == 18821) {
//                    continue;
//                }
                final PresentationsCriteria criteria = new PresentationsCriteria();
                criteria.setChannelId(c.getId());
                final List<Presentation> presentations = getParleysService().loadPresentationsWithCriteria(criteria);
                for (Presentation p : presentations) {
                    if (p.equals(presentation)) {
                        return c;
                    }
                }
            }
        }
//        throw new IllegalStateException("Given channel does not belong to a space");
        //TODO: revert this
        return new Channel();
    }

    public Space getCurrentSpace() {
        return currentSpace;
    }

    public Channel getCurrentChannel() {
        return currentChannel;
    }

    public Presentation getCurrentPresentation() {
        return currentPresentation;
    }

    public boolean getIsOnHomepage() {
        return isOnHomepage;
    }
}
