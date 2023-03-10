package org.example.court.business.usecases.action;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.action.command.AssignPartCommand;
import org.example.court.domain.action.command.PresentActionCommand;
import org.example.court.domain.action.events.ActionPresented;
import org.example.court.domain.action.events.PartAssigned;
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
class AssignPartUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;

    private AssignPartUseCase assignPartUseCase;

    @BeforeEach
    void setUp() {
        assignPartUseCase = new AssignPartUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){

        ActionPresented actionPresented = new ActionPresented("addInformation", 3);
        actionPresented.setAggregateRootId("actionID");

        AssignPartCommand assignPartCommand = new AssignPartCommand("actionID", "partID", "plaintiff",
                "Pedro Pablo", "5487964586", "3218567549",
                "pp@gmail.com", "av portland 123");

        PartAssigned partAssigned = new PartAssigned("partID", "plaintiff",
                "Pedro Pablo", "5487964586", "3218567549",
                "pp@gmail.com", "av portland 123");
        partAssigned.setAggregateRootId("actionID");

        Mockito.when(eventsRepository.findByAggregatedRootId("actionID")).thenReturn(List.of(
                actionPresented));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(PartAssigned.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = assignPartUseCase.apply(assignPartCommand);
        PartAssigned  event = (PartAssigned) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(partAssigned.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(partAssigned.getPartID(), event.getPartID());
        //Assertions.assertEquals(partAssigned.getType(), event.getType());
        Assertions.assertEquals(partAssigned.getName(), event.getName());
        Assertions.assertEquals(partAssigned.getNit(), event.getNit());
        Assertions.assertEquals(partAssigned.getPhone(), event.getPhone());
        Assertions.assertEquals(partAssigned.getEmail(), event.getEmail());
        Assertions.assertEquals(partAssigned.getAddress(), event.getAddress());
    }
}