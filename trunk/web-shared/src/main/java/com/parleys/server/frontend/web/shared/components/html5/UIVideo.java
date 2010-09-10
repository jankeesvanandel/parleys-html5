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
package com.parleys.server.frontend.web.shared.components.html5;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;

/**
 * Simple video component.
 *
 * For now, it renders an HTML5 video tag, but in the future it might support other players, like Flash.
 *
 * @author Jan-Kees van Andel
 */
@FacesComponent("com.parleys.jsf.components.html5.UIVideo")
public class UIVideo extends UIComponentBase {

    static final String FAMILY = "com.parleys.jsf.components.html5";

    private static final String NOTSUPPORTED_FACET = "notSupported";

    private String width, height;

    private String poster;

    private Boolean preload;

    private Boolean autoplay;

    private Boolean loop;

    private Boolean controls;

    private String ontimeupdate;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFamily() {
        return FAMILY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeBegin(final FacesContext context) throws IOException {
        super.encodeBegin(context);
        final ResponseWriter w = context.getResponseWriter();
        w.write("<video");
        writeIfNotNull("id", getClientId(context), w);
        writeIfNotNull("width", width, w);
        writeIfNotNull("height", height, w);
        if (preload == null) {
            w.write(" preload=\"auto\"");
        } else if (preload) {
            w.write(" preload=\"metadata\"");
        } else {
            w.write(" preload=\"none\"");
        }

        writeIfTrueOrNull("autoplay", autoplay, w);
        writeIfTrueAndNotNull("loop", loop, w);
        if (controls == null || controls) {
            w.write(" controls=\"true\"");
        }

        writeIfNotNull("ontimeupdate", ontimeupdate, w);

        w.write(">");
    }

    @Override
    public void encodeEnd(final FacesContext context) throws IOException {
        super.encodeEnd(context);
        final UIComponent facet = getFacet(NOTSUPPORTED_FACET);
        if (facet != null) {
            facet.encodeAll(context);
        }

        final ResponseWriter w = context.getResponseWriter();
        w.write("</video>");
    }

    private void writeIfTrueOrNull(final String attrName,
                                   final Boolean condition,
                                   final ResponseWriter w) throws IOException {
        if (condition == null || condition) {
            w.write(" " + attrName + "=\"true\"");
        }
    }

    private void writeIfTrueAndNotNull(final String attrName,
                                       final Boolean condition,
                                       final ResponseWriter w) throws IOException {
        if (condition == null || condition) {
            w.write(" " + attrName + "=\"true\"");
        }
    }

    private void writeIfNotNull(final String attrName,
                                final Object value,
                                final ResponseWriter w) throws IOException {
        if (value != null) {
            w.write(" " + attrName + "=\"" + value + "\"");
        }
    }

    public Boolean getAutoplay() {
        return autoplay;
    }

    public void setAutoplay(final Boolean autoplay) {
        this.autoplay = autoplay;
    }

    public Boolean getControls() {
        return controls;
    }

    public void setControls(final Boolean controls) {
        this.controls = controls;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(final String height) {
        this.height = height;
    }

    public Boolean getLoop() {
        return loop;
    }

    public void setLoop(final Boolean loop) {
        this.loop = loop;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(final String poster) {
        this.poster = poster;
    }

    public Boolean isPreload() {
        return preload;
    }

    public void setPreload(final Boolean preload) {
        this.preload = preload;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(final String width) {
        this.width = width;
    }

    public String getOntimeupdate() {
        return ontimeupdate;
    }

    public void setOntimeupdate(final String ontimeupdate) {
        this.ontimeupdate = ontimeupdate;
    }
}
