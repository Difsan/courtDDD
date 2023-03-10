package main.java.org.example.court.business.usecases.action;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForCommand;
import main.java.org.example.court.domain.action.Action;
import main.java.org.example.court.domain.action.command.ChangeAddressFromPartCommand;
import main.java.org.example.court.domain.action.command.ChangeEmailFromPartCommand;
import main.java.org.example.court.domain.action.values.ActionID;
import main.java.org.example.court.domain.action.values.PartID;
import main.java.org.example.court.domain.commonValues.Email;
import main.java.org.example.court.generic.DomainEvent;
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
        action.changeEmailPart(PartID.of(command.getPartID()),new Email(command.getNewAddress()));
        return action.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
