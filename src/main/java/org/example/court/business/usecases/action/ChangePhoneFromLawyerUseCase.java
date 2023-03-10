package org.example.court.business.usecases.action;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.action.Action;
import org.example.court.domain.action.command.ChangeNameFromLawyerCommand;
import org.example.court.domain.action.command.ChangePhoneFromLawyerCommand;
import org.example.court.domain.action.values.ActionID;
import org.example.court.domain.action.values.LawyerID;
import org.example.court.domain.commonValues.Name;
import org.example.court.domain.commonValues.Phone;
import org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangePhoneFromLawyerUseCase implements UseCaseForCommand<ChangePhoneFromLawyerCommand> {
    private EventsRepository eventsRepository;

    public ChangePhoneFromLawyerUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangePhoneFromLawyerCommand command) {
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getActionID());
        Action action = Action.from(ActionID.of(command.getActionID()), domainEvents);
        if(action.part().lawyer().phone().value().equals(command.getNewPhone())) {
            throw new IllegalArgumentException("Lawyer's phone is already " + command.getNewPhone());
        }
        action.changePhoneOfLawyer(LawyerID.of(command.getLawyerID()),new Phone(command.getNewPhone()));
        return action.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
