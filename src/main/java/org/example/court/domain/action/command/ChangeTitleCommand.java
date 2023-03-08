package main.java.org.example.court.domain.action.command;

import main.java.org.example.court.generic.Command;

public class ChangeTitleCommand extends Command {

    private String actionID;
    private String newTitle;

    public ChangeTitleCommand(String actionID, String newTitle) {
        this.actionID = actionID;
        this.newTitle = newTitle;
    }

    public String getActionID() {
        return actionID;
    }

    public void setActionID(String actionID) {
        this.actionID = actionID;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }
}
