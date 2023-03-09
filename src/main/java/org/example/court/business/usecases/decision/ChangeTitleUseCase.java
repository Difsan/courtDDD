package main.java.org.example.court.business.usecases.decision;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForCommand;
import main.java.org.example.court.domain.commonValues.Name;
import main.java.org.example.court.domain.commonValues.Title;
import main.java.org.example.court.domain.decision.Decision;
import main.java.org.example.court.domain.decision.command.AssignJudgeCommand;
import main.java.org.example.court.domain.decision.command.ChangeTitleCommand;
import main.java.org.example.court.domain.decision.values.DecisionID;
import main.java.org.example.court.domain.decision.values.JudgeID;
import main.java.org.example.court.generic.DomainEvent;
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
        decision.changeTitle(new Title(command.getNewTitle()));
        return decision.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
