package org.example.court.domain.caso.events;

import org.example.court.generic.DomainEvent;

public class GuarantorAssigned extends DomainEvent {

    private final String guarantorID;
    private final String name;
    private final String nit;

    public GuarantorAssigned(String guarantorID, String name, String nit) {
        super("org.example.court.domain.cases.events.GuarantorAssigned");
        this.guarantorID = guarantorID;
        this.name = name;
        this.nit = nit;
    }

    public String getGuarantorID() {
        return guarantorID;
    }

    public String getName() {
        return name;
    }

    public String getNit() {
        return nit;
    }
}
