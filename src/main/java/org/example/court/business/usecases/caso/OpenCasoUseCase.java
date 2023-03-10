package org.example.court.business.usecases.caso;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.caso.Caso;
import org.example.court.domain.caso.command.OpenCasoCommand;
import org.example.court.domain.caso.values.CasoID;
import org.example.court.domain.caso.values.State;
import org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OpenCasoUseCase implements UseCaseForCommand<OpenCasoCommand> {

    private EventsRepository eventsRepository;

    public OpenCasoUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(OpenCasoCommand command) {
        Caso caso = new Caso(CasoID.of(command.getCaseID()), new State(command.getState()));
        return caso.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
