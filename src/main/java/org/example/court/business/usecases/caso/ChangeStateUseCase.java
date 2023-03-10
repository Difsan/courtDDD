package main.java.org.example.court.business.usecases.caso;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForCommand;
import main.java.org.example.court.domain.caso.Caso;
import main.java.org.example.court.domain.caso.command.ChangeStateCommand;
import main.java.org.example.court.domain.caso.values.CasoID;
import main.java.org.example.court.domain.caso.values.State;
import main.java.org.example.court.generic.DomainEvent;

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
