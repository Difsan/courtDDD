package org.example.court.domain.action.events;

import org.example.court.generic.DomainEvent;

public class NameChangedFromLawyer extends DomainEvent {

    private final String lawyerID;
    private final String newName;

    public NameChangedFromLawyer(String lawyerID, String newName) {
        super("org.example.court.domain.action.events.NameChangedFromLayer");
        this.lawyerID = lawyerID;
        this.newName = newName;
    }

    public String getLawyerID() {
        return lawyerID;
    }

    public String getNewName() {
        return newName;
    }
}
