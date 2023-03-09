package main.java.org.example.court.business.cases;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForEvent;
import main.java.org.example.court.domain.action.values.ActionID;
import main.java.org.example.court.domain.cases.Caso;
import main.java.org.example.court.domain.cases.events.ActionIDAdded;
import main.java.org.example.court.domain.cases.events.DecisionIDAdded;
import main.java.org.example.court.domain.cases.values.CaseID;
import main.java.org.example.court.domain.cases.values.FileID;
import main.java.org.example.court.domain.decision.values.DecisionID;
import main.java.org.example.court.generic.DomainEvent;

import java.util.List;
import java.util.stream.Collectors;

public class DecisionIDAddedUseCase implements UseCaseForEvent<DecisionIDAdded> {
    private EventsRepository repository;

    public DecisionIDAddedUseCase(EventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(DecisionIDAdded domainEvent) {
        List<DomainEvent> fileEvents = repository.findByAggregatedRootId(domainEvent.getCaseID());
        Caso caso = Caso.from(CaseID.of(domainEvent.getCaseID()), fileEvents);
        caso.addDecisionIdToFile(CaseID.of(domainEvent.getCaseID()),
                FileID.of(domainEvent.getFileID()), DecisionID.of(domainEvent.getDecisionID()));
        return caso.getUncommittedChanges().stream().map(event->repository.saveEvent(event)).collect(Collectors.toList());
    }
}
