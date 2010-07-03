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
@FacesComponent("com.parleys.server.frontend.web.jsf.components.html5.UIDiv")
public class UIDiv extends UIComponentBase {
    public static final String FAMILY = "com.parleys.server.frontend.web.jsf.components.html5";

    @Override
    public String getFamily() {
        return FAMILY;
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        final ResponseWriter w = context.getResponseWriter();
        w.write("<div id=\"");
        w.write(getClientId(context));
        w.write("\">");
    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        final ResponseWriter w = context.getResponseWriter();
        w.write("</div>");
    }

}