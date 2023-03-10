package main.java.org.example.court.domain.action.command;

import main.java.org.example.court.generic.Command;

public class AssignLawyerCommand extends Command {

    private String actionID;
    private String partID;
    private String lawyerID;
    private String name;
    private String nit;

    private String phone;

    private String email;
    private String professionalCard;

    public AssignLawyerCommand(String actionID, String partID, String lawyerID, String name, String nit, String phone, String email, String professionalCard) {
        this.actionID = actionID;
        this.partID = partID;
        this.lawyerID = lawyerID;
        this.name = name;
        this.nit = nit;
        this.phone = phone;
        this.email = email;
        this.professionalCard = professionalCard;
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

    public String getProfessionalCard() {
        return professionalCard;
    }

    public void setProfessionalCard(String professionalCard) {
        this.professionalCard = professionalCard;
    }
}
