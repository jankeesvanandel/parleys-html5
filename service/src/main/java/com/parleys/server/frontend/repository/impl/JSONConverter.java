package com.parleys.server.frontend.repository.impl;

/**
 *
 */
public interface JSONConverter<T> {
    T getObject(String source);
}
