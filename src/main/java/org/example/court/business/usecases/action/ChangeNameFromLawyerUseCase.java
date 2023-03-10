package org.example.court.business.usecases.action;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.action.Action;
import org.example.court.domain.action.command.ChangeNameFromLawyerCommand;
import org.example.court.domain.action.values.ActionID;
import org.example.court.domain.action.values.LawyerID;
import org.example.court.domain.commonValues.Name;
import org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangeNameFromLawyerUseCase implements UseCaseForCommand<ChangeNameFromLawyerCommand> {
    private EventsRepository eventsRepository;

    public ChangeNameFromLawyerUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeNameFromLawyerCommand command) {
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getActionID());
        Action action = Action.from(ActionID.of(command.getActionID()), domainEvents);
        if(action.part().lawyer().name().value().equals(command.getNewName())) {
            throw new IllegalArgumentException("Lawyer's name is already " + command.getNewName());
        }
        action.changeNameOfLawyer(LawyerID.of(command.getLawyerID()),new Name(command.getNewName()));
        return action.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
