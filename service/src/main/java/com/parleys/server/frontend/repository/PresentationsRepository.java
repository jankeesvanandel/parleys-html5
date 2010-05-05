package com.parleys.server.frontend.repository;

import com.parleys.server.frontend.domain.Filter;
import com.parleys.server.frontend.domain.Presentation;

import java.util.List;

/**
 *
 */
public interface PresentationsRepository {
    List<Presentation> loadPresentationsForChannel(long channelId, int index, int paging);

    Presentation loadPresentation(long presentationId);

    List<Presentation> loadAllPresentations();

    List<Presentation> loadPresentationsForFilter(Filter filter);
}
