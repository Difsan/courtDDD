package org.example.court.business.usecases.action;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.action.command.AssignPartCommand;
import org.example.court.domain.action.command.ChangeAddressFromPartCommand;
import org.example.court.domain.action.events.ActionPresented;
import org.example.court.domain.action.events.AddressChangedFromPart;
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
class ChangeAddressFromPartUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;

    private ChangeAddressFromPartUseCase changeAddressFromPartUseCase;

    @BeforeEach
    void setUp() {
        changeAddressFromPartUseCase = new ChangeAddressFromPartUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){

        ActionPresented actionPresented = new ActionPresented("addInformation", 3);
        actionPresented.setAggregateRootId("actionID");

        PartAssigned partAssigned = new PartAssigned("partID", "plaintiff",
                "Pedro Pablo", "5487964586", "3218567549",
                "pp@gmail.com", "av portland 123");
        partAssigned.setAggregateRootId("actionID");

        ChangeAddressFromPartCommand command = new ChangeAddressFromPartCommand("actionID","partId",
                "132, My Street");

        AddressChangedFromPart addressChangedFromPart = new AddressChangedFromPart("partId",
                "132, My Street");
        addressChangedFromPart.setAggregateRootId("actionID");

        Mockito.when(eventsRepository.findByAggregatedRootId("actionID")).thenReturn(List.of(
                actionPresented, partAssigned));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(AddressChangedFromPart.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = changeAddressFromPartUseCase.apply(command);
        AddressChangedFromPart  event = (AddressChangedFromPart) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(partAssigned.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(addressChangedFromPart.getPartID(), event.getPartID());
        Assertions.assertEquals(addressChangedFromPart.getNewAddress(), event.getNewAddress());

    }
}