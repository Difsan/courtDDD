package main.java.org.example.court.domain.action.events;

import main.java.org.example.court.domain.action.Lawyer;
import main.java.org.example.court.domain.action.values.PartID;
import main.java.org.example.court.domain.commonValues.Name;
import main.java.org.example.court.domain.commonValues.Nit;
import main.java.org.example.court.domain.commonValues.Type;
import main.java.org.example.court.generic.DomainEvent;
import main.java.org.example.court.generic.Entity;

public class PartAssigned extends DomainEvent {

    private final String partID;
    private final String type;
    private final String name;
    private final String nit;

    public PartAssigned(String partID, String type, String name, String nit) {
        super("main.java.org.example.court.domain.action.events.PartAssigned");
        this.partID = partID;
        this.type = type;
        this.name = name;
        this.nit = nit;
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
}
