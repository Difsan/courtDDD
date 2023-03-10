package org.example.court.business.commons;

import org.example.court.generic.DomainEvent;

import java.util.List;

public interface UseCaseForEvent<T extends DomainEvent>{
    List<DomainEvent> apply(T domainEvent);
}
