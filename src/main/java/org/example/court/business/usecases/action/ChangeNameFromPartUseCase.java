package main.java.org.example.court.business.usecases.action;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForCommand;
import main.java.org.example.court.domain.action.Action;
import main.java.org.example.court.domain.action.command.ChangeNameFromPartCommand;
import main.java.org.example.court.domain.action.values.ActionID;
import main.java.org.example.court.domain.action.values.PartID;
import main.java.org.example.court.domain.commonValues.Name;
import main.java.org.example.court.generic.DomainEvent;
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
        action.changeNamePart(PartID.of(command.getPartID()),new Name(command.getNewName()));
        return action.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
