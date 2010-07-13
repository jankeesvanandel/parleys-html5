package com.parleys.server.service.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Stephan Janssen
 */
public class ParleysServiceException extends Exception {

    private final transient Log LOG = LogFactory.getLog(getClass());

    public ParleysServiceException(final String message) {
        super(message);
        LOG.error(message);
    }
}
