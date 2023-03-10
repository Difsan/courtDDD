package org.example.court.domain.caso.events;

import org.example.court.generic.DomainEvent;

public class TotalPagesChangedFromFile extends DomainEvent {

    private final String fileID;
    private final Integer newTotalPages;

    public TotalPagesChangedFromFile(String fileID, Integer newTotalPages) {
        super("org.example.court.domain.caso.events.TotalPagesChangedFromFile");
        this.fileID = fileID;
        this.newTotalPages = newTotalPages;
    }

    public String getFileID() {
        return fileID;
    }

    public Integer getNewTotalPages() {
        return newTotalPages;
    }
}
