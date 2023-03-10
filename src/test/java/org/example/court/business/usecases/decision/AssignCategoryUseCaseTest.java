package org.example.court.business.usecases.decision;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.decision.command.AssignCategoryCommand;
import org.example.court.domain.decision.events.CategoryAssigned;
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

@ExtendWith(MockitoExtension.class)
class AssignCategoryUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;

    private AssignCategoryUseCase assignCategoryUseCase;

    @BeforeEach
    void setUp(){ assignCategoryUseCase = new AssignCategoryUseCase(eventsRepository);}

    @Test
    void successfulScenario(){

        DecisionCreated decisionCreated = new DecisionCreated("answerToDemand", 5);
        decisionCreated.setAggregateRootId("decisionID");

        AssignCategoryCommand assignCategoryCommand = new AssignCategoryCommand("decisionID",
                "categoryID", "answer");

        CategoryAssigned categoryAssigned = new CategoryAssigned("categoryID", "answer");
        categoryAssigned.setAggregateRootId("decisionID");

        Mockito.when(eventsRepository.findByAggregatedRootId("decisionID")).thenReturn(List.of(
                decisionCreated));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(CategoryAssigned.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = assignCategoryUseCase.apply(assignCategoryCommand);
        CategoryAssigned  event = (CategoryAssigned) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(categoryAssigned.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(categoryAssigned.getCategoryID(), event.getCategoryID());
        Assertions.assertEquals(categoryAssigned.getType(), event.getType());
    }
}