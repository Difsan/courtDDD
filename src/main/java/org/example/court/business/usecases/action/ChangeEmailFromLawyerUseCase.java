package main.java.org.example.court.business.usecases.action;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForCommand;
import main.java.org.example.court.domain.action.Action;
import main.java.org.example.court.domain.action.command.ChangeEmailFromLawyerCommand;
import main.java.org.example.court.domain.action.command.ChangePhoneFromLawyerCommand;
import main.java.org.example.court.domain.action.values.ActionID;
import main.java.org.example.court.domain.action.values.LawyerID;
import main.java.org.example.court.domain.commonValues.Email;
import main.java.org.example.court.domain.commonValues.Phone;
import main.java.org.example.court.generic.DomainEvent;
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
