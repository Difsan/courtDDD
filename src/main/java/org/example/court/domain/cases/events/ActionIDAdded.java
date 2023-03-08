package main.java.org.example.court.domain.cases.events;

import main.java.org.example.court.generic.DomainEvent;

import java.time.LocalDate;

public class ActionIDAdded extends DomainEvent {

    private final String fileID;
    private final String actionID;

    public ActionIDAdded(String fileID, String actionID) {
        super("main.java.org.example.court.domain.cases.events.ActionIDAdded");
        this.fileID = fileID;
        this.actionID = actionID;
    }

    public String getFileID() {
        return fileID;
    }

    public String getActionID() {
        return actionID;
    }
}
