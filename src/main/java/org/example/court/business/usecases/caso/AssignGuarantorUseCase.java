package org.example.court.business.usecases.caso;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.caso.Caso;
import org.example.court.domain.caso.command.AssignGuarantorCommand;
import org.example.court.domain.caso.values.CasoID;
import org.example.court.domain.caso.values.GuarantorID;
import org.example.court.domain.commonValues.Name;
import org.example.court.domain.commonValues.Nit;
import org.example.court.generic.DomainEvent;

import java.util.List;
import java.util.stream.Collectors;

public class AssignGuarantorUseCase implements UseCaseForCommand<AssignGuarantorCommand> {

    private EventsRepository eventsRepository;

    public AssignGuarantorUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(AssignGuarantorCommand command) {
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getCaseID());
        Caso caso = Caso.from(CasoID.of(command.getCaseID()), domainEvents);
        caso.assignGuarantor(GuarantorID.of(command.getGuarantorID()), new Name(command.getName()),
                new Nit(command.getNit()));
        return caso.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
