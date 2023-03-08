package main.java.org.example.court.domain.action.command;

import main.java.org.example.court.generic.Command;

import java.time.LocalDate;

public class PresentActionCommand extends Command {

    private String actionID;
    private LocalDate createDate;
    private String title;

    public PresentActionCommand(String actionID, LocalDate createDate, String title) {
        this.actionID = actionID;
        this.createDate = createDate;
        this.title = title;
    }

    public String getActionID() {
        return actionID;
    }

    public void setActionID(String actionID) {
        this.actionID = actionID;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
