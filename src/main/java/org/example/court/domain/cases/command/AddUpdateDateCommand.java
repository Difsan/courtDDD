package main.java.org.example.court.domain.cases.command;

import main.java.org.example.court.generic.Command;
import main.java.org.example.court.generic.DomainEvent;

import java.time.LocalDate;

public class AddUpdateDateCommand extends Command {

    private String caseID;
    private String fileID;
    private LocalDate updateDate;

    public AddUpdateDateCommand(String caseID, String fileID, LocalDate updateDate) {
        this.caseID = caseID;
        this.fileID = fileID;
        this.updateDate = updateDate;
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

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }
}
