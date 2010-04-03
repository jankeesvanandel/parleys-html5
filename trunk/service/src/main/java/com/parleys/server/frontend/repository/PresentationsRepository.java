package com.parleys.server.frontend.repository;

import com.parleys.server.frontend.domain.Presentation;
import com.parleys.server.frontend.domain.Space;

import java.util.List;

/**
 *
 */
public interface PresentationsRepository {
    List<Presentation> loadPresentations(long channelId, int index, int paging);

    Presentation loadPresentation(long presentationId);

    List<Presentation> loadAllPresentations();
}
