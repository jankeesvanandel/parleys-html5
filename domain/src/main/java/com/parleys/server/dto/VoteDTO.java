package com.parleys.server.dto;

import com.parleys.server.domain.types.VoteType;

/**
 * @author Stephan Janssen
 */
public class VoteDTO extends AbstractDTO {

    private VoteType value;

    private SimpleUserDTO user;

    /**
     *
     */
    private static final long serialVersionUID = 7513460844503696497L;

    /**
     * @return the value
     */
    public final VoteType getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public final void setValue(final VoteType value) {
        this.value = value;
    }

    /**
     * @return the user
     */
    public final SimpleUserDTO getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public final void setUser(final SimpleUserDTO user) {
        this.user = user;
    }
}
