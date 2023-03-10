package org.example.court.domain.action.events;

import org.example.court.generic.DomainEvent;

public class AddressChangedFromPart extends DomainEvent {

    private final String partID;
    private final String newAddress;

    public AddressChangedFromPart(String partID, String newAddress) {
        super("org.example.court.domain.action.events.AddressChangedFromPart");
        this.partID = partID;
        this.newAddress = newAddress;
    }

    public String getPartID() {
        return partID;
    }

    public String getNewAddress() {
        return newAddress;
    }
}
