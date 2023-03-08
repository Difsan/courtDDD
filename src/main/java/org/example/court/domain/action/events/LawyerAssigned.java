package main.java.org.example.court.domain.action.events;

import main.java.org.example.court.generic.DomainEvent;

public class LawyerAssigned extends DomainEvent {

    private final String lawyerID;
    private final String name;
    private final String nit;
    private final String professionalCard;

    public LawyerAssigned(String lawyerID, String professionalCard, String name, String nit) {
        super("main.java.org.example.court.domain.action.events.LawyerAssignedToPart");
        this.lawyerID = lawyerID;
        this.professionalCard = professionalCard;
        this.name = name;
        this.nit = nit;
    }

    public String getLawyerID() {
        return lawyerID;
    }

    public String getProfessionalCard() {
        return professionalCard;
    }

    public String getName() {
        return name;
    }

    public String getNit() {
        return nit;
    }
}
