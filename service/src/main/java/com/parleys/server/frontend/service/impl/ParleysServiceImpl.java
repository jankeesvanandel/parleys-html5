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

import java.util.Collections;
import java.util.Comparator;
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
        final List<Space> spaces = spacesRepository.loadSpaces(index, paging);
        Collections.sort(spaces,
                new Comparator<Space>() {
                    @Override
                    public int compare(Space s1, Space s2) {
                        return s1.getName().compareToIgnoreCase(s2.getName());
                    }
                });
        return spaces;
    }

    @Override
    public Space loadSpace(long id) {
        return spacesRepository.loadSpace(id);
    }

    @Override
    public List<Channel> loadChannels(long spaceId) {
        final List<Channel> channels = channelsRepository.loadChannels(spaceId);
        Collections.sort(channels,
                new Comparator<Channel>() {
                    @Override
                    public int compare(Channel c1, Channel c2) {
                        return c1.getName().compareToIgnoreCase(c2.getName());
                    }
                });
        return channels;
    }

    @Override
    public Channel loadChannel(long channelId) {
        return channelsRepository.loadChannel(channelId);
    }

    @Override
    public List<Presentation> loadPresentations(long channelId, int index, int paging) {
        final List<Presentation> presentations = presentationsRepository.loadPresentations(channelId, index, paging);
        Collections.sort(presentations,
                new Comparator<Presentation>() {
                    @Override
                    public int compare(Presentation p1, Presentation p2) {
                        return p1.getTitle().compareToIgnoreCase(p2.getTitle());
                    }
                });
        return presentations;
    }

    @Override
    public Presentation loadPresentation(long presentationId) {
        return presentationsRepository.loadPresentation(presentationId);
    }
}
