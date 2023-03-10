package org.example.court.business.usecases.caso;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.caso.Caso;
import org.example.court.domain.caso.command.ChangeNameFromGuarantorCommand;
import org.example.court.domain.caso.values.CasoID;
import org.example.court.domain.caso.values.GuarantorID;
import org.example.court.domain.commonValues.Name;
import org.example.court.generic.DomainEvent;

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
        Caso caso = Caso.from(CasoID.of(command.getCaseID()), domainEvents);
        caso.changeNameOfGuarantor(GuarantorID.of(command.getGuarantorID()), new Name(command.getNewName()));
        return caso.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
