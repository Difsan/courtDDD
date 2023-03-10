package org.example.court.business.usecases.caso;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.caso.command.ChangeStateCommand;
import org.example.court.domain.caso.command.CreateFileCommand;
import org.example.court.domain.caso.events.CasoOpened;
import org.example.court.domain.caso.events.FileCreated;
import org.example.court.domain.caso.events.StateChanged;
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
class ChangeStateUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;

    private ChangeStateUseCase changeStateUseCase;

    @BeforeEach
    void setUp() { changeStateUseCase = new ChangeStateUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){

        CasoOpened casoOpened = new CasoOpened("inProcess");
        casoOpened.setAggregateRootId("casoID");

        ChangeStateCommand changeStateCommand = new ChangeStateCommand("casoID", "Close");

        StateChanged stateChanged = new StateChanged("Close");
        stateChanged.setAggregateRootId("casoID");

        Mockito.when(eventsRepository.findByAggregatedRootId("casoID")).thenReturn(List.of(
                casoOpened));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(StateChanged.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = changeStateUseCase.apply(changeStateCommand);
        StateChanged event = (StateChanged) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(stateChanged.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(stateChanged.getNewState(), event.getNewState());

    }
}