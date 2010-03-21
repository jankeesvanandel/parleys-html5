package com.parleys.server.frontend.repository.impl;

import com.parleys.server.frontend.domain.Channel;
import com.parleys.server.frontend.domain.Space;
import com.parleys.server.frontend.repository.ChannelsRepository;
import com.parleys.server.frontend.repository.SpacesRepository;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Repository
public class ChannelsRepositoryImpl implements ChannelsRepository {

    private final RestTemplate channelsTemplate;

    private final ChannelsJSONConverter channelsJSONConverter;

    @Autowired
    public ChannelsRepositoryImpl(RestTemplate channelsTemplate, ChannelsJSONConverter channelsJSONConverter) {
        this.channelsTemplate = channelsTemplate;
        this.channelsJSONConverter = channelsJSONConverter;
    }

    @Override
    public List<Channel> loadChannels(long spaceId) {
        final String url = "http://www.parleys.com/parleysserver/rest/mobile/channels.form?id={id}";
        final String resultString = channelsTemplate.getForObject(url, String.class, spaceId);
        final List<Channel> channels = channelsJSONConverter.getObject(resultString);

        return channels;
    }

    //TODO: get rid of this
    private SpacesRepository spacesRepository;
    @Autowired
    public void setSpacesRepository(SpacesRepository spacesRepository) {
        this.spacesRepository = spacesRepository;
    }

    @Override
    public Channel loadChannel(long channelId) {
        // TODO: Dirty way to load all channels
        final List<Space> spaces = spacesRepository.loadSpaces(0, 1000);
        for (Space space : spaces) {
            final List<Channel> channels = loadChannels(space.getId());
            for (final Channel channel : channels) {
                if (channel.getId() == channelId) {
                    return channel;
                }
            }
        }

        return null;
    }
}