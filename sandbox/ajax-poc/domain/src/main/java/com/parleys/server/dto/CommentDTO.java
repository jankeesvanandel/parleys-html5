package com.parleys.server.dto;

/**
 * @author Stephan Janssen
 */
public class CommentDTO extends AbstractDTO {
    /**
     *
     */
    private static final long serialVersionUID = 6266929465269600779L;

    private Long parent;

    private String text;

    private Integer cuePoint;

    private SimpleUserDTO user;

    private int thumbUpVote;

    /**
     * This holds the total number of thumbDown votes
     */
    private int thumbDownVote;

    private boolean spam;

    private int spamVotes;

    private boolean informReply;

    /**
     * @param parent the parent to set
     */
    public void setParent(final Long parent) {
        this.parent = parent;
    }

    /**
     * @return the parent
     */
    public Long getParent() {
        return parent;
    }

    /**
     * @param text the text to set
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param cuePoint the cuePoint to set
     */
    public void setCuePoint(final Integer cuePoint) {
        this.cuePoint = cuePoint;
    }

    /**
     * @return the cuePoint
     */
    public Integer getCuePoint() {
        return cuePoint;
    }

    /**
     * @param user the user to set
     */
    public void setUser(final SimpleUserDTO user) {
        this.user = user;
    }

    /**
     * @return the user
     */
    public SimpleUserDTO getUser() {
        return user;
    }

    /**
     * @param thumbUpVote the thumbUpVote to set
     */
    public void setThumbUpVote(final int thumbUpVote) {
        this.thumbUpVote = thumbUpVote;
    }

    /**
     * @return the thumbUpVote
     */
    public int getThumbUpVote() {
        return thumbUpVote;
    }

    /**
     * @param thumbDownVote the thumbDownVote to set
     */
    public void setThumbDownVote(final int thumbDownVote) {
        this.thumbDownVote = thumbDownVote;
    }

    /**
     * @return the thumbDownVote
     */
    public int getThumbDownVote() {
        return thumbDownVote;
    }

    /**
     * @param spam the spam to set
     */
    public void setSpam(final boolean spam) {
        this.spam = spam;
    }

    /**
     * @return the spam
     */
    public boolean isSpam() {
        return spam;
    }

    /**
     * @param spamVotes the spamVotes to set
     */
    public void setSpamVotes(final int spamVotes) {
        this.spamVotes = spamVotes;
    }

    /**
     * @return the spamVotes
     */
    public int getSpamVotes() {
        return spamVotes;
    }

    public boolean isInformReply() {
        return informReply;
    }

    public void setInformReply(final boolean informReply) {
        this.informReply = informReply;
    }
}
