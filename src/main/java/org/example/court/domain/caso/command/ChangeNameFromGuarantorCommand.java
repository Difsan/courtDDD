package org.example.court.domain.caso.command;

import org.example.court.generic.Command;

public class ChangeNameFromGuarantorCommand extends Command {

    private String caseID;
    private String guarantorID;
    private String newName;

    public ChangeNameFromGuarantorCommand(String caseID, String guarantorID, String newName) {
        this.caseID = caseID;
        this.guarantorID = guarantorID;
        this.newName = newName;
    }

    public String getCaseID() {
        return caseID;
    }

    public void setCaseID(String caseID) {
        this.caseID = caseID;
    }
    public String getGuarantorID() {
        return guarantorID;
    }

    public void setGuarantorID(String guarantorID) {
        this.guarantorID = guarantorID;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
