package org.example.court.business.usecases.action;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.action.Action;
import org.example.court.domain.action.command.ChangePagesCommand;
import org.example.court.domain.action.command.ChangeTitleCommand;
import org.example.court.domain.action.values.ActionID;
import org.example.court.domain.commonValues.Pages;
import org.example.court.domain.commonValues.Title;
import org.example.court.generic.DomainEvent;
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
