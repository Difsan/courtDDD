package main.java.org.example.court.domain.decision.command;

import main.java.org.example.court.generic.Command;

import java.time.LocalDate;

public class CreateDecisionCommand extends Command {

    private String decisionID;
    private String title;

    public CreateDecisionCommand(String decisionID, String title) {
        this.decisionID = decisionID;
        this.title = title;
    }

    public String getDecisionID() {
        return decisionID;
    }

    public void setDecisionID(String decisionID) {
        this.decisionID = decisionID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
