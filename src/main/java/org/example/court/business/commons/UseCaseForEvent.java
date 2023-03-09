package main.java.org.example.court.business.commons;

import main.java.org.example.court.generic.DomainEvent;

import java.util.List;

public interface UseCaseForEvent<T extends DomainEvent>{
    List<DomainEvent> apply(T domainEvent);
}
