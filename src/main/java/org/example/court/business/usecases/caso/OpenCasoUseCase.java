package main.java.org.example.court.business.usecases.caso;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForCommand;
import main.java.org.example.court.domain.caso.Caso;
import main.java.org.example.court.domain.caso.command.OpenCasoCommand;
import main.java.org.example.court.domain.caso.values.CasoID;
import main.java.org.example.court.domain.caso.values.State;
import main.java.org.example.court.generic.DomainEvent;
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
