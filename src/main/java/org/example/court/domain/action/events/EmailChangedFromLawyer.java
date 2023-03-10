package org.example.court.domain.action.events;

import org.example.court.generic.DomainEvent;

public class EmailChangedFromLawyer extends DomainEvent {

    private final String lawyerID;
    private final String newEmail;

    public EmailChangedFromLawyer(String lawyerID, String newEmail) {
        super("org.example.court.domain.action.events.EmailChangedFromLawyer");
        this.lawyerID = lawyerID;
        this.newEmail = newEmail;
    }

    public String getLawyerID() {
        return lawyerID;
    }

    public String getNewEmail() {
        return newEmail;
    }
}
