package main.java.org.example.court.domain.action.command;

import main.java.org.example.court.generic.Command;

public class AssignPartCommand extends Command {

    private String actionID;
    private String partID;
    private String type;
    private String name;
    private String nit;

    public AssignPartCommand(String actionID, String partID, String type, String name, String nit) {
        this.actionID = actionID;
        this.partID = partID;
        this.type = type;
        this.name = name;
        this.nit = nit;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
}
