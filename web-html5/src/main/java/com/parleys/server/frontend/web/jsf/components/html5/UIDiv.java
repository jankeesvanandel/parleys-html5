package com.parleys.server.frontend.web.jsf.components.html5;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;

/**
 * @author Jan-Kees Vanandel
 */
@FacesComponent("com.parleys.server.frontend.web.jsf.components.html5.UIDiv")
public class UIDiv extends UIOutput {

    public static final String FAMILY = "com.parleys.server.frontend.web.jsf.components.html5";

    public String styleClass;

    public String style;

    public UIDiv() {
        setRendererType(null); // Override rendererType set by UIOutput to prevent WARNING
    }

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
        final ResponseWriter w = context.getResponseWriter();
        w.startElement("div", this);
        w.writeAttribute("id", getClientId(context), "id");
        if (styleClass != null) {
            w.writeAttribute("class", styleClass, "styleClass");
        }
        if (style != null) {
            w.writeAttribute("style", style, "style");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeEnd(final FacesContext context) throws IOException {
        final ResponseWriter w = context.getResponseWriter();
        w.endElement("div");
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(final String styleClass) {
        this.styleClass = styleClass;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
