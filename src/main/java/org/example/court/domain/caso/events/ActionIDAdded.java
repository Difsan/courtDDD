package main.java.org.example.court.domain.caso.events;

import main.java.org.example.court.generic.DomainEvent;

public class ActionIDAdded extends DomainEvent {

    private final String caseID;
    private final String fileID;
    private final String actionID;

    public ActionIDAdded(String caseID, String fileID, String actionID) {
        super("main.java.org.example.court.domain.cases.events.ActionIDAdded");
        this.caseID = caseID;
        this.fileID = fileID;
        this.actionID = actionID;
    }

    public String getCaseID() {
        return caseID;
    }

    public String getFileID() {
        return fileID;
    }

    public String getActionID() {
        return actionID;
    }
}
