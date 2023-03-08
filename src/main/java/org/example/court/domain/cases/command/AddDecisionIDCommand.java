package main.java.org.example.court.domain.cases.command;

import main.java.org.example.court.generic.Command;
import main.java.org.example.court.generic.DomainEvent;

public class AddDecisionIDCommand extends Command {

    private String caseID;
    private String fileID;
    private String decisionID;

    public AddDecisionIDCommand(String caseID, String fileID, String decisionID) {
        this.caseID = caseID;
        this.fileID = fileID;
        this.decisionID = decisionID;
    }

    public String getCaseID() {
        return caseID;
    }

    public void setCaseID(String caseID) {
        this.caseID = caseID;
    }

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public String getDecisionID() {
        return decisionID;
    }

    public void setDecisionID(String decisionID) {
        this.decisionID = decisionID;
    }
}
