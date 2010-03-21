package com.parleys.server.frontend.service.impl;

import com.parleys.server.frontend.domain.Channel;
import com.parleys.server.frontend.domain.Presentation;
import com.parleys.server.frontend.domain.Space;
import com.parleys.server.frontend.repository.ChannelsRepository;
import com.parleys.server.frontend.repository.PresentationsRepository;
import com.parleys.server.frontend.repository.SpacesRepository;
import com.parleys.server.frontend.service.ParleysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service("parleysService")
public class ParleysServiceImpl implements ParleysService {

    private final SpacesRepository spacesRepository;
    private final ChannelsRepository channelsRepository;
    private final PresentationsRepository presentationsRepository;

    @Autowired
    public ParleysServiceImpl(SpacesRepository spacesRepository, ChannelsRepository channelsRepository, PresentationsRepository presentationsRepository) {
        this.spacesRepository = spacesRepository;
        this.channelsRepository = channelsRepository;
        this.presentationsRepository = presentationsRepository;
    }

    /** {@inheritDoc} */
    @Override
    public List<Space> loadSpaces(int index, int paging) {
        return spacesRepository.loadSpaces(index, paging);
    }

    @Override
    public Space loadSpace(long id) {
        return spacesRepository.loadSpace(id);
    }

    @Override
    public List<Channel> loadChannels(long spaceId) {
        return channelsRepository.loadChannels(spaceId);
    }

    @Override
    public Channel loadChannel(long channelId) {
        return channelsRepository.loadChannel(channelId);
    }

    @Override
    public List<Presentation> loadPresentations(long channelId, int index, int paging) {
        return presentationsRepository.loadPresentations(channelId, index, paging);
    }

    @Override
    public Presentation loadPresentation(long presentationId) {
        return presentationsRepository.loadPresentation(presentationId);
    }
}
