package main.java.org.example.court.domain.cases.command;

import main.java.org.example.court.generic.Command;

public class ChangeStateCommand extends Command {
    private String caseID;
    private String newState;

    public ChangeStateCommand(String caseID, String newState) {
        this.caseID = caseID;
        this.newState = newState;
    }

    public String getCaseID() {
        return caseID;
    }

    public void setCaseID(String caseID) {
        this.caseID = caseID;
    }

    public String getNewState() {
        return newState;
    }

    public void setNewState(String newState) {
        this.newState = newState;
    }
}
