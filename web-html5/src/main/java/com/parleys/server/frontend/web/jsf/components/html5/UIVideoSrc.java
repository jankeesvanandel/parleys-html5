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

    private String source;

    private String type;

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
