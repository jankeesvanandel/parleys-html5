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
package com.parleys.server.frontend.web.html5.util;

import com.parleys.server.common.exception.AuthorizationException;
import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.application.ProjectStage;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * Our custom ParleysExceptionHandler implementation.
 *
 * Deals nicely with all exceptions that may occur during a request.
 *
 * @author Jan-Kees van Andel
 */
public class ParleysExceptionHandler extends ExceptionHandlerWrapper {

    private static final Logger LOGGER = Logger.getLogger(ParleysExceptionHandler.class);

    private final ExceptionHandler wrapped;

    /**
     * Full constructor.
     *
     * @param wrapped The wrapped ExceptionHandler. Called by JSF.
     */
    public ParleysExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    /** {@inheritDoc} */
    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    /** {@inheritDoc} */
    @Override
    public void handle() {
        Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionEventsIterator();
        while (iterator.hasNext()) {
            Throwable throwable = getException(iterator);
            try {
                handleException(throwable);
            } finally {
                iterator.remove();
            }
        }
        super.handle();
    }

    private Iterator<ExceptionQueuedEvent> getUnhandledExceptionEventsIterator() {
        Iterable<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents();
        return events.iterator();
    }

    private void handleException(Throwable throwable) {
        FacesMessage message;
        Throwable cause;
        if ((cause = getExceptionOfType(throwable, AuthorizationException.class)) != null) {
            message = getAuthorizationExceptionMessage((AuthorizationException) cause);
        } else if ((cause = getExceptionOfType(throwable, ViewExpiredException.class)) != null) {
            message = getViewExpiredExceptionMessage((ViewExpiredException) cause);
        } else {
            message = getDefaultExceptionMessage(throwable);
        }

        showErrorPage(message);
    }

    private <T extends Throwable> T getExceptionOfType(Throwable throwable, Class<T> clazz) {
        do {
            if (clazz.isAssignableFrom(throwable.getClass())) {
                return clazz.cast(throwable);
            }
        } while ((throwable = throwable.getCause()) != null);

        return null;
    }

    private Throwable getDeepestNestedException(Throwable throwable) {
        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }

        return throwable;
    }

    private FacesMessage getAuthorizationExceptionMessage(AuthorizationException e) {
        String message = "You are not authorized to view this resource.";
        String details = "";
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("AuthorizationException caught", e);
        }

        return new FacesMessage(FacesMessage.SEVERITY_ERROR, message, details);
    }

    private FacesMessage getViewExpiredExceptionMessage(ViewExpiredException e) {
        String message = "Your session has expired.";
        String details = "You were on page " + e.getViewId();
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("ViewExpiredException caught", e);
        }

        return new FacesMessage(FacesMessage.SEVERITY_ERROR, message, details);
    }

    private FacesMessage getDefaultExceptionMessage(Throwable t) {
        String message = "An unexpected error occurred.";
        String details;
        if (JSFUtil.fc().isProjectStage(ProjectStage.Development)) {
            LOGGER.error("Unexpected exception", t);
            details = getDeepestNestedException(t).getMessage();
        } else {
            String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String randomNumber = Integer.toString((int) (Math.random() * 100000.0));
            String code = today + "-" + randomNumber;

            LOGGER.error("Unexpected exception with code: '" + code + "'", t);
            details = "This code identifies your error. If you file an issue, please mention it: " + code;
        }

        return new FacesMessage(FacesMessage.SEVERITY_ERROR, message, details);
    }

    private void showErrorPage(FacesMessage message) {
        FacesContext facesContext = JSFUtil.fc();
        try {
            Flash flash = facesContext.getExternalContext().getFlash();
            flash.put("errorSummary", message.getSummary());
            flash.put("errorDetail", message.getDetail());

            facesContext.getExternalContext().redirect("error.xhtml");
        } catch (IOException e) {
            LOGGER.error("Error redirecting to error page", e);
        }
        facesContext.responseComplete();
    }

    private Throwable getException(Iterator<ExceptionQueuedEvent> iterator) {
        ExceptionQueuedEvent event = iterator.next();
        ExceptionQueuedEventContext context = event.getContext();
        return context.getException();
    }
}
