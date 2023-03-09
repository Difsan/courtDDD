package main.java.org.example.court.business.usecases.decision;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForCommand;
import main.java.org.example.court.domain.commonValues.Name;
import main.java.org.example.court.domain.commonValues.Type;
import main.java.org.example.court.domain.decision.Decision;
import main.java.org.example.court.domain.decision.command.AssignCategoryCommand;
import main.java.org.example.court.domain.decision.command.AssignJudgeCommand;
import main.java.org.example.court.domain.decision.values.CategoryID;
import main.java.org.example.court.domain.decision.values.DecisionID;
import main.java.org.example.court.domain.decision.values.JudgeID;
import main.java.org.example.court.generic.DomainEvent;
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
