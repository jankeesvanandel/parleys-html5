package com.parleys.server.domain.types;

/**
 * @author Stephan Janssen
 */
public enum MembershipType {

     // Default
    FREE(0),

    PAY_PER_TALK(1),

    SUBSCRIPTION(2),

    PAY_PER_TALK_AND_SUBSCRIPTION(3);

    private int value;

    MembershipType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
