package org.example.court.business.usecases.action;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.action.command.ChangeTitleCommand;
import org.example.court.domain.action.events.ActionPresented;
import org.example.court.domain.action.events.PartAssigned;
import org.example.court.domain.action.events.TitleChanged;
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
class ChangeTitleUseCaseTest {

    @Mock
    private EventsRepository eventsRepository;

    private ChangeTitleUseCase changeTitleUseCase;

    @BeforeEach
    void setUp() {
        changeTitleUseCase = new ChangeTitleUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){

        ActionPresented actionPresented = new ActionPresented("addInformation", 3);
        actionPresented.setAggregateRootId("actionID");

        //
        ChangeTitleCommand changeTitleCommand = new ChangeTitleCommand("actionID", "giveDetails");

        TitleChanged titleChanged = new TitleChanged("giveDetails");
        titleChanged.setAggregateRootId("actionID");

        Mockito.when(eventsRepository.findByAggregatedRootId("actionID")).thenReturn(List.of(
                actionPresented));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(TitleChanged.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = changeTitleUseCase.apply(changeTitleCommand);
        TitleChanged  event = (TitleChanged) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(titleChanged.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(titleChanged.getNewTitle(), event.getNewTitle());
    }
}