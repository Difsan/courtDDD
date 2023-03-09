package main.java.org.example.court.domain.action.command;

import main.java.org.example.court.generic.Command;

public class ChangeNameFromPartCommand extends Command {

    private String actionID;
    private String partID;
    private String newName;

    public ChangeNameFromPartCommand(String actionID, String partID, String newName) {
        this.actionID = actionID;
        this.partID = partID;
        this.newName = newName;
    }

    public String getActionID() {
        return actionID;
    }

    public void setActionID(String actionID) {
        this.actionID = actionID;
    }

    public String getPartID() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID = partID;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
