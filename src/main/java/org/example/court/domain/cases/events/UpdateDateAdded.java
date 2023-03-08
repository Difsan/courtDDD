package main.java.org.example.court.domain.cases.events;

import main.java.org.example.court.generic.DomainEvent;

import java.time.LocalDate;

public class UpdateDateAdded extends DomainEvent {

    private final String fileID;
    private final LocalDate updateDate;

    public UpdateDateAdded(String fileID, LocalDate updateDate) {
        super("main.java.org.example.court.domain.cases.events.UpdateDateAdded");
        this.fileID = fileID;
        this.updateDate = updateDate;
    }

    public String getFileID() {
        return fileID;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }
}
