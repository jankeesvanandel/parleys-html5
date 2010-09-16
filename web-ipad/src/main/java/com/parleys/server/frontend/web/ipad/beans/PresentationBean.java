/*
 * Copyright (C) 2010 Parleys.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.parleys.server.frontend.web.ipad.beans;

import com.parleys.server.domain.types.AssetTargetType;
import com.parleys.server.domain.types.PresentationType;
import com.parleys.server.dto.AbstractDTO;
import com.parleys.server.dto.AssetDTO;
import com.parleys.server.dto.ExtendedPresentationDetailsDTO;
import com.parleys.server.dto.PresentationOverviewDTO;
import com.parleys.server.frontend.web.jsf.util.JSFUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Backing bean for the space detail page.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean
@RequestScoped
public class PresentationBean extends AbstractParleysBean {

    @ManagedProperty("#{navigationBean}")
    private NavigationBean navigationBean;

    private long presentationId;

    private String presentationTitle;

    private ExtendedPresentationDetailsDTO presentation;

    private List<AssetDTO> slideAssets;

    private List<? extends AbstractDTO> relatedPresentations;

    private String streamURL;

    private String type;

    public void init() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        presentation = getParleysService().getPresentationDetails(presentationId);

        setRelatedPresentations(getParleysService().searchPresentations(presentation.getKeywordsString(),0,5));
    }

    public List<? extends AbstractDTO> getRelatedPresentations(){
        return relatedPresentations;
    }

    public void setRelatedPresentations(List<? extends AbstractDTO> value){
        relatedPresentations = value;

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


    public void setSlideAssets(List<AssetDTO> slideAssets) {
        this.slideAssets = slideAssets;
    }

    public List getSlideAssets() {
        if (slideAssets != null) {
            return slideAssets;
        }
        final List<AssetDTO> assets = presentation.getAssetDTOs();
        final List<AssetDTO> sAssets = new ArrayList<AssetDTO>();

        for (AssetDTO asset : assets) {
            if (asset.getTarget().equals(AssetTargetType.SLIDE_PANEL.name())) {
                String value = asset.getValue();
                if (value != null && value.length() > 4) {
                    value = "/iphone_" + value.substring(1, value.length() - 3) + "jpg";
                    asset.setValue(value);

                }
                 sAssets.add(asset);
            }
        }

        slideAssets = sAssets;
        return slideAssets;
    }


    public String getStreamURL() {
        final List<AssetDTO> assets = presentation.getAssetDTOs();
        AssetDTO streamAsset = null;
        for (AssetDTO asset : assets) {
            if (asset.getTarget().equals(AssetTargetType.VIDEO_PANEL.name())) {
                streamAsset = asset;
            }
        }

        //http://www.bejug.org:1935/parleys/_definst_/1973/mp4:201007151225031102499.mp4/playlist.m3u8
        if (streamAsset != null) {
            /*
            String value = streamAsset.getValue();
            value = value.substring(1, value.length());
            String template = "http://www.bejug.org:1935/parleys/_definst_/%n/mp4:%n/playlist.m3u8";
            value = String.format(template, presentationId, value);
            this.streamURL = value;
            LOGGER.info(streamURL);
            */
            String value = streamAsset.getValue();
            value = value.substring(1,value.length()).split("\\.")[0];
            value = "http://www.bejug.org:1935/parleys/_definst_/"+presentationId+"/mp4:"+value+".mp4/playlist.m3u8";
            this.streamURL = value;

        } else {
            throw new IllegalArgumentException("No streaming asset for presentation :" + presentation.getId());
        }
        return streamURL;

    }


    public void setStreamURL(String streamURL) {
        this.streamURL = streamURL;
    }



    public void setType(String value) {
        this.type = value;
    }

    public String getType(){
        String t = presentation.getType();
        return t;
    }

    public void setPresentationTitle(String value){
        this.presentationTitle = value;
    }

    public String getPresentationTitle(){
        return presentation.getTitle();
    }

    public NavigationBean getNavigationBean() {
        return navigationBean;
    }

    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }
}
