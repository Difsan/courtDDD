package main.java.org.example.court.domain.action.events;

import main.java.org.example.court.generic.DomainEvent;

public class NameChangedFromPart extends DomainEvent {

    private final String partID;
    private final String newName;

    public NameChangedFromPart(String partID, String newName) {
        super("main.java.org.example.court.domain.action.events.NameChangedFromPart");
        this.partID = partID;
        this.newName = newName;
    }

    public String getPartID() {
        return partID;
    }

    public String getNewName() {
        return newName;
    }
}
