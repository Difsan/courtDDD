package main.java.org.example.court.domain.decision.command;

import main.java.org.example.court.generic.Command;
import main.java.org.example.court.generic.DomainEvent;

public class CreateCategoryCommand extends Command {

    private String decisionID;
    private String categoryID;
    private String type;

    public CreateCategoryCommand(String decisionID, String categoryID, String type) {
        this.decisionID = decisionID;
        this.categoryID = categoryID;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
