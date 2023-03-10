package org.example.court.domain.action.command;

import org.example.court.generic.Command;

import java.time.LocalDate;

public class PresentActionCommand extends Command {

    private String actionID;
    private String title;

    private Integer pages;

    public PresentActionCommand(String actionID, String title, Integer pages) {
        this.actionID = actionID;
        this.title = title;
        this.pages = pages;
    }

    public String getActionID() {
        return actionID;
    }

    public void setActionID(String actionID) {
        this.actionID = actionID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
