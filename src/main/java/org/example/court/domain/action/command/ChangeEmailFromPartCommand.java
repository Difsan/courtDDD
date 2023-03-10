package main.java.org.example.court.domain.action.command;

import main.java.org.example.court.generic.Command;

public class ChangeEmailFromPartCommand extends Command {

    private String actionID;
    private String partID;
    private String newEmail;

    public ChangeEmailFromPartCommand(String actionID, String partID, String newEmail) {
        this.actionID = actionID;
        this.partID = partID;
        this.newEmail = newEmail;
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

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
}
