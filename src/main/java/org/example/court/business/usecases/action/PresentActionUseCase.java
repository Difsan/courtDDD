package main.java.org.example.court.business.usecases.action;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForCommand;
import main.java.org.example.court.domain.action.Action;
import main.java.org.example.court.domain.action.command.PresentActionCommand;
import main.java.org.example.court.domain.action.values.ActionID;
import main.java.org.example.court.domain.commonValues.Title;
import main.java.org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PresentActionUseCase implements UseCaseForCommand<PresentActionCommand> {
    private EventsRepository eventsRepository;

    public PresentActionUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(PresentActionCommand command) {
        Action action = new Action(ActionID.of(command.getActionID()), new Title(command.getTitle()));
        return action.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
