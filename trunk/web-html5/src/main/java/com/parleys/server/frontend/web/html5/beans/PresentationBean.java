package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.domain.types.AssetTargetType;
import com.parleys.server.dto.AssetDTO;
import com.parleys.server.dto.ExtendedPresentationDetailsDTO;
import com.parleys.server.frontend.web.html5.util.JSFUtil;
import com.parleys.server.security.AuthorizationException;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Backing bean for the space detail page.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean @RequestScoped
public class PresentationBean extends AbstractParleysBean {

    private static final Logger LOGGER = Logger.getLogger(PresentationBean.class);

    private long presentationId;

    private ExtendedPresentationDetailsDTO presentation;

    private List<AssetDTO> slideAssets;

    private String streamURL;

    public void init() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        try {
            presentation = getParleysServiceDelegate().getPresentationDetails(presentationId);
        } catch (ClientStatusException e) {
            LOGGER.error(e);
        } catch (AuthorizationException e) {
            LOGGER.error(e);
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
            if (asset.getTarget().equals(AssetTargetType.SLIDE_PANEL.name())) {
                String value = asset.getValue();
                if (value != null && value.length() > 4) {
                    value = "/iphone_"+value.substring(1,value.length()-3)+"jpg";
                    asset.setValue(value);
                    sAssets.add(asset);
                }
            }
        }

        slideAssets = sAssets;
        return slideAssets;
    }


    public String getStreamURL(){
        List<AssetDTO> assets = presentation.getAssetDTOs();
        List<AssetDTO> sAssets = new ArrayList<AssetDTO>();
        AssetDTO streamAsset = null;
        for (AssetDTO asset : assets) {
            if (asset.getTarget().equals(AssetTargetType.VIDEO_PANEL.name())) {
                streamAsset = asset;
            }
        }

        //http://www.bejug.org:1935/parleys/_definst_/1973/mp4:201007151225031102499.mp4/playlist.m3u8
        String value = streamAsset.getValue();
        value = value.substring(1,value.length());
        value = "http://www.bejug.org:1935/parleys/_definst_/"+presentationId+"/mp4:"+value+"/playlist.m3u8";
        this.streamURL = value;
        System.out.println(streamURL);
        return streamURL;

    }


    public void setStreamURL(String streamURL){
        this.streamURL = streamURL;
    }
}
