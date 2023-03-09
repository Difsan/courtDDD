package main.java.org.example.court.business.usecases.action;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForCommand;
import main.java.org.example.court.domain.action.Action;
import main.java.org.example.court.domain.action.command.ChangeNameFromLawyerCommand;
import main.java.org.example.court.domain.action.values.ActionID;
import main.java.org.example.court.domain.action.values.LawyerID;
import main.java.org.example.court.domain.commonValues.Name;
import main.java.org.example.court.generic.DomainEvent;
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
        action.changeNameOfLawyer(LawyerID.of(command.getLawyerID()),new Name(command.getNewName()));
        return action.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
