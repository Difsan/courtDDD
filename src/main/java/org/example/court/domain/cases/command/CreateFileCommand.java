package main.java.org.example.court.domain.cases.command;

import main.java.org.example.court.generic.Command;
import main.java.org.example.court.generic.DomainEvent;

import java.time.LocalDate;

public class CreateFileCommand extends Command {

    private String caseID;
    private String fileID;

    public CreateFileCommand(String caseID, String fileID) {
        this.caseID = caseID;
        this.fileID = fileID;
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
}
