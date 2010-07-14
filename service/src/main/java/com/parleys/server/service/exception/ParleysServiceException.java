package com.parleys.server.service.exception;

import org.apache.log4j.Logger;

/**
 * @author Stephan Janssen
 */
public class ParleysServiceException extends Exception {

    private static final Logger LOG = Logger.getLogger(ParleysServiceException.class);

    public ParleysServiceException(final String message) {
        super(message);
        LOG.error(message);
    }
}
