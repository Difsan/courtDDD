package org.example.court.domain.caso.events;

import org.example.court.generic.DomainEvent;

public class TotalPagesChangedFromFile extends DomainEvent {

    private final String guarantorID;
    private final Integer newTotalPages;

    public TotalPagesChangedFromFile(String guarantorID, Integer newTotalPages) {
        super("org.example.court.domain.caso.events.TotalPagesChangedFromFile");
        this.guarantorID = guarantorID;
        this.newTotalPages = newTotalPages;
    }

    public String getGuarantorID() {
        return guarantorID;
    }

    public Integer getNewTotalPages() {
        return newTotalPages;
    }
}
