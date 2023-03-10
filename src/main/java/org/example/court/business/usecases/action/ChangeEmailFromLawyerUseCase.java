package org.example.court.business.usecases.action;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.action.Action;
import org.example.court.domain.action.command.ChangeEmailFromLawyerCommand;
import org.example.court.domain.action.command.ChangePhoneFromLawyerCommand;
import org.example.court.domain.action.values.ActionID;
import org.example.court.domain.action.values.LawyerID;
import org.example.court.domain.commonValues.Email;
import org.example.court.domain.commonValues.Phone;
import org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangeEmailFromLawyerUseCase implements UseCaseForCommand<ChangeEmailFromLawyerCommand> {
    private EventsRepository eventsRepository;

    public ChangeEmailFromLawyerUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeEmailFromLawyerCommand command) {
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getActionID());
        Action action = Action.from(ActionID.of(command.getActionID()), domainEvents);
        action.changeEmailOfLawyer(LawyerID.of(command.getLawyerID()),new Email(command.getNewEmail()));
        return action.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
