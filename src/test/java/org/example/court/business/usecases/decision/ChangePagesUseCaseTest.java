package org.example.court.business.usecases.decision;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.decision.command.ChangePagesCommand;
import org.example.court.domain.decision.command.ChangeTitleCommand;
import org.example.court.domain.decision.events.DecisionCreated;
import org.example.court.domain.decision.events.PagesChanged;
import org.example.court.domain.decision.events.TitleChanged;
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
class ChangePagesUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;

    private ChangePagesUseCase changePagesUseCase;

    @BeforeEach
    void setUp(){ changePagesUseCase = new ChangePagesUseCase(eventsRepository);}

    @Test
    void successfulScenario(){

        DecisionCreated decisionCreated = new DecisionCreated("answerToDemand", 5);
        decisionCreated.setAggregateRootId("decisionID");

        ChangePagesCommand changePagesCommand = new ChangePagesCommand("decisionID", 3);

        PagesChanged pagesChanged = new PagesChanged(3);
        pagesChanged.setAggregateRootId("decisionID");

        Mockito.when(eventsRepository.findByAggregatedRootId("decisionID")).thenReturn(List.of(
                decisionCreated));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(PagesChanged.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = changePagesUseCase.apply(changePagesCommand);
        PagesChanged  event = (PagesChanged) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(pagesChanged.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(pagesChanged.getNewPages(), event.getNewPages());
    }
}