package org.example.court.domain.action.command;

import org.example.court.generic.Command;

public class ChangeNameFromLawyerCommand extends Command {

    private String actionID;
    private String partID;
    private String lawyerID;
    private String newName;

    public ChangeNameFromLawyerCommand(String actionID, String partID, String lawyerID, String newName) {
        this.actionID = actionID;
        this.partID = partID;
        this.lawyerID = lawyerID;
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

    public String getLawyerID() {
        return lawyerID;
    }

    public void setLawyerID(String lawyerID) {
        this.lawyerID = lawyerID;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

}
