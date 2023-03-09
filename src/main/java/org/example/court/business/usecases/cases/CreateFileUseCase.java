package main.java.org.example.court.business.usecases.cases;

import main.java.org.example.court.business.commons.EventsRepository;
import main.java.org.example.court.business.commons.UseCaseForCommand;
import main.java.org.example.court.domain.cases.Caso;
import main.java.org.example.court.domain.cases.command.AssignGuarantorCommand;
import main.java.org.example.court.domain.cases.command.CreateFileCommand;
import main.java.org.example.court.domain.cases.values.CaseID;
import main.java.org.example.court.domain.cases.values.FileID;
import main.java.org.example.court.domain.cases.values.GuarantorID;
import main.java.org.example.court.domain.commonValues.Name;
import main.java.org.example.court.domain.commonValues.Nit;
import main.java.org.example.court.generic.DomainEvent;

import java.util.List;
import java.util.stream.Collectors;

public class CreateFileUseCase implements UseCaseForCommand<CreateFileCommand> {

    private EventsRepository eventsRepository;

    public CreateFileUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(CreateFileCommand command) {
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getCaseID());
        Caso caso = Caso.from(CaseID.of(command.getCaseID()), domainEvents);
        caso.addFile(FileID.of(command.getFileID()));
        return caso.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
