package org.example.court.domain.decision.command;

import org.example.court.generic.Command;
import org.example.court.generic.DomainEvent;

public class ChangeTypeFromCategoryCommand extends Command {

    private String decisionID;
    private String categoryID;
    private String newType;

    public ChangeTypeFromCategoryCommand(String decisionID, String categoryID, String newType) {
        this.decisionID = decisionID;
        this.categoryID = categoryID;
        this.newType = newType;
    }

    public String getDecisionID() {
        return decisionID;
    }

    public void setDecisionID(String decisionID) {
        this.decisionID = decisionID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getNewType() {
        return newType;
    }

    public void setNewType(String newType) {
        this.newType = newType;
    }
}
