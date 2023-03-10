package org.example.court.business.usecases.action;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.action.Action;
import org.example.court.domain.action.command.ChangeEmailFromPartCommand;
import org.example.court.domain.action.command.ChangeNameFromPartCommand;
import org.example.court.domain.action.values.ActionID;
import org.example.court.domain.action.values.PartID;
import org.example.court.domain.commonValues.Email;
import org.example.court.domain.commonValues.Name;
import org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangeEmailFromPartUseCase implements UseCaseForCommand<ChangeEmailFromPartCommand> {
    private EventsRepository eventsRepository;

    public ChangeEmailFromPartUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeEmailFromPartCommand command) {
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getActionID());
        Action action = Action.from(ActionID.of(command.getActionID()), domainEvents);
        action.changeEmailPart(PartID.of(command.getPartID()),new Email(command.getNewEmail()));
        return action.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
