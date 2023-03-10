package org.example.court.business.usecases.caso;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForEvent;
import org.example.court.domain.action.values.ActionID;
import org.example.court.domain.caso.Caso;
import org.example.court.domain.caso.events.ActionIDAdded;
import org.example.court.domain.caso.values.CasoID;
import org.example.court.domain.caso.values.FileID;
import org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class AddActionIDEventUseCase implements UseCaseForEvent<ActionIDAdded> {
    private EventsRepository repository;

    public AddActionIDEventUseCase(EventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(ActionIDAdded domainEvent) {
        List<DomainEvent> fileEvents = repository.findByAggregatedRootId(domainEvent.getCaseID());
        Caso caso = Caso.from(CasoID.of(domainEvent.getCaseID()), fileEvents);
        caso.addActionIdToFile(CasoID.of(domainEvent.getCaseID()),
                FileID.of(domainEvent.getFileID()), ActionID.of(domainEvent.getActionID()));
        return caso.getUncommittedChanges().stream().map(event->repository.saveEvent(event)).collect(Collectors.toList());
    }
}
