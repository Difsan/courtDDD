package main.java.org.example.court.domain.action.command;

import main.java.org.example.court.generic.Command;

public class AssignPartCommand extends Command {

    private String actionID;
    private String partID;
    private String type;
    private String name;
    private String nit;

    private String phone;

    private String email;

    private String address;

    public AssignPartCommand(String actionID, String partID, String type, String name, String nit, String phone, String email, String address) {
        this.actionID = actionID;
        this.partID = partID;
        this.type = type;
        this.name = name;
        this.nit = nit;
        this.phone = phone;
        this.email = email;
        this.address = address;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
