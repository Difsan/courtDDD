package org.example.court.business.usecases.caso;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.caso.command.OpenCasoCommand;
import org.example.court.domain.caso.events.CasoOpened;
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
class OpenCasoUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;

    private OpenCasoUseCase openCasoUseCase;

    @BeforeEach
    void setUp() { openCasoUseCase = new OpenCasoUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){
        OpenCasoCommand openCasoCommand = new OpenCasoCommand("casoID", "inProcess");

        CasoOpened casoOpened = new CasoOpened("inProcess");
        casoOpened.setAggregateRootId(openCasoCommand.getCaseID());

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(CasoOpened.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });
        List<DomainEvent> domainEventList = openCasoUseCase.apply(openCasoCommand);
        CasoOpened event = (CasoOpened) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(casoOpened.aggregateRootId(), domainEventList.get(0).aggregateRootId());
        Assertions.assertEquals(casoOpened.getState(), ((CasoOpened) domainEventList.get(0)).getState());
    }
}