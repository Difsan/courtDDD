package main.java.org.example.court.domain.action.events;

import main.java.org.example.court.generic.DomainEvent;

public class EmailChangedFromPart extends DomainEvent {

    private final String partID;
    private final String newEmail;

    public EmailChangedFromPart(String partID, String newEmail) {
        super("main.java.org.example.court.domain.action.events.EmailChangedFromPart");
        this.partID = partID;
        this.newEmail = newEmail;
    }

    public String getPartID() {
        return partID;
    }

    public String getNewEmail() {
        return newEmail;
    }
}
