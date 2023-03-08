package main.java.org.example.court.domain.action.command;

import main.java.org.example.court.generic.Command;

public class ChangeNameFromPartCommand extends Command {

    private String actionID;
    private String partID;
    private String name;

    public ChangeNameFromPartCommand(String actionID, String partID, String name) {
        this.actionID = actionID;
        this.partID = partID;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
