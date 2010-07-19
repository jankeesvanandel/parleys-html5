package com.parleys.server.dto;

/**
 * @author Stephan Janssen
 */
public class NoteDetailsDTO extends AbstractDTO {

    /**
     *
     */
    private static final long serialVersionUID = -8059773735770985857L;

    private String text;

    private Integer cuePoint;

    private boolean isPublicNote;

    private SimpleUserDTO user;

    /**
     * @return the text
     */
    public final String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public final void setText(final String text) {
        this.text = text;
    }

    /**
     * @return the cuePoint
     */
    public final Integer getCuePoint() {
        return cuePoint;
    }

    /**
     * @param cuePoint the cuePoint to set
     */
    public final void setCuePoint(final Integer cuePoint) {
        this.cuePoint = cuePoint;
    }

    /**
     * @return the isPublicNote
     */
    public final boolean isPublicNote() {
        return isPublicNote;
    }

    /**
     * @param isPublicNote the isPublicNote to set
     */
    public final void setPublicNote(final boolean isPublicNote) {
        this.isPublicNote = isPublicNote;
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
