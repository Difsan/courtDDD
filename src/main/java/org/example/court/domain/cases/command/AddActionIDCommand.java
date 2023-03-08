package main.java.org.example.court.domain.cases.command;

import main.java.org.example.court.generic.Command;
import main.java.org.example.court.generic.DomainEvent;

public class AddActionIDCommand extends Command {

    private String caseID;
    private String fileID;
    private String actionID;

    public AddActionIDCommand(String caseID, String fileID, String actionID) {
        this.caseID = caseID;
        this.fileID = fileID;
        this.actionID = actionID;
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

    public String getActionID() {
        return actionID;
    }

    public void setActionID(String actionID) {
        this.actionID = actionID;
    }
}
