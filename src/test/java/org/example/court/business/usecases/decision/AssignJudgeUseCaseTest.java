package org.example.court.business.usecases.decision;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.decision.command.AssignCategoryCommand;
import org.example.court.domain.decision.command.AssignJudgeCommand;
import org.example.court.domain.decision.events.CategoryAssigned;
import org.example.court.domain.decision.events.DecisionCreated;
import org.example.court.domain.decision.events.JudgeAssigned;
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
class AssignJudgeUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;

    private AssignJudgeUseCase assignJudgeUseCase;

    @BeforeEach
    void setUp(){ assignJudgeUseCase = new AssignJudgeUseCase(eventsRepository);}

    @Test
    void successfulScenario(){

        DecisionCreated decisionCreated = new DecisionCreated("answerToDemand", 5);
        decisionCreated.setAggregateRootId("decisionID");

        AssignJudgeCommand assignJudgeCommand = new AssignJudgeCommand("decisionID", "judgeID",
                "Ana");

        JudgeAssigned judgeAssigned = new JudgeAssigned("judgeID",
                "Ana");
        judgeAssigned.setAggregateRootId("decisionID");

        Mockito.when(eventsRepository.findByAggregatedRootId("decisionID")).thenReturn(List.of(
                decisionCreated));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(JudgeAssigned.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = assignJudgeUseCase.apply(assignJudgeCommand);
        JudgeAssigned  event = (JudgeAssigned) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(judgeAssigned.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(judgeAssigned.getJudgeID(), event.getJudgeID());
        Assertions.assertEquals(judgeAssigned.getName(), event.getName());
    }
}