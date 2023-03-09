package main.java.org.example.court.domain.cases.events;

import main.java.org.example.court.generic.DomainEvent;

public class DecisionIDAdded extends DomainEvent {

    private final String caseID;
    private final String fileID;
    private final String decisionID;

    public DecisionIDAdded(String caseID, String fileID, String decisionID) {
        super("main.java.org.example.court.domain.cases.events.DecisionIDAdded");
        this.caseID = caseID;
        this.fileID = fileID;
        this.decisionID = decisionID;
    }

    public String getCaseID() {
        return caseID;
    }

    public String getFileID() {
        return fileID;
    }

    public String getDecisionID() {
        return decisionID;
    }
}
