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
package com.parleys.server.frontend.service;

/**
 * Holder class for parameters to query presentations lists.
 *
 * @author Jan-Kees van Andel
 */
public class PresentationsCriteria {

    private Long channelId;

    private int index = 0;

    private int paging = 20;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PresentationsCriteria)) return false;

        PresentationsCriteria that = (PresentationsCriteria) o;

        return index == that.index &&
               paging == that.paging &&
               !(channelId != null ? !channelId.equals(that.channelId) : that.channelId != null);
    }

    @Override
    public int hashCode() {
        int result = channelId != null ? channelId.hashCode() : 0;
        result = 31 * result + index;
        result = 31 * result + paging;
        return result;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(final Long channelId) {
        this.channelId = channelId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(final int index) {
        this.index = index;
    }

    public int getPaging() {
        return paging;
    }

    public void setPaging(final int paging) {
        this.paging = paging;
    }
}
