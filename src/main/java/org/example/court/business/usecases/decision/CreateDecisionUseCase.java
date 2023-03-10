package org.example.court.business.usecases.decision;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.commonValues.Pages;
import org.example.court.domain.commonValues.Title;
import org.example.court.domain.decision.Decision;
import org.example.court.domain.decision.command.CreateDecisionCommand;
import org.example.court.domain.decision.values.DecisionID;
import org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateDecisionUseCase implements UseCaseForCommand<CreateDecisionCommand> {
    private EventsRepository eventsRepository;

    public CreateDecisionUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(CreateDecisionCommand command) {
        Decision decision = new Decision(DecisionID.of(command.getDecisionID()),
                new Title(command.getTitle()),new Pages(command.getPages()));
        return decision.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
