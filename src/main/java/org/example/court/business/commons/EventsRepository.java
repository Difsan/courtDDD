package main.java.org.example.court.business.commons;

import main.java.org.example.court.generic.DomainEvent;

import java.util.List;

public interface EventsRepository {
    DomainEvent saveEvent(DomainEvent event);

    List<DomainEvent> findByAggregatedRootId(String aggregatedRootId);
}
