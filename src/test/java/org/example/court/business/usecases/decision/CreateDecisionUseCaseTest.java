package org.example.court.business.usecases.decision;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.caso.events.CasoOpened;
import org.example.court.domain.decision.command.CreateDecisionCommand;
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
class CreateDecisionUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;

    private CreateDecisionUseCase createDecisionUseCase;

    @BeforeEach
    void setUp(){ createDecisionUseCase = new CreateDecisionUseCase(eventsRepository);}

    @Test
    void successfulScenario(){

        CreateDecisionCommand createDecisionCommand = new CreateDecisionCommand("decisionID",
                "answerToDemand", 5);

        DecisionCreated decisionCreated = new DecisionCreated("answerToDemand", 5);
        decisionCreated.setAggregateRootId("decisionID");

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(DecisionCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = createDecisionUseCase.apply(createDecisionCommand);
        DecisionCreated  event = (DecisionCreated) domainEventList.get(0);
        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(decisionCreated.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(decisionCreated.getTitle(), event.getTitle());
        Assertions.assertEquals(decisionCreated.getPages(), event.getPages());
    }
}