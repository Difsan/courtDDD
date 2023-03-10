package main.java.org.example.court.business.usecases.action;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForCommand;
import main.java.org.example.court.domain.action.Action;
import main.java.org.example.court.domain.action.command.ChangePagesCommand;
import main.java.org.example.court.domain.action.command.ChangeTitleCommand;
import main.java.org.example.court.domain.action.values.ActionID;
import main.java.org.example.court.domain.commonValues.Pages;
import main.java.org.example.court.domain.commonValues.Title;
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
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getActionID());
        Action action = Action.from(ActionID.of(command.getActionID()), domainEvents);
        action.changePages(new Pages(command.getNewPages()));
        return action.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
