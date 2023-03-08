package main.java.org.example.court.domain.decision.command;

import main.java.org.example.court.generic.Command;

public class ChangeTitleCommand extends Command {

    private String decisionID;
    private String newTitle;

    public ChangeTitleCommand(String decisionID, String newTitle) {
        this.decisionID = decisionID;
        this.newTitle = newTitle;
    }

    public String getDecisionID() {
        return decisionID;
    }

    public void setDecisionID(String decisionID) {
        this.decisionID = decisionID;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }
}
