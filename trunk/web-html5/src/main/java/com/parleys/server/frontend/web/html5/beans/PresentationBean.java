package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.domain.types.AssetTargetType;
import com.parleys.server.dto.AssetDTO;
import com.parleys.server.dto.ExtendedPresentationDetailsDTO;
import com.parleys.server.security.AuthorizationException;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Backing bean for the space detail page.
 */
@ManagedBean
@RequestScoped
public class PresentationBean extends AbstractParleysBean {

    private final transient Log LOG = LogFactory.getLog(getClass());

    private long presentationId;

    private ExtendedPresentationDetailsDTO presentation;

    private List<AssetDTO> slideAssets;

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



    public void setSlideAssets(List<AssetDTO> slideAssets){
        this.slideAssets = slideAssets;
    }

    public List getSlideAssets(){
        if(slideAssets!=null){
            return slideAssets;
        }


        List<AssetDTO> assets = presentation.getAssetDTOs();

        List<AssetDTO> sAssets = new ArrayList<AssetDTO>();

        for (AssetDTO asset : assets) {
            System.out.println(asset.getTarget() + "    " + asset.getType() + "    " + asset.getValue());

            if (asset.getTarget().name().equals(AssetTargetType.SLIDE_PANEL.name())) {
                System.out.println("Yep it is and asset");
                sAssets.add(asset);
            } else {
                System.out.println("Nope its not and asset");
            }
        }

        slideAssets = sAssets;

        return slideAssets;
    }

}
