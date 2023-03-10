package org.example.court.business.commons;

import org.example.court.generic.DomainEvent;

import java.util.List;

public interface EventsRepository {
    DomainEvent saveEvent(DomainEvent event);

    List<DomainEvent> findByAggregatedRootId(String aggregatedRootId);
}
