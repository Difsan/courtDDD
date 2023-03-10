package org.example.court.domain.action.events;

import org.example.court.generic.DomainEvent;

public class PartAssigned extends DomainEvent {

    private final String partID;
    private final String type;
    private final String name;
    private final String nit;

    private final String phone;

    private final String email;

    private final String address;

    public PartAssigned(String partID, String type, String name, String nit, String phone, String email, String address) {
        super("org.example.court.domain.action.events.PartAssigned");
        this.partID = partID;
        this.type = type;
        this.name = name;
        this.nit = nit;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getPartID() {
        return partID;
    }

    public String getType() {
        return type;
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

    public String getAddress() {
        return address;
    }
}
