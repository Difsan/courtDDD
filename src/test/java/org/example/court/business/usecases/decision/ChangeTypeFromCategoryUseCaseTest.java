package org.example.court.business.usecases.decision;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.decision.command.ChangeNameFromJudgeCommand;
import org.example.court.domain.decision.command.ChangeTypeFromCategoryCommand;
import org.example.court.domain.decision.events.*;
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
class ChangeTypeFromCategoryUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;

    private ChangeTypeFromCategoryUseCase changeTypeFromCategoryUseCase;

    @BeforeEach
    void setUp(){ changeTypeFromCategoryUseCase = new ChangeTypeFromCategoryUseCase(eventsRepository);}

    @Test
    void successfulScenario(){

        DecisionCreated decisionCreated = new DecisionCreated("answerToDemand", 5);
        decisionCreated.setAggregateRootId("decisionID");

        CategoryAssigned categoryAssigned = new CategoryAssigned("categoryID", "answer");
        categoryAssigned.setAggregateRootId("decisionID");

        ChangeTypeFromCategoryCommand command = new ChangeTypeFromCategoryCommand("decisionID", "categoryID",
                "appoint");

        TypeChangedFromCategory typeChangedFromCategory = new TypeChangedFromCategory("categoryID",
                "appoint");
        typeChangedFromCategory.setAggregateRootId("decisionID");

        Mockito.when(eventsRepository.findByAggregatedRootId("decisionID")).thenReturn(List.of(
                decisionCreated, categoryAssigned));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(TypeChangedFromCategory.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = changeTypeFromCategoryUseCase.apply(command);
        TypeChangedFromCategory event = (TypeChangedFromCategory) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(typeChangedFromCategory.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(typeChangedFromCategory.getCategoryID(), event.getCategoryID());
        Assertions.assertEquals(typeChangedFromCategory.getNewType(), event.getNewType());
    }
}