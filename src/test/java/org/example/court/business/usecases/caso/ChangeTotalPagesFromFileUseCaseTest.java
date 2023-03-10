package org.example.court.business.usecases.caso;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.caso.command.ChangeTotalPagesFromFileCommand;
import org.example.court.domain.caso.command.CreateFileCommand;
import org.example.court.domain.caso.events.CasoOpened;
import org.example.court.domain.caso.events.FileCreated;
import org.example.court.domain.caso.events.TotalPagesChangedFromFile;
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
class ChangeTotalPagesFromFileUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;

    private ChangeTotalPagesFromFileUseCase changeTotalPagesFromFileUseCase;

    @BeforeEach
    void setUp() { changeTotalPagesFromFileUseCase = new ChangeTotalPagesFromFileUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){

        CasoOpened casoOpened = new CasoOpened("inProcess");
        casoOpened.setAggregateRootId("casoID");

        FileCreated fileCreated = new FileCreated("fileID");
        fileCreated.setAggregateRootId("casoID");

        ChangeTotalPagesFromFileCommand command = new ChangeTotalPagesFromFileCommand("casoID",
                "fileID", 9);

        TotalPagesChangedFromFile totalPagesChangedFromFile = new TotalPagesChangedFromFile("fileID",9);
        totalPagesChangedFromFile.setAggregateRootId("casoID");
        Mockito.when(eventsRepository.findByAggregatedRootId("casoID")).thenReturn(List.of(
                casoOpened, fileCreated));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(TotalPagesChangedFromFile.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = changeTotalPagesFromFileUseCase.apply(command);
        TotalPagesChangedFromFile event = (TotalPagesChangedFromFile) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(totalPagesChangedFromFile.aggregateRootId(), domainEventList.get(0).aggregateRootId());

    }
}