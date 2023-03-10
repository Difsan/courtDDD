package main.java.org.example.court.business.usecases.caso;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForEvent;
import main.java.org.example.court.domain.caso.Caso;
import main.java.org.example.court.domain.caso.events.DecisionIDAdded;
import main.java.org.example.court.domain.caso.values.CasoID;
import main.java.org.example.court.domain.caso.values.FileID;
import main.java.org.example.court.domain.decision.values.DecisionID;
import main.java.org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class DecisionIDAddedUseCase implements UseCaseForEvent<DecisionIDAdded> {
    private EventsRepository repository;

    public DecisionIDAddedUseCase(EventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(DecisionIDAdded domainEvent) {
        List<DomainEvent> fileEvents = repository.findByAggregatedRootId(domainEvent.getCaseID());
        Caso caso = Caso.from(CasoID.of(domainEvent.getCaseID()), fileEvents);
        caso.addDecisionIdToFile(CasoID.of(domainEvent.getCaseID()),
                FileID.of(domainEvent.getFileID()), DecisionID.of(domainEvent.getDecisionID()));
        return caso.getUncommittedChanges().stream().map(event->repository.saveEvent(event)).collect(Collectors.toList());
    }
}