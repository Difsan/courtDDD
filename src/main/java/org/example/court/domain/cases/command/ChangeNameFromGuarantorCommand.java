package main.java.org.example.court.domain.cases.command;

import main.java.org.example.court.generic.Command;
import main.java.org.example.court.generic.DomainEvent;

public class ChangeNameFromGuarantorCommand extends Command {

    private String guarantorID;
    private String newName;

    public ChangeNameFromGuarantorCommand(String guarantorID, String newName) {
        this.guarantorID = guarantorID;
        this.newName = newName;
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
