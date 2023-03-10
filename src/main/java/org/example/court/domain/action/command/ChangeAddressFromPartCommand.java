package org.example.court.domain.action.command;

import org.example.court.generic.Command;

public class ChangeAddressFromPartCommand extends Command {

    private String actionID;
    private String partID;
    private String newAddress;

    public ChangeAddressFromPartCommand(String actionID, String partID, String newAddress) {
        this.actionID = actionID;
        this.partID = partID;
        this.newAddress = newAddress;
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

    public String getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }
}
