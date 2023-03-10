package org.example.court.business.usecases.action;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.action.command.ChangeAddressFromPartCommand;
import org.example.court.domain.action.command.ChangeEmailFromPartCommand;
import org.example.court.domain.action.events.ActionPresented;
import org.example.court.domain.action.events.AddressChangedFromPart;
import org.example.court.domain.action.events.EmailChangedFromPart;
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
class ChangeEmailFromPartUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;

    private ChangeEmailFromPartUseCase changeEmailFromPartUseCase;

    @BeforeEach
    void setUp() {
        changeEmailFromPartUseCase = new ChangeEmailFromPartUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){

        ActionPresented actionPresented = new ActionPresented("addInformation", 3);
        actionPresented.setAggregateRootId("actionID");

        PartAssigned partAssigned = new PartAssigned("partID", "plaintiff",
                "Pedro Pablo", "5487964586", "3218567549",
                "pp@gmail.com", "av portland 123");
        partAssigned.setAggregateRootId("actionID");

        ChangeEmailFromPartCommand command = new ChangeEmailFromPartCommand("actionID","partId",
                "id@hotmail.com");

        EmailChangedFromPart emailChangedFromPart = new EmailChangedFromPart("partId",
                "id@hotmail.com");
        emailChangedFromPart.setAggregateRootId("actionID");

        Mockito.when(eventsRepository.findByAggregatedRootId("actionID")).thenReturn(List.of(
                actionPresented, partAssigned));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(EmailChangedFromPart.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = changeEmailFromPartUseCase.apply(command);
        EmailChangedFromPart  event = (EmailChangedFromPart) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(partAssigned.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(emailChangedFromPart.getPartID(), event.getPartID());
        Assertions.assertEquals(emailChangedFromPart.getNewEmail(), event.getNewEmail());

    }
}