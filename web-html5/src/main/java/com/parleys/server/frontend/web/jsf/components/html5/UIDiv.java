package com.parleys.server.frontend.web.jsf.components.html5;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
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
        w.write("\">");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void encodeEnd(final FacesContext context) throws IOException {
        final ResponseWriter w = context.getResponseWriter();
        w.write("</div>");
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(final String styleClass) {
        this.styleClass = styleClass;
    }
}