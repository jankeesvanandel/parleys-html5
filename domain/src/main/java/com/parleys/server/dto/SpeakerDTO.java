package com.parleys.server.dto;

/**
 * @author Stephan Janssen
 */
public class SpeakerDTO extends AbstractDTO {

    private static final long serialVersionUID = 2360554629906075291L;

    private String firstName;

    private String lastName;

    private String nickName;

    private String email;

    private boolean featured;

    private String bio;

    private Long userId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(final String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @param featured the featured to set
     */
    public void setFeatured(final boolean featured) {
	    this.featured = featured;
    }

    /**
     * @return the featured
     */
    public boolean isFeatured() {
	    return featured;
    }

	/**
	 * @param bio the bio to set
	 */
	public void setBio(final String bio) {
		this.bio = bio;
	}

	/**
	 * @return the bio
	 */
	public String getBio() {
		return bio;
	}

	/**
	 * @return the userId (null if no user yet).
	 */
	public final Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public final void setUserId(final Long userId) {
		this.userId = userId;
	}

    public final String getName() {
        return firstName + " " + lastName;
    }
}
