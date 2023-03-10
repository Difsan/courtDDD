package org.example.court.domain.action.events;

import org.example.court.domain.commonValues.Email;
import org.example.court.domain.commonValues.Phone;
import org.example.court.generic.DomainEvent;

public class LawyerAssigned extends DomainEvent {

    private final String lawyerID;
    private final String name;
    private final String nit;

    private final String phone;

    private final String email;
    private final String professionalCard;

    public LawyerAssigned(String lawyerID, String name,
                          String nit, String phone, String email, String professionalCard) {
        super("org.example.court.domain.action.events.LawyerAssignedToPart");
        this.lawyerID = lawyerID;

        this.name = name;
        this.nit = nit;
        this.phone = phone;
        this.email = email;
        this.professionalCard = professionalCard;
    }

    public String getLawyerID() {
        return lawyerID;
    }

    public String getName() {
        return name;
    }

    public String getNit() {
        return nit;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
    public String getProfessionalCard() {
        return professionalCard;
    }
}
