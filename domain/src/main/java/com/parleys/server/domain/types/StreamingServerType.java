package com.parleys.server.domain.types;

/**
 * The different supported streaming server types.
 * @author Stephan Janssen
 */
@SuppressWarnings("unused")
public enum StreamingServerType {
    RED5,

    FMS,

    WOWZA,

    CLOUDFRONT,

    OTHER
}
