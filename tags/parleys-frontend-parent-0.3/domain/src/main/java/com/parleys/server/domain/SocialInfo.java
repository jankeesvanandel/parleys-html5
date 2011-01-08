/*
 * Copyright (C) 2010 Parleys.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.parleys.server.domain;

import java.io.Serializable;

/**
 * @author Stephan Janssen
 */
public class SocialInfo implements Serializable {

    private static final long serialVersionUID = -5960051913325804215L;

    /*
     * This holds the total number of thumbUp votes.
     * Note: that the total number of thumb votes is of course ThumbUp + ThumbDown  ;)
     */
    private int thumbUpVote;

    /*
     * This holds the total number of thumbDown votes.
     */
    private int thumbDownVote;

    private boolean spam = false;

    private int spamVotes;

    public final int getThumbUpVote() {
        return thumbUpVote;
    }

    /**
     * This increments the "Thumb Up" vote with one.
     * The total thumb votes needs to be incremented by one.
     */
    public final void incrementThumbUpVote() {
        thumbUpVote++;
    }

    public void setThumbUpVote(final int thumbUpVote) {
        this.thumbUpVote = thumbUpVote;
    }

    public final int getThumbDownVote() {
        return thumbDownVote;
    }

    public final void incrementThumbDownVote() {
        thumbDownVote++;
    }

    public void setThumbDownVote(final int thumbDownVote) {
        this.thumbDownVote = thumbDownVote;
    }

    public final boolean isSpam() {
        return spam;
    }

    /**
     * The SpamManager (which will get triggered by Quartz) will check
     * which content item has X spam votes, once a given threshold has been
     * reached the content item can be flagged as spam.
     *
     * @param value true if item has been marked as spam
     */
    public final void setSpam(final boolean value) {
        spam = value;
    }

    public final int getSpamVotes() {
        return spamVotes;
    }

    public final void setSpamVotes(final int value) {
        spamVotes = value;
    }
}
