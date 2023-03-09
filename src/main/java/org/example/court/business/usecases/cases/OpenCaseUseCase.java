package main.java.org.example.court.business.usecases.cases;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForCommand;
import main.java.org.example.court.domain.cases.Caso;
import main.java.org.example.court.domain.cases.command.OpenCaseCommand;
import main.java.org.example.court.domain.cases.values.CaseID;
import main.java.org.example.court.domain.cases.values.State;
import main.java.org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OpenCaseUseCase implements UseCaseForCommand<OpenCaseCommand> {

    private EventsRepository eventsRepository;

    public OpenCaseUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(OpenCaseCommand command) {
        Caso caso = new Caso(CaseID.of(command.getCaseID()), new State(command.getState()));
        return caso.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
