package com.parleys.server.frontend.repository.impl;

import com.parleys.server.frontend.domain.Space;
import com.parleys.server.frontend.repository.SpacesRepository;
import com.parleys.server.frontend.repository.cache.CachedRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Repository
public class SpacesRepositoryImpl implements SpacesRepository {

    private static final String URL = "http://www.parleys.com/parleysserver/rest/mobile/spaces.form?index={index}&paging={paging}";

    private final CachedRestTemplate<Space> spacesTemplate;

    @Autowired
    public SpacesRepositoryImpl(CachedRestTemplate<Space> spacesTemplate) {
        this.spacesTemplate = spacesTemplate;
    }

    @Override
    public List<Space> loadSpaces(int index, int paging) {
        Space[] result = spacesTemplate.getForObject(URL, Space[].class, index, paging);

        return Arrays.asList(result);
    }

    @Override
    public Space loadSpace(long spaceId) {
        // TODO: Dirty way to load all spaces
        final List<Space> spaces = loadAllSpaces();
        for (Space space : spaces) {
            if (space.getId() == spaceId) {
                return space;
            }
        }
        return null;
    }

    @Override
    public List<Space> loadAllSpaces() {
        return loadSpaces(0, 1000);
    }
}
