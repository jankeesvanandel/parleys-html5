package com.parleys.server.dto;

/**
 * @author Stephan Janssen
 */
public class SimpleUserDTO extends AbstractDTO {

    /**
     *
     */
    private static final long serialVersionUID = -5936329913515033638L;

    private String username;

    private String firstName;

    private String lastName;

    // We always need the user image!
    private String thumbnailURL;

    /**
     * @return the username
     */
    public final String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public final void setUsername(final String username) {
        this.username = username;
    }

    /**
     * @return the firstName
     */
    public final String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public final void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public final String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public final void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(final String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
