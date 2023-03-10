package org.example.court.domain.action.events;

import org.example.court.generic.DomainEvent;

public class PhoneChangedFromLawyer extends DomainEvent {

    private final String lawyerID;
    private final String newPhone;

    public PhoneChangedFromLawyer(String lawyerID, String newPhone) {
        super("org.example.court.domain.action.events.PhoneChangedFromLawyer");
        this.lawyerID = lawyerID;
        this.newPhone = newPhone;
    }

    public String getLawyerID() {
        return lawyerID;
    }

    public String getNewPhone() {
        return newPhone;
    }
}
