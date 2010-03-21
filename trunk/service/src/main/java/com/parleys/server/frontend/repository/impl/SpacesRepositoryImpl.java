package com.parleys.server.frontend.repository.impl;

import com.parleys.server.frontend.domain.Space;
import com.parleys.server.frontend.repository.SpacesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Repository
public class SpacesRepositoryImpl implements SpacesRepository {

    private final RestTemplate spacesTemplate;

    private final SpacesJSONConverter spacesJSONConverter;

    @Autowired
    public SpacesRepositoryImpl(RestTemplate spacesTemplate, SpacesJSONConverter spacesJSONConverter) {
        this.spacesTemplate = spacesTemplate;
        this.spacesJSONConverter = spacesJSONConverter;
    }

    @Override
    public List<Space> loadSpaces(int index, int paging) {
        final String url = "http://www.parleys.com/parleysserver/rest/mobile/spaces.form?index={index}&paging={paging}";
        final String resultString = spacesTemplate.getForObject(url, String.class, index, paging);
        final List<Space> spaces = spacesJSONConverter.getObject(resultString);

        return spaces;
    }

    @Override
    public Space loadSpace(long spaceId) {
        // TODO: Dirty way to load all spaces
        final List<Space> spaces = loadSpaces(0, 1000);
        for (Space space : spaces) {
            if (space.getId() == spaceId) {
                return space;
            }
        }
        return null;
    }
}
