package com.parleys.server.frontend.web.jsf.components.html5;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;

/**
 *
 */
@FacesComponent("com.parleys.server.frontend.web.jsf.components.html5.UIVideo")
public class UIVideo extends UIComponentBase {
    public static final String FAMILY = "com.parleys.server.frontend.web.jsf.components.html5";

    public Integer width, height;

    public String poster;

    public Boolean preload;

    public Boolean autoplay;

    public Boolean loop;

    public Boolean controls;

    public String ontimeupdate;

    public static final String NOTSUPPORTED_FACET = "notSupported";

    @Override
    public String getFamily() {
        return FAMILY;
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
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
            w.write(" controls");
        }

        writeIfNotNull("ontimeupdate", ontimeupdate, w);

        w.write(">");
    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        final UIComponent facet = getFacet(NOTSUPPORTED_FACET);
        if (facet != null) {
            facet.encodeAll(context);
        }

        final ResponseWriter w = context.getResponseWriter();
        w.write("</video>");
    }

    private void writeIfTrueOrNull(String attrName, Boolean condition, ResponseWriter w) throws IOException {
        if (condition == null || condition) {
            w.write(" " + attrName + "=\"true\"");
        }
    }

    private void writeIfTrueAndNotNull(String attrName, Boolean condition, ResponseWriter w) throws IOException {
        if (condition == null || condition) {
            w.write(" " + attrName + "=\"true\"");
        }
    }

    private void writeIfNotNull(String attrName, Object value, ResponseWriter w) throws IOException {
        if (value != null) {
            w.write(" " + attrName + "=\"" + value + "\"");
        }
    }

    public Boolean getAutoplay() {
        return autoplay;
    }

    public void setAutoplay(Boolean autoplay) {
        this.autoplay = autoplay;
    }

    public Boolean getControls() {
        return controls;
    }

    public void setControls(Boolean controls) {
        this.controls = controls;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Boolean getLoop() {
        return loop;
    }

    public void setLoop(Boolean loop) {
        this.loop = loop;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Boolean isPreload() {
        return preload;
    }

    public void setPreload(Boolean preload) {
        this.preload = preload;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getOntimeupdate() {
        return ontimeupdate;
    }

    public void setOntimeupdate(String ontimeupdate) {
        this.ontimeupdate = ontimeupdate;
    }
}
