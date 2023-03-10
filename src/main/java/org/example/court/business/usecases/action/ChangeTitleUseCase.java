package org.example.court.business.usecases.action;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.action.Action;
import org.example.court.domain.action.command.ChangeTitleCommand;
import org.example.court.domain.action.values.ActionID;
import org.example.court.domain.commonValues.Title;
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
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getActionID());
        Action action = Action.from(ActionID.of(command.getActionID()), domainEvents);
        if(action.title().value().equals(command.getNewTitle())) {
            throw new IllegalArgumentException("Action title is already " + command.getNewTitle());
        }
        action.changeTitle(new Title(command.getNewTitle()));
        return action.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
