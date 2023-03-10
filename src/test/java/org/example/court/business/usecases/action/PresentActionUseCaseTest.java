package org.example.court.business.usecases.action;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.action.command.PresentActionCommand;
import org.example.court.domain.action.events.ActionPresented;
import org.example.court.domain.decision.events.DecisionCreated;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PresentActionUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;

    private PresentActionUseCase presentActionUseCase;

    @BeforeEach
    void setUp() {
        presentActionUseCase = new PresentActionUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){

        PresentActionCommand presentActionCommand = new PresentActionCommand("actionID",
                "addInformation", 3);

        ActionPresented actionPresented = new ActionPresented("addInformation", 3);
        actionPresented.setAggregateRootId("actionID");

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(ActionPresented.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = presentActionUseCase.apply(presentActionCommand);
        ActionPresented  event = (ActionPresented) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(actionPresented.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(actionPresented.getTitle(), event.getTitle());
        Assertions.assertEquals(actionPresented.getPages(), event.getPages());
    }
}