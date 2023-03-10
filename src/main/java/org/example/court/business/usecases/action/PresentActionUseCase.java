package org.example.court.business.usecases.action;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.action.Action;
import org.example.court.domain.action.command.PresentActionCommand;
import org.example.court.domain.action.values.ActionID;
import org.example.court.domain.commonValues.Pages;
import org.example.court.domain.commonValues.Title;
import org.example.court.generic.DomainEvent;
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
        Action action = new Action(ActionID.of(command.getActionID()), new Title(command.getTitle()),
                new Pages(command.getPages()));
        return action.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
