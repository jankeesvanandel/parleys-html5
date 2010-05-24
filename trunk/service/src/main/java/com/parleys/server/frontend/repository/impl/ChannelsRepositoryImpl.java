package com.parleys.server.frontend.repository.impl;

import com.parleys.server.frontend.domain.Channel;
import com.parleys.server.frontend.domain.Space;
import com.parleys.server.frontend.repository.ChannelsRepository;
import com.parleys.server.frontend.repository.SpacesRepository;
import com.parleys.server.frontend.repository.cache.CachedRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Repository
public class ChannelsRepositoryImpl implements ChannelsRepository {

    private static final String URL = "http://www.parleys.com/parleysserver/rest/mobile/channels.form?id={id}";

    private final CachedRestTemplate<Channel> channelsTemplate;

    @Autowired
    public ChannelsRepositoryImpl(CachedRestTemplate<Channel> channelsTemplate) {
        this.channelsTemplate = channelsTemplate;
    }

    @Override
    public List<Channel> loadChannels(long spaceId) {
        Channel[] result = channelsTemplate.getForObject(URL, Channel[].class, spaceId);

        return Arrays.asList(result);
    }

    //TODO: get rid of this
    private SpacesRepository spacesRepository;

    @Autowired
    public void setSpacesRepository(SpacesRepository spacesRepository) {
        this.spacesRepository = spacesRepository;
    }

    @Override
    public Channel loadChannel(long channelId) {
        final List<Channel> channels = loadAllChannels();
        for (final Channel channel : channels) {
            if (channel.getId() == channelId) {
                return channel;
            }
        }

        return null;
    }

    @Override
    public List<Channel> loadAllChannels() {
        // TODO: Dirty way to load all channels
        final List<Space> spaces = spacesRepository.loadSpaces(0, 100);
        final List<Channel> ret = new ArrayList<Channel>();
        for (Space space : spaces) {
            final List<Channel> channels = loadChannels(space.getId());
            ret.addAll(channels);
        }

        return ret;
    }
}
