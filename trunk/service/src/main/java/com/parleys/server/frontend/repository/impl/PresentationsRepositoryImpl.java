package com.parleys.server.frontend.repository.impl;

import com.parleys.server.frontend.domain.*;
import com.parleys.server.frontend.repository.ChannelsRepository;
import com.parleys.server.frontend.repository.PresentationsRepository;
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
public class PresentationsRepositoryImpl implements PresentationsRepository {

    private static final String PRESENTATIONS_URL = "http://www.parleys.com/parleysserver/rest/mobile/presentations.form?id={channelId}&index={index}&paging={paging}";

    private static final String ASSETS_URL = "http://www.parleys.com/parleysserver/rest/mobile/assets.form?id={presentationId}";

    private final CachedRestTemplate<Presentation> template;

    @Autowired
    public PresentationsRepositoryImpl(CachedRestTemplate<Presentation> template) {
        this.template = template;
    }

    @Override
    public List<Presentation> loadPresentationsForChannel(long channelId, int index, int paging) {
        Presentation[] result = template.getForObject(PRESENTATIONS_URL, Presentation[].class, channelId, index, paging);

        return Arrays.asList(result);
    }

    @Override
    public List<Presentation> loadPresentationsForFilter(Filter filter) {
        final List<Presentation> list = loadAllPresentations();
        return list.subList(0, Math.max(5, list.size()));
    }

    //TODO: get rid of this
    private ChannelsRepository channelsRepository;

    @Autowired
    public void setChannelsRepository(ChannelsRepository channelsRepository) {
        this.channelsRepository = channelsRepository;
    }

    //TODO: get rid of this
    private SpacesRepository spacesRepository;

    @Autowired
    public void setSpacesRepository(SpacesRepository spacesRepository) {
        this.spacesRepository = spacesRepository;
    }

    @Override
    public Presentation loadPresentation(long presentationId) {
        // TODO: REST service for a "Presentation by id"
        final List<Space> spaces = spacesRepository.loadAllSpaces();
        for (Space space : spaces) {
            final List<Channel> channels = channelsRepository.loadChannels(space.getId());
            for (final Channel channel : channels) {
//                if (channel.getId() != 18821) {
                final List<Presentation> presentations = loadPresentationsForChannel(channel.getId(), 0, 10);
                for (Presentation presentation : presentations) {
                    if (presentation.getId() == presentationId) {
                        final List<Asset> assets = loadAssets(presentationId);

                        presentation.setAssets(assets);
                        return presentation;
                    }
                }
//                }
            }
        }

        return null;
    }

    @Override
    public List<Presentation> loadAllPresentations() {
        // TODO: REST service for a "Presentation by id"
        final List<Space> spaces = spacesRepository.loadSpaces(0, 3);
        final List<Presentation> ret = new ArrayList<Presentation>();
        for (Space space : spaces) {
            final List<Channel> channels = channelsRepository.loadChannels(space.getId());
            int i = 0; // Hack to reduce load on parleys server
            for (final Channel channel : channels) {
//                System.out.println("channel.getId() = " + channel.getId());
//                if (channel.getId() == 18821 || channel.getId() == 18821) {
//                    continue;
//                }
                if (i++ > 3) {
                    break;
                }
                final List<Presentation> presentations = loadPresentationsForChannel(channel.getId(), 0, 5);
                for (Presentation presentation : presentations) {
                    final List<Asset> assets = loadAssets(presentation.getId());

                    presentation.setAssets(assets);
                }
                ret.addAll(presentations);
            }
        }
        return ret;
    }

    private List<Asset> loadAssets(long presentationId) {
        Asset[] result = template.getForObject(ASSETS_URL, Asset[].class, presentationId);

        return Arrays.asList(result);
    }
}
