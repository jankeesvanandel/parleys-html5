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
package com.parleys.server.frontend.web.jsf.util;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * Factory for our custom ParleysExceptionHandler implementation.
 *
 * @author Jan-Kees van Andel
 */
public class ParleysExceptionHandlerFactory extends ExceptionHandlerFactory {

    private final ExceptionHandlerFactory wrapped;

    /**
     * Full constructor. Called by JSF.
     *
     * @param wrapped The wrapped ExceptionHandlerFactory implementation.
     */
    public ParleysExceptionHandlerFactory(ExceptionHandlerFactory wrapped) {
        this.wrapped = wrapped;
    }

    /** {@inheritDoc} */
    @Override
    public ExceptionHandler getExceptionHandler() {
        ExceptionHandler wrappedExceptionHandler = wrapped.getExceptionHandler();
        return new ParleysExceptionHandler(wrappedExceptionHandler);
    }
}
