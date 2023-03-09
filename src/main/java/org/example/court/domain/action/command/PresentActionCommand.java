package main.java.org.example.court.domain.action.command;

import main.java.org.example.court.generic.Command;

import java.time.LocalDate;

public class PresentActionCommand extends Command {

    private String actionID;
    private String title;

    public PresentActionCommand(String actionID, String title) {
        this.actionID = actionID;
        this.title = title;
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
}
