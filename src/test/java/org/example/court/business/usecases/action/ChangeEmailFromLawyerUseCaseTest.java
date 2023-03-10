package org.example.court.business.usecases.action;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.action.command.AssignLawyerCommand;
import org.example.court.domain.action.command.ChangeEmailFromLawyerCommand;
import org.example.court.domain.action.events.ActionPresented;
import org.example.court.domain.action.events.EmailChangedFromLawyer;
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
class ChangeEmailFromLawyerUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;

    private ChangeEmailFromLawyerUseCase changeEmailFromLawyerUseCase;

    @BeforeEach
    void setUp() {
        changeEmailFromLawyerUseCase = new ChangeEmailFromLawyerUseCase(eventsRepository);
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

        ChangeEmailFromLawyerCommand command = new ChangeEmailFromLawyerCommand("actionID", "partID",
                "lawyerID", "mk@hotmail.com");

        EmailChangedFromLawyer emailChangedFromLawyer = new EmailChangedFromLawyer("lawyerID",
                "mk@hotmail.com");
        emailChangedFromLawyer.setAggregateRootId("actionID");


        Mockito.when(eventsRepository.findByAggregatedRootId("actionID")).thenReturn(List.of(
                actionPresented, partAssigned, lawyerAssigned));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(EmailChangedFromLawyer.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = changeEmailFromLawyerUseCase.apply(command);
        EmailChangedFromLawyer  event = (EmailChangedFromLawyer) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(partAssigned.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(emailChangedFromLawyer.getLawyerID(), event.getLawyerID());
        Assertions.assertEquals(emailChangedFromLawyer.getNewEmail(), event.getNewEmail());

    }
}