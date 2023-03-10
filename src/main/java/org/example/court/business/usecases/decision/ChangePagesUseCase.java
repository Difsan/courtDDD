package main.java.org.example.court.business.usecases.decision;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForCommand;
import main.java.org.example.court.domain.commonValues.Pages;
import main.java.org.example.court.domain.decision.Decision;
import main.java.org.example.court.domain.decision.command.ChangePagesCommand;
import main.java.org.example.court.domain.decision.values.DecisionID;
import main.java.org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangePagesUseCase implements UseCaseForCommand<ChangePagesCommand> {
    private EventsRepository eventsRepository;

    public ChangePagesUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangePagesCommand command) {
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getDecisionID());
        Decision decision = Decision.from(DecisionID.of(command.getDecisionID()), domainEvents);
        decision.changePages(new Pages(command.getNewPages()));
        return decision.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
