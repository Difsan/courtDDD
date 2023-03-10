package main.java.org.example.court.domain.caso.command;

import main.java.org.example.court.generic.Command;

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
