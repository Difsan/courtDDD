package org.example.court.business.usecases.action;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.action.Action;
import org.example.court.domain.action.command.ChangeAddressFromPartCommand;
import org.example.court.domain.action.command.ChangeEmailFromPartCommand;
import org.example.court.domain.action.values.ActionID;
import org.example.court.domain.action.values.Address;
import org.example.court.domain.action.values.PartID;
import org.example.court.domain.commonValues.Email;
import org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangeAddressFromPartUseCase implements UseCaseForCommand<ChangeAddressFromPartCommand> {
    private EventsRepository eventsRepository;

    public ChangeAddressFromPartUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeAddressFromPartCommand command) {
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getActionID());
        Action action = Action.from(ActionID.of(command.getActionID()), domainEvents);
        action.changeAddressPart(PartID.of(command.getPartID()), new Address(command.getNewAddress()));
        return action.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
