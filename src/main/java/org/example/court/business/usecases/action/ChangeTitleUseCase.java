package main.java.org.example.court.business.usecases.action;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForCommand;
import main.java.org.example.court.domain.action.Action;
import main.java.org.example.court.domain.action.command.ChangeTitleCommand;
import main.java.org.example.court.domain.action.values.ActionID;
import main.java.org.example.court.domain.commonValues.Title;
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
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getActionID());
        Action action = Action.from(ActionID.of(command.getActionID()), domainEvents);
        action.changeTitle(new Title(command.getNewTitle()));
        return action.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
