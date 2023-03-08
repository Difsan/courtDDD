package main.java.org.example.court.domain.cases.command;

import main.java.org.example.court.generic.Command;
import main.java.org.example.court.generic.DomainEvent;

public class OpenCaseCommand extends Command {
    private String caseID;
    private String state;

    public OpenCaseCommand(String caseID, String state) {
        this.caseID = caseID;
        this.state = state;
    }

    public String getCaseID() {
        return caseID;
    }

    public void setCaseID(String caseID) {
        this.caseID = caseID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
