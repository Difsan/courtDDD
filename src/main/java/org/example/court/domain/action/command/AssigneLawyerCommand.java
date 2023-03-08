package main.java.org.example.court.domain.action.command;

import main.java.org.example.court.generic.Command;
import main.java.org.example.court.generic.DomainEvent;

public class AssigneLawyerCommand extends Command {

    private String actionID;
    private String partID;
    private String lawyerID;
    private String name;
    private String nit;
    private String professionalCard;

    public AssigneLawyerCommand(String actionID, String partID, String lawyerID, String name,
                                String nit, String professionalCard) {
        this.actionID = actionID;
        this.partID = partID;
        this.lawyerID = lawyerID;
        this.name = name;
        this.nit = nit;
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

    public String getProfessionalCard() {
        return professionalCard;
    }

    public void setProfessionalCard(String professionalCard) {
        this.professionalCard = professionalCard;
    }
}
