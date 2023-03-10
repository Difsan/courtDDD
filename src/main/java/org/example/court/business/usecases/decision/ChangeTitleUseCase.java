package org.example.court.business.usecases.decision;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.commonValues.Name;
import org.example.court.domain.commonValues.Title;
import org.example.court.domain.decision.Decision;
import org.example.court.domain.decision.command.AssignJudgeCommand;
import org.example.court.domain.decision.command.ChangeTitleCommand;
import org.example.court.domain.decision.values.DecisionID;
import org.example.court.domain.decision.values.JudgeID;
import org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangeTitleUseCase implements UseCaseForCommand<ChangeTitleCommand> {
    private EventsRepository eventsRepository;

    public ChangeTitleUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeTitleCommand command) {
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getDecisionID());
        Decision decision = Decision.from(DecisionID.of(command.getDecisionID()), domainEvents);
        if(decision.title().value().equals(command.getNewTitle())) {
            throw new IllegalArgumentException("Decision title is already " + command.getNewTitle());
        }
        decision.changeTitle(new Title(command.getNewTitle()));
        return decision.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
