package org.example.court.domain.caso.command;

import org.example.court.generic.Command;

public class AssignGuarantorCommand extends Command {

    private String caseID;
    private String guarantorID;
    private String name;
    private String nit;

    public AssignGuarantorCommand(String caseID, String guarantorID, String name, String nit) {
        this.caseID = caseID;
        this.guarantorID = guarantorID;
        this.name = name;
        this.nit = nit;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
}
