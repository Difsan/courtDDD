package org.example.court.business.usecases.action;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.action.Action;
import org.example.court.domain.action.command.ChangeNameFromPartCommand;
import org.example.court.domain.action.values.ActionID;
import org.example.court.domain.action.values.PartID;
import org.example.court.domain.commonValues.Name;
import org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangeNameFromPartUseCase implements UseCaseForCommand<ChangeNameFromPartCommand> {
    private EventsRepository eventsRepository;

    public ChangeNameFromPartUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeNameFromPartCommand command) {
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getActionID());
        Action action = Action.from(ActionID.of(command.getActionID()), domainEvents);
        if(action.part().name().value().equals(command.getNewName())) {
            throw new IllegalArgumentException("Part's name is already " + command.getNewName());
        }
        action.changeNamePart(PartID.of(command.getPartID()),new Name(command.getNewName()));
        return action.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
