package main.java.org.example.court.domain.action.command;

import main.java.org.example.court.generic.Command;

public class ChangePhoneFromLawyerCommand extends Command {

    private String actionID;
    private String partID;
    private String lawyerID;
    private String newPhone;

    public ChangePhoneFromLawyerCommand(String actionID, String partID, String lawyerID, String newPhone) {
        this.actionID = actionID;
        this.partID = partID;
        this.lawyerID = lawyerID;
        this.newPhone = newPhone;
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

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

}
