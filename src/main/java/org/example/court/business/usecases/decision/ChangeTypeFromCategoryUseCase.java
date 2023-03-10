package org.example.court.business.usecases.decision;

import org.example.court.business.commons.EventsRepository;
import org.example.court.business.commons.UseCaseForCommand;
import org.example.court.domain.commonValues.Title;
import org.example.court.domain.commonValues.Type;
import org.example.court.domain.decision.Decision;
import org.example.court.domain.decision.command.ChangeTitleCommand;
import org.example.court.domain.decision.command.ChangeTypeFromCategoryCommand;
import org.example.court.domain.decision.values.CategoryID;
import org.example.court.domain.decision.values.DecisionID;
import org.example.court.generic.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangeTypeFromCategoryUseCase implements UseCaseForCommand<ChangeTypeFromCategoryCommand> {
    private EventsRepository eventsRepository;

    public ChangeTypeFromCategoryUseCase(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<DomainEvent> apply(ChangeTypeFromCategoryCommand command) {
        List<DomainEvent> domainEvents = eventsRepository.findByAggregatedRootId(command.getDecisionID());
        Decision decision = Decision.from(DecisionID.of(command.getDecisionID()), domainEvents);
        if(decision.category().type().value().equals(command.getNewType())) {
            throw new IllegalArgumentException("Category's type is already " + command.getNewType());
        }
        decision.changeTypeFromCategory(CategoryID.of(command.getCategoryID()),new Type(command.getNewType()));
        return decision.getUncommittedChanges().stream()
                .map(event->eventsRepository.saveEvent(event)).collect(Collectors.toList());
    }
}
