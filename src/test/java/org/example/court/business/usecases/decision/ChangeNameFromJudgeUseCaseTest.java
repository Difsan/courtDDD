package org.example.court.business.usecases.decision;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.decision.command.AssignJudgeCommand;
import org.example.court.domain.decision.command.ChangeNameFromJudgeCommand;
import org.example.court.domain.decision.events.DecisionCreated;
import org.example.court.domain.decision.events.JudgeAssigned;
import org.example.court.domain.decision.events.NameChangedFromJudge;
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
class ChangeNameFromJudgeUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;

    private ChangeNameFromJudgeUseCase changeNameFromJudgeUseCase;

    @BeforeEach
    void setUp(){ changeNameFromJudgeUseCase = new ChangeNameFromJudgeUseCase(eventsRepository);}

    @Test
    void successfulScenario(){

        DecisionCreated decisionCreated = new DecisionCreated("answerToDemand", 5);
        decisionCreated.setAggregateRootId("decisionID");

        JudgeAssigned judgeAssigned = new JudgeAssigned("judgeID",
                "Ana");
        judgeAssigned.setAggregateRootId("decisionID");

        ChangeNameFromJudgeCommand command = new ChangeNameFromJudgeCommand("decisionID", "judgeID",
                "Maria");

        NameChangedFromJudge nameChangedFromJudge = new NameChangedFromJudge("judgeID",
                "Maria");

        Mockito.when(eventsRepository.findByAggregatedRootId("decisionID")).thenReturn(List.of(
                decisionCreated, judgeAssigned));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(NameChangedFromJudge.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = changeNameFromJudgeUseCase.apply(command);
        NameChangedFromJudge  event = (NameChangedFromJudge) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(judgeAssigned.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(nameChangedFromJudge.getJudgeID(), event.getJudgeID());
        Assertions.assertEquals(nameChangedFromJudge.getNewName(), event.getNewName());
    }
}