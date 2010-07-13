package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.dto.ExtendedPresentationDetailsDTO;
import com.parleys.server.security.AuthorizationException;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Backing bean for the space detail page.
 */
@ManagedBean
@RequestScoped
public class PresentationBean extends AbstractParleysBean {

    private final transient Log LOG = LogFactory.getLog(getClass());

    private long presentationId;

    private ExtendedPresentationDetailsDTO presentation;

    public void init() {
        try {
            presentation = getParleysServiceDelegate().getPresentationDetails(presentationId);
        } catch (ClientStatusException e) {
            LOG.error(e);
        } catch (AuthorizationException e) {
            LOG.error(e);
        }

        super.initializePresentation(presentation);
    }

    public long getPresentationId() {
        return presentationId;
    }

    public void setPresentationId(final long presentationId) {
        this.presentationId = presentationId;
    }

    public ExtendedPresentationDetailsDTO getPresentation() {
        return presentation;
    }

    public void setPresentation(final ExtendedPresentationDetailsDTO presentation) {
        this.presentation = presentation;
    }
}
