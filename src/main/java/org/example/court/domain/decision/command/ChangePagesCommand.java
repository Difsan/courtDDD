package main.java.org.example.court.domain.decision.command;

import main.java.org.example.court.generic.Command;

public class ChangePagesCommand extends Command {

    private String decisionID;
    private Integer newPages;

    public ChangePagesCommand(String decisionID, Integer newPages) {
        this.decisionID = decisionID;
        this.newPages = newPages;
    }

    public String getDecisionID() {
        return decisionID;
    }

    public void setDecisionID(String decisionID) {
        this.decisionID = decisionID;
    }

    public Integer getNewPages() {
        return newPages;
    }

    public void setNewPages(Integer newPages) {
        this.newPages = newPages;
    }
}
