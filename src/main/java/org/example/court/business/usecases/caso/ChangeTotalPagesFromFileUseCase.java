package org.example.court.business.usecases.caso;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.caso.Caso;
import org.example.court.domain.caso.command.ChangeNameFromGuarantorCommand;
import org.example.court.domain.caso.command.ChangeTotalPagesFromFileCommand;
import org.example.court.domain.caso.values.CasoID;
import org.example.court.domain.caso.values.FileID;
import org.example.court.domain.caso.values.GuarantorID;
import org.example.court.domain.caso.values.TotalPages;
import org.example.court.domain.commonValues.Name;
import org.example.court.generic.DomainEvent;

import java.util.List;
import java.util.stream.Collectors;

public class ChangeTotalPagesFromFileUseCase implements UseCaseForCommand<ChangeTotalPagesFromFileCommand> {

    private EventsRepository eventsRepository;

    public ChangeTotalPagesFromFileUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeTotalPagesFromFileCommand command) {
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getCasoID());
        Caso caso = Caso.from(CasoID.of(command.getCasoID()), domainEvents);
        caso.changeTotalPagesOfFile(FileID.of(command.getFileID()), new TotalPages(command.getNewTotalPages()));
        return caso.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
