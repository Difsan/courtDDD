package org.example.court.domain.action.command;

import org.example.court.generic.Command;

public class ChangeEmailFromLawyerCommand extends Command {

    private String actionID;
    private String partID;
    private String lawyerID;
    private String newEmail;

    public ChangeEmailFromLawyerCommand(String actionID, String partID, String lawyerID, String newEmail) {
        this.actionID = actionID;
        this.partID = partID;
        this.lawyerID = lawyerID;
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

    public String getLawyerID() {
        return lawyerID;
    }

    public void setLawyerID(String lawyerID) {
        this.lawyerID = lawyerID;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

}
