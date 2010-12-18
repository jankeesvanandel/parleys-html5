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
import com.parleys.server.dto.AbstractDTO;
import com.parleys.server.dto.AssetDTO;
import com.parleys.server.dto.ExtendedPresentationDetailsDTO;
import com.parleys.server.frontend.domain.Stream;
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

    private Long presentationId;

    private ExtendedPresentationDetailsDTO presentation;

    private List<AssetDTO> slideAssets;

    private List<? extends AbstractDTO> relatedPresentations;

    private List<Stream> streams;

    public void init() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        presentation = getParleysService().getPresentationDetails(presentationId);

        // TODO: Will need to add some kind of paging and filter the current presentation from the result
        setRelatedPresentations(getParleysService().searchPresentations(presentation.getKeywordsString(),0,4));
    }

    public List<? extends AbstractDTO> getRelatedPresentations(){
        return relatedPresentations;
    }

    public void setRelatedPresentations(List<? extends AbstractDTO> value){
        relatedPresentations = value;
    }

    public Long getPresentationId() {
        return presentationId;
    }

    public void setPresentationId(final Long presentationId) {
        this.presentationId = presentationId;
    }

    public ExtendedPresentationDetailsDTO getPresentation() {
        return presentation;
    }

    public List<AssetDTO> getSlideAssets() {
        if (this.slideAssets == null) {
            if (presentation == null) {
                return null;
            }
            final List<AssetDTO> assets = presentation.getAssetDTOs();
            final List<AssetDTO> slideAssets = new ArrayList<AssetDTO>();

            for (AssetDTO asset : assets) {
                if (asset.getTarget().equals(AssetTargetType.SLIDE_PANEL.name())) {
                    String value = asset.getValue();
                    if (value != null && value.length() >= 4) {
                        value = "/ipad_" + value.substring(1, value.length() - 3) + "jpg";
                        asset.setValue(value);
                    }
                    slideAssets.add(asset);
                }
            }
            for (int i = 0, assetsSize = slideAssets.size(); i < assetsSize; i++) {
                AssetDTO asset = slideAssets.get(i);
                if (asset.getValue() == null
                 || asset.getValue().length() < 4) {
                    AssetDTO nextAsset;
                    if (i <= 0) {
                        nextAsset = slideAssets.get(i+1);
                    } else {
                        nextAsset = slideAssets.get(i-1);
                    }
                    asset.setValue(nextAsset.getValue());
                }
            }

            this.slideAssets = slideAssets;
        }
        return this.slideAssets;
    }

    public List<Stream> getStreams() {
        if (this.streams == null) {
            if (presentation == null) {
                return null;
            }
            final List<AssetDTO> assets = presentation.getAssetDTOs();

            String baseUrl = presentation.getStreamingURL();

            //http://www.bejug.org:1935/parleys/_definst_/1973/mp4:201007151225031102499.mp4/playlist.m3u8
            /*
            String value = streamAsset.getValue();
            value = value.substring(1, value.length());
            String template = "http://www.bejug.org:1935/parleys/_definst_/%n/mp4:%n/playlist.m3u8";
            value = String.format(template, presentationId, value);
            this.streamURL = value;
            LOGGER.info(streamURL);
            */
            this.streams = new ArrayList<Stream>();
            for (AssetDTO asset : assets) {
                if (asset.getTarget().equals(AssetTargetType.VIDEO_PANEL.name())) {
                    String streamUrl = baseUrl.replaceFirst("rtmp", "http");
                    streamUrl += "_definst_/";
                    streamUrl += presentationId;
                    streamUrl += "/mp4:";
                    streamUrl += (asset.getValue().substring(1));
                    streamUrl += "/playlist.m3u8";
                    this.streams.add(new Stream(streamUrl, asset.getDuration()));
                }
            }
        }
        return this.streams;
    }

    public String getType(){
        if (presentation != null) {
            return presentation.getType();
        } else {
            return null;
        }
    }

    public String getPresentationTitle(){
        if (presentation != null) {
            return presentation.getTitle();
        } else {
            return null;
        }
    }

    public NavigationBean getNavigationBean() {
        return navigationBean;
    }

    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }
}
