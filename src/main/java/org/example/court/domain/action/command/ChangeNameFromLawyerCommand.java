package main.java.org.example.court.domain.action.command;

import main.java.org.example.court.generic.Command;

public class ChangeNameFromLawyerCommand extends Command {

    private String actionID;
    private String partID;
    private String lawyerID;
    private String name;

    public ChangeNameFromLawyerCommand(String actionID, String partID, String lawyerID, String name) {
        this.actionID = actionID;
        this.partID = partID;
        this.lawyerID = lawyerID;
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

    public String getLawyerID() {
        return lawyerID;
    }

    public void setLawyerID(String lawyerID) {
        this.lawyerID = lawyerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
