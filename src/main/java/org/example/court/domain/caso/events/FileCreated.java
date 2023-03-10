package org.example.court.domain.caso.events;

import org.example.court.generic.DomainEvent;

import java.time.LocalDate;

public class FileCreated extends DomainEvent {

    //private final LocalDate createDate;
    private final String fileID;

    //private Integer totalPages;

    public FileCreated(String fileID) {
        super("org.example.court.domain.cases.events.FileCreated");
        //this.createDate = LocalDate.now();
        this.fileID = fileID;
    }

    /*public LocalDate getCreateDate() {
        return createDate;
    }*/

    public String getFileID() {
        return fileID;
    }
}
