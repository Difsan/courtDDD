package org.example.court.domain.caso.events;

import org.example.court.generic.DomainEvent;

public class NameChangedFromGuarantor extends DomainEvent {

    private final String guarantorID;
    private final String newName;

    public NameChangedFromGuarantor(String guarantorID, String newName) {
        super("org.example.court.domain.cases.events.NameChangedFromGuarantor");
        this.guarantorID = guarantorID;
        this.newName = newName;
    }

    public String getGuarantorID() {
        return guarantorID;
    }

    public String getNewName() {
        return newName;
    }
}
