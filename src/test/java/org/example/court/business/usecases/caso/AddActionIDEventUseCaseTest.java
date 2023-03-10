package org.example.court.business.usecases.caso;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.caso.events.ActionIDAdded;
import org.example.court.domain.caso.events.CasoOpened;
import org.example.court.domain.caso.events.FileCreated;
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
class AddActionIDEventUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;

    private AddActionIDEventUseCase addActionIDEventUseCase;

    @BeforeEach
    void setup(){addActionIDEventUseCase = new AddActionIDEventUseCase(eventsRepository);}

    @Test
    void successfulScenario(){
        CasoOpened casoOpened = new CasoOpened("inProcess");
        casoOpened.setAggregateRootId("casoID");

        FileCreated fileCreated = new FileCreated("fileID");
        fileCreated.setAggregateRootId("casoID");

        ActionIDAdded actionIDAdded = new ActionIDAdded("casoID","fileID",
                "actionID");
        actionIDAdded.setAggregateRootId("casoID");

        Mockito.when(eventsRepository.findByAggregatedRootId("casoID")).thenReturn(List.of(
                casoOpened, fileCreated));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(ActionIDAdded.class))).thenAnswer(
                invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                }
        );

        List<DomainEvent> domainEventList = addActionIDEventUseCase.apply(actionIDAdded);

        Mockito.verify(eventsRepository).saveEvent(ArgumentMatchers.any(ActionIDAdded.class));
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(actionIDAdded.aggregateRootId(), domainEventList.get(0).aggregateRootId());
    }
}