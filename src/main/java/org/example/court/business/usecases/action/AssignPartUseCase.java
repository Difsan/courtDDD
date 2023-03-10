package org.example.court.business.usecases.action;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.action.Action;
import org.example.court.domain.action.command.AssignPartCommand;
import org.example.court.domain.action.values.ActionID;
import org.example.court.domain.action.values.Address;
import org.example.court.domain.action.values.PartID;
import org.example.court.domain.commonValues.*;
import org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssignPartUseCase implements UseCaseForCommand<AssignPartCommand> {

    private EventsRepository eventsRepository;

    public AssignPartUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(AssignPartCommand command) {
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getActionID());
        Action action = Action.from(ActionID.of(command.getActionID()), domainEvents);
        action.assignPart(PartID.of(command.getPartID()), new Type(command.getPartID()),
                new Name(command.getName()),new Nit(command.getNit()),new Phone(command.getPhone()),
                new Email(command.getEmail()),new Address(command.getAddress()));
        return action.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
