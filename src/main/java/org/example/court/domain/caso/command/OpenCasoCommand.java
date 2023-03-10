package org.example.court.domain.caso.command;

import org.example.court.generic.Command;

public class OpenCasoCommand extends Command {
    private String caseID;
    private String state;

    public OpenCasoCommand(String caseID, String state) {
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
