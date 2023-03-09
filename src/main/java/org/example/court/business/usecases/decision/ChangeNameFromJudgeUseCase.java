package main.java.org.example.court.business.usecases.decision;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForCommand;
import main.java.org.example.court.domain.commonValues.Name;
import main.java.org.example.court.domain.commonValues.Type;
import main.java.org.example.court.domain.decision.Decision;
import main.java.org.example.court.domain.decision.command.ChangeNameFromJudgeCommand;
import main.java.org.example.court.domain.decision.command.ChangeTypeFromCategoryCommand;
import main.java.org.example.court.domain.decision.values.CategoryID;
import main.java.org.example.court.domain.decision.values.DecisionID;
import main.java.org.example.court.domain.decision.values.JudgeID;
import main.java.org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangeNameFromJudgeUseCase implements UseCaseForCommand<ChangeNameFromJudgeCommand> {
    private EventsRepository eventsRepository;

    public ChangeNameFromJudgeUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeNameFromJudgeCommand command) {
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getDecisionID());
        Decision decision = Decision.from(DecisionID.of(command.getDecisionID()), domainEvents);
        decision.changeNameFromJudge(JudgeID.of(command.getJudgeID()),new Name(command.getNewName()));
        return decision.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
