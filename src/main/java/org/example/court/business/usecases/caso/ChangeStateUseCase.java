package org.example.court.business.usecases.caso;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.caso.Caso;
import org.example.court.domain.caso.command.ChangeStateCommand;
import org.example.court.domain.caso.values.CasoID;
import org.example.court.domain.caso.values.State;
import org.example.court.generic.DomainEvent;

import java.util.List;
import java.util.stream.Collectors;

public class ChangeStateUseCase implements UseCaseForCommand<ChangeStateCommand> {

    private EventsRepository eventsRepository;

    public ChangeStateUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeStateCommand command) {
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getCaseID());
        Caso caso = Caso.from(CasoID.of(command.getCaseID()), domainEvents);
        caso.changeState(new State(command.getNewState()));
        return caso.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
