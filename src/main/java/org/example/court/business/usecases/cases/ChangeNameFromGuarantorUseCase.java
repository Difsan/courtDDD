package main.java.org.example.court.business.usecases.cases;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForCommand;
import main.java.org.example.court.domain.cases.Caso;
import main.java.org.example.court.domain.cases.command.ChangeNameFromGuarantorCommand;
import main.java.org.example.court.domain.cases.command.ChangeStateCommand;
import main.java.org.example.court.domain.cases.values.CaseID;
import main.java.org.example.court.domain.cases.values.GuarantorID;
import main.java.org.example.court.domain.cases.values.State;
import main.java.org.example.court.domain.commonValues.Name;
import main.java.org.example.court.generic.DomainEvent;

import java.util.List;
import java.util.stream.Collectors;

public class ChangeNameFromGuarantorUseCase implements UseCaseForCommand<ChangeNameFromGuarantorCommand> {

    private EventsRepository eventsRepository;

    public ChangeNameFromGuarantorUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeNameFromGuarantorCommand command) {
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getCaseID());
        Caso caso = Caso.from(CaseID.of(command.getCaseID()), domainEvents);
        caso.changeNameOfGuarantor(GuarantorID.of(command.getGuarantorID()), new Name(command.getNewName()));
        return caso.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
