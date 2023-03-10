package org.example.court.business.usecases.decision;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.commonValues.Name;
import org.example.court.domain.commonValues.Type;
import org.example.court.domain.decision.Decision;
import org.example.court.domain.decision.command.AssignCategoryCommand;
import org.example.court.domain.decision.command.AssignJudgeCommand;
import org.example.court.domain.decision.values.CategoryID;
import org.example.court.domain.decision.values.DecisionID;
import org.example.court.domain.decision.values.JudgeID;
import org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssignJudgeUseCase implements UseCaseForCommand<AssignJudgeCommand> {
    private EventsRepository eventsRepository;

    public AssignJudgeUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(AssignJudgeCommand command) {
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getDecisionID());
        Decision decision = Decision.from(DecisionID.of(command.getDecisionID()), domainEvents);
        decision.createJudge(JudgeID.of(command.getJudgeID()), new Name(command.getName()));
        return decision.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
