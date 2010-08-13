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
package com.parleys.server.frontend.web.jsf.components.html5;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;

/**
 * Simple div component. Renders a simple div.
 *
 * The addition of the component is the ability to set te "rendered" attribute.
 *
 * @author Jan-Kees van Andel
 */
@FacesComponent("com.parleys.server.frontend.web.jsf.components.html5.UIDiv")
public class UIDiv extends UIOutput {

    private static final String FAMILY = "com.parleys.server.frontend.web.jsf.components.html5";

    private String styleClass;

    private String style;

    public UIDiv() {
        setRendererType(null); // Override rendererType set by UIOutput to prevent annoying WARNINGs.
    }

    /** {@inheritDoc} */
    @Override
    public String getFamily() {
        return FAMILY;
    }

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
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
