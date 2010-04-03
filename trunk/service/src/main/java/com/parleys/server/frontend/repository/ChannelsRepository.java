package com.parleys.server.frontend.repository;

import com.parleys.server.frontend.domain.Channel;
import com.parleys.server.frontend.domain.Space;

import java.util.List;

/**
 *
 */
public interface ChannelsRepository {
    List<Channel> loadChannels(long spaceId);

    Channel loadChannel(long channelId);

    List<Channel> loadAllChannels();
}
