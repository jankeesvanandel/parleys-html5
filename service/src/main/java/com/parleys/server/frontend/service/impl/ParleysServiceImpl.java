package com.parleys.server.frontend.service.impl;

import com.parleys.server.frontend.domain.*;
import com.parleys.server.frontend.repository.ChannelsRepository;
import com.parleys.server.frontend.repository.PresentationsRepository;
import com.parleys.server.frontend.repository.SpacesRepository;
import com.parleys.server.frontend.service.ParleysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public List<Presentation> loadPresentations(Filter thumbnailsFilter, Filter.Type thumbnailsFilterType, int index, int paging) {
        final List<Presentation> presentations = presentationsRepository.loadAllPresentations();
        while (presentations.size() > 6) {
            presentations.remove((int) (Math.random() * (double) presentations.size()));
        }
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
    public List<NewsItem> loadAllNewsItems() {
        return Arrays.asList(
                new NewsItem(24L, "iPhone, iPad Open Sourced", new Date(), "We're very excited to announce the immediate availability of three new Parleys.com Open Source Client projects for the iPhone, Android and iPad, hosted on Google Code! (this is not an April fools joke :)This has been made available through the great initiatives of following developers : Vincent Claeys (iPhone), David Van Droogenbroeck (Android) and Jan-Kees van Andel (JSF2 and HTML5).Read on..."),
                new NewsItem(13L, "test 2", new Date(), "test 2"),
                new NewsItem(53L, "test 3", new Date(), "test 3"),
                new NewsItem(64L, "test 4", new Date(), "test 4"),
                new NewsItem(95L, "test 5", new Date(), "test 5"),
                new NewsItem(16L, "test 6", new Date(), "test 6"),
                new NewsItem(73L, "test 7", new Date(), "test 7")
        );
    }

    @Override
    public Space loadRecommendedSpace() {
        //TODO: Yuck
        final List<Space> spaces = spacesRepository.loadAllSpaces();
        Collections.shuffle(spaces);
        return spaces.get(0);
    }

    @Override
    public Channel loadRecommendedChannel() {
        //TODO: Yuck
        final List<Channel> channels = channelsRepository.loadAllChannels();
        Collections.shuffle(channels);
        return channels.get(0);
    }

    @Override
    public Presentation loadRecommendedPresentation() {
        //TODO: Yuck
        final List<Presentation> presentations = presentationsRepository.loadAllPresentations();
        Collections.shuffle(presentations);
        return presentations.get(0);
    }
}
