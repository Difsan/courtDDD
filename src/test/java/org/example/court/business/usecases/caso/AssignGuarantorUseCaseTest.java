package org.example.court.business.usecases.caso;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.caso.command.AssignGuarantorCommand;
import org.example.court.domain.caso.events.ActionIDAdded;
import org.example.court.domain.caso.events.CasoOpened;
import org.example.court.domain.caso.events.FileCreated;
import org.example.court.domain.caso.events.GuarantorAssigned;
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
class AssignGuarantorUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;

    private AssignGuarantorUseCase assignGuarantorUseCase;

    @BeforeEach
    void setup(){
        assignGuarantorUseCase = new AssignGuarantorUseCase(eventsRepository);}

    @Test
    void successfulScenario(){

        CasoOpened casoOpened = new CasoOpened("inProcess");
        casoOpened.setAggregateRootId("casoID");

        AssignGuarantorCommand assignGuarantorCommand = new AssignGuarantorCommand("casoID",
                "guarantorID", "Pedro", "2566895846");

        GuarantorAssigned guarantorAssigned = new GuarantorAssigned("guarantorID",
                "Pedro", "2566895846");
        guarantorAssigned.setAggregateRootId("casoID");

        Mockito.when(eventsRepository.findByAggregatedRootId("casoID")).thenReturn(List.of(
                casoOpened));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(GuarantorAssigned.class))).thenAnswer(
                invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                }
        );

        List<DomainEvent> domainEventList = assignGuarantorUseCase.apply(assignGuarantorCommand);
        Mockito.verify(eventsRepository).saveEvent(ArgumentMatchers.any(GuarantorAssigned.class));
        GuarantorAssigned event = (GuarantorAssigned) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(guarantorAssigned.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(guarantorAssigned.getName(), event.getName());
        Assertions.assertEquals(guarantorAssigned.getNit(), event.getNit());
        //Assertions.assertEquals("4785", event.getNit());
    }
}