package main.java.org.example.court.domain.action.command;

import main.java.org.example.court.generic.Command;

public class ChangePagesCommand extends Command {

    private String actionID;
    private Integer newPages;

    public ChangePagesCommand(String actionID, Integer newPages) {
        this.actionID = actionID;
        this.newPages = newPages;
    }

    public String getActionID() {
        return actionID;
    }

    public void setActionID(String actionID) {
        this.actionID = actionID;
    }

    public Integer getNewPages() {
        return newPages;
    }

    public void setNewPages(Integer newPages) {
        this.newPages = newPages;
    }
}
