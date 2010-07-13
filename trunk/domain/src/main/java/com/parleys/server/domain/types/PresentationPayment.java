package com.parleys.server.domain.types;

/**
 * @author Stephan Janssen
 */
public enum PresentationPayment {

    FREE(0),

    NOT_FREE(1);

    private int value;

    PresentationPayment(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
