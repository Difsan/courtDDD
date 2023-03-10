package org.example.court.business.usecases.caso;

import org.example.court.business.commons.EventsRepository;
import org.example.court.domain.caso.command.CreateFileCommand;
import org.example.court.domain.caso.command.OpenCasoCommand;
import org.example.court.domain.caso.events.CasoOpened;
import org.example.court.domain.caso.events.FileCreated;
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
class CreateFileUseCaseTest {
    @Mock
    private EventsRepository eventsRepository;

    private CreateFileUseCase createFileUseCase;

    @BeforeEach
    void setUp() { createFileUseCase = new CreateFileUseCase(eventsRepository);
    }

    @Test
    void successfulScenario(){

        CasoOpened casoOpened = new CasoOpened("inProcess");
        casoOpened.setAggregateRootId("casoID");

        CreateFileCommand createFileCommand = new CreateFileCommand("casoID", "fileID");

        FileCreated fileCreated = new FileCreated("fileID");
        fileCreated.setAggregateRootId("casoID");

        Mockito.when(eventsRepository.findByAggregatedRootId("casoID")).thenReturn(List.of(
                casoOpened));

        Mockito.when(eventsRepository.saveEvent(ArgumentMatchers.any(FileCreated.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });

        List<DomainEvent> domainEventList = createFileUseCase.apply(createFileCommand);
        FileCreated event = (FileCreated) domainEventList.get(0);

        Assertions.assertEquals(1, domainEventList.size());
        Assertions.assertEquals(fileCreated.aggregateRootId(), domainEventList.get(0).aggregateRootId());

    }
}