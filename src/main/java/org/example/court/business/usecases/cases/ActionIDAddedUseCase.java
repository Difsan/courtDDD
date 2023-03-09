package main.java.org.example.court.business.usecases.cases;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForEvent;
import main.java.org.example.court.domain.action.values.ActionID;
import main.java.org.example.court.domain.cases.Caso;
import main.java.org.example.court.domain.cases.events.ActionIDAdded;
import main.java.org.example.court.domain.cases.values.CaseID;
import main.java.org.example.court.domain.cases.values.FileID;
import main.java.org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ActionIDAddedUseCase implements UseCaseForEvent<ActionIDAdded> {
    private EventsRepository repository;

    public ActionIDAddedUseCase(EventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(ActionIDAdded domainEvent) {
        List<DomainEvent> fileEvents = repository.findByAggregatedRootId(domainEvent.getCaseID());
        Caso caso = Caso.from(CaseID.of(domainEvent.getCaseID()), fileEvents);
        caso.addActionIdToFile(CaseID.of(domainEvent.getCaseID()),
                FileID.of(domainEvent.getFileID()), ActionID.of(domainEvent.getActionID()));
        return caso.getUncommittedChanges().stream().map(event->repository.saveEvent(event)).collect(Collectors.toList());
    }
}
