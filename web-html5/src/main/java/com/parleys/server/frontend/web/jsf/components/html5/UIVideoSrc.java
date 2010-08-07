package com.parleys.server.frontend.web.jsf.components.html5;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;

/**
 * The source tag that can be embedded in the video tag.
 *
 * @author Jan-Kees van Andel
 */
@FacesComponent("com.parleys.server.frontend.web.jsf.components.html5.UIVideoSrc")
public class UIVideoSrc extends UIComponentBase {
    public static final String FAMILY = UIVideo.FAMILY;

    public String source;

    public String type;

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
    public void encodeEnd(final FacesContext context) throws IOException {
        final ResponseWriter w = context.getResponseWriter();
        w.write("<source");
        writeIfNotNull("src", source, w);
        writeIfNotNull("type", type, w);

        w.write("/>");
    }

    private void writeIfNotNull(final String attrName,
                                final Object value,
                                final ResponseWriter w) throws IOException {
        if (value != null) {
            w.write(" " + attrName + "=\"" + value + "\"");
        }
    }

    public String getSource() {
        return source;
    }

    public void setSource(final String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }
}
