package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.frontend.domain.Presentation;
import com.parleys.server.frontend.service.ParleysServiceDelegate;
import com.parleys.server.service.ParleysService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * Backing bean for the space detail page.
 */
@ManagedBean
@RequestScoped
public class PresentationBean extends AbstractParleysBean {

    private long presentationId;

    private Presentation presentation;

    public void init() {
        presentation = getParleysServiceDelegate().loadPresentation(presentationId);

        super.initializePresentation(presentation);
    }

    public long getPresentationId() {
        return presentationId;
    }

    public void setPresentationId(final long presentationId) {
        this.presentationId = presentationId;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(final Presentation presentation) {
        this.presentation = presentation;
    }
}
