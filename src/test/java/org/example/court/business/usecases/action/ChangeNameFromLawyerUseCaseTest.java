package org.example.court.business.usecases.action;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.action.command.ChangeEmailFromLawyerCommand;
import org.example.court.domain.action.command.ChangeNameFromLawyerCommand;
import org.example.court.domain.action.events.*;
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
class ChangeNameFromLawyerUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;

    private ChangeNameFromLawyerUseCase changeNameFromLawyerUseCase;

    @BeforeEach
    void setUp() {
        changeNameFromLawyerUseCase = new ChangeNameFromLawyerUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){

        ActionPresented actionPresented = new ActionPresented("addInformation", 3);
        actionPresented.setAggregateRootId("actionID");

        PartAssigned partAssigned = new PartAssigned("partID", "plaintiff",
                "Pedro Pablo", "5487964586", "3218567549",
                "pp@gmail.com", "av portland 123");
        partAssigned.setAggregateRootId("actionID");

        LawyerAssigned lawyerAssigned = new LawyerAssigned("lawyerID", "Franco",
                "8745631598", "3004986875", "fl@gmail.com", "65412365");
        lawyerAssigned.setAggregateRootId("actionID");

        ChangeNameFromLawyerCommand command = new ChangeNameFromLawyerCommand("actionID", "partID",
                "lawyerID", "Pedro Infante");

        NameChangedFromLawyer nameChangedFromLawyer = new NameChangedFromLawyer("lawyerID",
                "Pedro Infante");
        nameChangedFromLawyer.setAggregateRootId("actionID");


        Mockito.when(eventsRepository.findByAggregatedRootId("actionID")).thenReturn(List.of(
                actionPresented, partAssigned, lawyerAssigned));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(NameChangedFromLawyer.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = changeNameFromLawyerUseCase.apply(command);
        NameChangedFromLawyer  event = (NameChangedFromLawyer) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(partAssigned.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(nameChangedFromLawyer.getLawyerID(), event.getLawyerID());
        Assertions.assertEquals(nameChangedFromLawyer.getNewName(), event.getNewName());

    }
}