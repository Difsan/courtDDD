package org.example.court.business.usecases.caso;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.caso.command.ChangeNameFromGuarantorCommand;
import org.example.court.domain.caso.events.CasoOpened;
import org.example.court.domain.caso.events.GuarantorAssigned;
import org.example.court.domain.caso.events.NameChangedFromGuarantor;
import org.example.court.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ChangeNameFromGuarantorUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;

    private ChangeNameFromGuarantorUseCase changeNameFromGuarantorUseCase;

    @BeforeEach
    void setup(){
        changeNameFromGuarantorUseCase = new ChangeNameFromGuarantorUseCase(eventsRepository);}

    @Test
    void successfulScenario(){

        CasoOpened casoOpened = new CasoOpened("inProcess");
        casoOpened.setAggregateRootId("casoID");

        GuarantorAssigned guarantorAssigned = new GuarantorAssigned("guarantorID",
                "Pedro", "2566895846");
        guarantorAssigned.setAggregateRootId("casoID");

        ChangeNameFromGuarantorCommand changeNameFromGuarantorCommand = new ChangeNameFromGuarantorCommand("casoID",
                "guarantorID", "Pablo");

        NameChangedFromGuarantor nameChangedFromGuarantor = new NameChangedFromGuarantor("guarantorID", "Pablo");
        nameChangedFromGuarantor.setAggregateRootId("casoID");

        Mockito.when(eventsRepository.findByAggregatedRootId("casoID")).thenReturn(List.of(
                casoOpened, guarantorAssigned));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(NameChangedFromGuarantor.class))).thenAnswer(
                invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                }
        );

        List<DomainEvent> domainEventList = changeNameFromGuarantorUseCase.apply(changeNameFromGuarantorCommand);
        Mockito.verify(eventsRepository).saveEvent(ArgumentMatchers.any(NameChangedFromGuarantor.class));
        NameChangedFromGuarantor event = (NameChangedFromGuarantor) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(nameChangedFromGuarantor.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(nameChangedFromGuarantor.getNewName(), event.getNewName());
    }
}