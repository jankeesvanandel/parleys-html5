package com.parleys.server.frontend.repository;

import com.parleys.server.frontend.domain.Space;

import java.util.List;

/**
 *
 */
public interface SpacesRepository {
    List<Space> loadSpaces(int index, int paging);

    Space loadSpace(long spaceId);
}
