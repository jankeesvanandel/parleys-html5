package com.parleys.server.frontend.web.jsf.components.html5;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;

/**
 *
 */
@FacesComponent("com.parleys.server.frontend.web.jsf.components.html5.UIVideoSrc")
public class UIVideoSrc extends UIComponentBase {
    public static final String FAMILY = UIVideo.FAMILY;

    public String source;

    public String type;

    @Override
    public String getFamily() {
        return FAMILY;
    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        final ResponseWriter w = context.getResponseWriter();
        w.write("<source");
        writeIfNotNull("src", source, w);
        writeIfNotNull("type", type, w);

        w.write("/>");
    }

    private void writeIfNotNull(String attrName, Object value, ResponseWriter w) throws IOException {
        if (value != null) {
            w.write(" " + attrName + "=\"" + value + "\"");
        }
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}