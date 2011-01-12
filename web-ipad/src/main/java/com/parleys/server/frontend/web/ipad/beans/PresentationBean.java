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
import com.parleys.server.frontend.web.ipad.filters.AESEncrypter;
import com.parleys.server.frontend.web.ipad.filters.LoginFilter;
import com.parleys.server.frontend.web.jsf.util.JSFUtil;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

    private static final Logger LOGGER = Logger.getLogger(PresentationBean.class);

    @ManagedProperty("#{navigationBean}")
    private NavigationBean navigationBean;

    private Long presentationId;

    private ExtendedPresentationDetailsDTO presentation;

    private List<AssetDTO> slideAssets;

    private List<? extends AbstractDTO> relatedPresentations;

    private List<Stream> streams;

    private String userToken;

    private boolean isFirstLoginAttempt = true;

    public void init() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        presentation = getParleysService().getPresentationDetails(presentationId);

        // TODO: Will need to add some kind of paging and filter the current presentation from the result
        setRelatedPresentations(getParleysService().searchPresentations(presentation.getKeywordsString(), 0, 4));
    }

    public List<? extends AbstractDTO> getRelatedPresentations(){
        return relatedPresentations;
    }

    public void setRelatedPresentations(final List<? extends AbstractDTO> value){
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

            final String slidePrefix = determineSlidePrefix();

            for (final AssetDTO asset : assets) {
                if (asset.getTarget().equals(AssetTargetType.SLIDE_PANEL.name())) {
                    String value = asset.getValue();
                    if (value != null && value.length() >= 4) {
                        value = slidePrefix + value.substring(1, value.length() - 3) + "jpg";
                        asset.setValue(value);
                    }
                    slideAssets.add(asset);
                }
            }
            for (int i = 0, assetsSize = slideAssets.size(); i < assetsSize; i++) {
                final AssetDTO asset = slideAssets.get(i);
                if (asset.getValue() == null
                 || asset.getValue().length() < 4) {
                    final AssetDTO nextAsset;
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

    private String determineSlidePrefix() {
        final String slidePrefix;

        // Use iPhone slides if it's a Devoxx09 test talk.
        if (presentation.getChannel().getId().equals(74957L)) {
            slidePrefix = "/iphone_";
        } else {
            slidePrefix = "/ipad_";
        }
        return slidePrefix;
    }

    public List<Stream> getStreams() {
        if (this.streams == null) {
            if (presentation == null) {
                return null;
            }
            final List<AssetDTO> assets = presentation.getAssetDTOs();

            final String baseUrl = presentation.getStreamingURL();

            this.streams = new ArrayList<Stream>();
            for (final AssetDTO asset : assets) {
                if (asset.getTarget().equals(AssetTargetType.VIDEO_PANEL.name())) {
                    String streamUrl = baseUrl.replaceFirst("rtmp", "http");

                    // This replace is needed for the Devoxx09 test talks, which don't have
                    // the correct ExtendedPresentationDetailsDTO.streamingURL property.
                    streamUrl = streamUrl.replaceFirst("stream1", "stream2");

                    streamUrl += "_definst_/";
                    streamUrl += presentationId;
                    streamUrl += "/mp4:";
                    String assetValue = asset.getValue().substring(1);

                    // This replace is needed for the Devoxx09 test talks, which don't have
                    // the correct extension.
                    assetValue = assetValue.replace(".flv", ".mp4");

                    streamUrl += assetValue;
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

    public boolean getShowLoginPanel(){
        if (presentation != null) {
            boolean loggedIn = loginUsingCookie();
            return !presentation.isFree() && !loggedIn;
        } else {
            return false;
        }
    }

    private boolean loginUsingCookie() {
        if (isFirstLoginAttempt) {
            isFirstLoginAttempt = false;

            final FacesContext facesContext = FacesContext.getCurrentInstance();
            final ExternalContext externalContext = facesContext.getExternalContext();
            final HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
            final Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (final Cookie cookie : cookies) {
                    if (LoginFilter.PARLEYS_REMEMBER_ME_IPAD.equals(cookie.getName())) {
                        try {
                            final String value = cookie.getValue();
                            final String decrypted = AESEncrypter.INSTANCE.decrypt(value);
                            final String[] parts = decrypted.split(";");
                            final String username = parts[0];
                            final String password = parts[1];

                            if (LOGGER.isInfoEnabled()) {
                                final String address = request.getHeader("X-Forwarded-For");
                                final String userAgent = request.getHeader("User-Agent");
                                LOGGER.info(String.format("%s - %s - %s", address, username, userAgent));
                            }

                            userToken = Long.toString(getParleysService().getUserId(username, password));
                            break;
                        } catch (Exception ignored) {
                            userToken = null;
                        }
                    }
                }
            }
        }
        return userToken != null;
    }

    public String getUserToken() {
        loginUsingCookie();
        return userToken;
    }

    public void setUserToken(final String userToken) {
        this.userToken = userToken;
    }

    public NavigationBean getNavigationBean() {
        return navigationBean;
    }

    public void setNavigationBean(final NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }
}
