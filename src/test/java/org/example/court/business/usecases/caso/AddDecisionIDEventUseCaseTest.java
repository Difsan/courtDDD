package org.example.court.business.usecases.caso;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.caso.events.ActionIDAdded;
import org.example.court.domain.caso.events.CasoOpened;
import org.example.court.domain.caso.events.DecisionIDAdded;
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

@ExtendWith(MockitoExtension.class)
class AddDecisionIDEventUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;
    private AddDecisionIDEventUseCase addDecisionIDEventUseCase;

    @BeforeEach
    void setUp() {
        addDecisionIDEventUseCase = new AddDecisionIDEventUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){
        CasoOpened casoOpened = new CasoOpened("inProcess");
        casoOpened.setAggregateRootId("casoID");

        FileCreated fileCreated = new FileCreated("fileID");
        fileCreated.setAggregateRootId("casoID");

        DecisionIDAdded decisionIDAdded = new DecisionIDAdded("casoID", "fileID",
                "decisionID");
        decisionIDAdded.setAggregateRootId("casoID");

        Mockito.when(eventsRepository.findByAggregatedRootId("casoID")).thenReturn(List.of(
                casoOpened, fileCreated));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(DecisionIDAdded.class))).thenAnswer(
                invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                }
        );

        List<DomainEvent> domainEventList = addDecisionIDEventUseCase.apply(decisionIDAdded);

        Mockito.verify(eventsRepository).saveEvent(ArgumentMatchers.any(DecisionIDAdded.class));
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(decisionIDAdded.aggregateRootId(), domainEventList.get(0).aggregateRootId());
    }

}