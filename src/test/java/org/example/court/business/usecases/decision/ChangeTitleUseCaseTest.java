package org.example.court.business.usecases.decision;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.decision.command.ChangeTitleCommand;
import org.example.court.domain.decision.command.CreateDecisionCommand;
import org.example.court.domain.decision.events.DecisionCreated;
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
class ChangeTitleUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;

    private ChangeTitleUseCase changeTitleUseCase;

    @BeforeEach
    void setUp(){ changeTitleUseCase = new ChangeTitleUseCase(eventsRepository);}

    @Test
    void successfulScenario(){

        DecisionCreated decisionCreated = new DecisionCreated("answerToDemand", 5);
        decisionCreated.setAggregateRootId("decisionID");

        ChangeTitleCommand changeTitleCommand = new ChangeTitleCommand("decisionID", "appointTrial");

        TitleChanged titleChanged = new TitleChanged("appointTrial");
        titleChanged.setAggregateRootId("decisionID");

        Mockito.when(eventsRepository.findByAggregatedRootId("decisionID")).thenReturn(List.of(
                decisionCreated));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(TitleChanged.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = changeTitleUseCase.apply(changeTitleCommand);
        TitleChanged  event = (TitleChanged) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(decisionCreated.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(titleChanged.getNewTitle(), event.getNewTitle());
    }
}