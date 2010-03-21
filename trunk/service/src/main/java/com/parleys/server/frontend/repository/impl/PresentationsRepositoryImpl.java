package com.parleys.server.frontend.repository.impl;

import com.parleys.server.frontend.domain.Channel;
import com.parleys.server.frontend.domain.Presentation;
import com.parleys.server.frontend.domain.Space;
import com.parleys.server.frontend.repository.ChannelsRepository;
import com.parleys.server.frontend.repository.PresentationsRepository;
import com.parleys.server.frontend.repository.SpacesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;

/**
 *
 */
@Repository
public class PresentationsRepositoryImpl implements PresentationsRepository {

    private final RestTemplate template;

    private final PresentationsJSONConverter jsonConverter;

    @Autowired
    public PresentationsRepositoryImpl(RestTemplate template, PresentationsJSONConverter jsonConverter) {
        this.template = template;
        this.jsonConverter = jsonConverter;
    }

    @Override
    public List<Presentation> loadPresentations(long channelId, int index, int paging) {
        final String url = "http://www.parleys.com/parleysserver/rest/mobile/presentations.form?id={channelId}&index={index}&paging={paging}";
        final String resultString = template.getForObject(url, String.class, channelId, index, paging);
        final List<Presentation> channels = jsonConverter.getObject(resultString);

        return channels;
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
        final List<Space> spaces = spacesRepository.loadSpaces(0, 1000);
        for (Space space : spaces) {
            final List<Channel> channels = channelsRepository.loadChannels(space.getId());
            for (final Channel channel : channels) {
                final List<Presentation> presentations = loadPresentations(channel.getId(), 0, 1000);
                for (Presentation presentation : presentations) {
                    if (presentation.getId() == presentationId) {
                        return presentation;
                    }
                }
            }
        }

        return null;
    }
}
