package org.example.court.domain.caso.command;

import org.example.court.generic.Command;

public class ChangeTotalPagesFromFileCommand extends Command {

    private String casoID;
    private String fileID;
    private Integer newTotalPages;

    public ChangeTotalPagesFromFileCommand(String casoID, String fileID, Integer newTotalPages) {
        this.casoID = casoID;
        this.fileID = fileID;
        this.newTotalPages = newTotalPages;
    }

    public String getCasoID() {
        return casoID;
    }

    public void setCasoID(String casoID) {
        this.casoID = casoID;
    }

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public Integer getNewTotalPages() {
        return newTotalPages;
    }

    public void setNewTotalPages(Integer newTotalPages) {
        this.newTotalPages = newTotalPages;
    }
}
