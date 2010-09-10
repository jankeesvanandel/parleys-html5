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

import com.parleys.server.frontend.web.shared.util.JSFUtil;

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
@FacesComponent("com.parleys.jsf.components.html5.UIImage")
public class UIImage extends UIOutput {

    private static final String FAMILY = "com.parleys.jsf.components.html5";

    private String styleClass;

    private String style;
    private String src;
    private String alt;
    private String width;
    private String height;
    private String border;

    public UIImage() {
        setRendererType(null); // Override rendererType set by UIOutput to prevent annoying WARNINGs.
    }

    /** {@inheritDoc} */
    @Override
    public String getFamily() {
        return FAMILY;
    }

    /** {@inheritDoc} */
    @Override
    public void encodeEnd(final FacesContext context) throws IOException {
        super.encodeEnd(context);
        final ResponseWriter w = context.getResponseWriter();
        w.startElement("img", this);
        w.writeAttribute("id", getClientId(context), "id");

        JSFUtil.writeComponentAttribute(this, context, "class", styleClass);
        JSFUtil.writeComponentAttribute(this, context, "style", style);
        JSFUtil.writeComponentAttribute(this, context, "src", src);
        JSFUtil.writeComponentAttribute(this, context, "alt", alt);
        JSFUtil.writeComponentAttribute(this, context, "width", width);
        JSFUtil.writeComponentAttribute(this, context, "height", height);
        JSFUtil.writeComponentAttribute(this, context, "border", border);

        w.endElement("img");
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

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }
}
