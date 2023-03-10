package org.example.court.business.usecases.action;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.action.command.AssignLawyerCommand;
import org.example.court.domain.action.command.AssignPartCommand;
import org.example.court.domain.action.events.ActionPresented;
import org.example.court.domain.action.events.LawyerAssigned;
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
class AssignLawyerUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;

    private AssignLawyerUseCase assignLawyerUseCase;

    @BeforeEach
    void setUp() {
        assignLawyerUseCase = new AssignLawyerUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){

        ActionPresented actionPresented = new ActionPresented("addInformation", 3);
        actionPresented.setAggregateRootId("actionID");

        PartAssigned partAssigned = new PartAssigned("partID", "plaintiff",
                "Pedro Pablo", "5487964586", "3218567549",
                "pp@gmail.com", "av portland 123");
        partAssigned.setAggregateRootId("actionID");

        AssignLawyerCommand assignLawyerCommand = new AssignLawyerCommand("actionID", "partID",
                "lawyerID", "Franco",
                "8745631598", "3004986875", "fl@gmail.com", "65412365");

        LawyerAssigned lawyerAssigned = new LawyerAssigned("lawyerID", "Franco",
                "8745631598", "3004986875", "fl@gmail.com", "65412365");
        lawyerAssigned.setAggregateRootId("actionID");

        Mockito.when(eventsRepository.findByAggregatedRootId("actionID")).thenReturn(List.of(
                actionPresented, partAssigned));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(LawyerAssigned.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = assignLawyerUseCase.apply(assignLawyerCommand);
        LawyerAssigned  event = (LawyerAssigned) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(partAssigned.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(lawyerAssigned.getLawyerID(), event.getLawyerID());
        Assertions.assertEquals(lawyerAssigned.getName(), event.getName());
        Assertions.assertEquals(lawyerAssigned.getNit(), event.getNit());
        Assertions.assertEquals(lawyerAssigned.getPhone(), event.getPhone());
        Assertions.assertEquals(lawyerAssigned.getEmail(), event.getEmail());
        Assertions.assertEquals(lawyerAssigned.getProfessionalCard(), event.getProfessionalCard());
    }
}