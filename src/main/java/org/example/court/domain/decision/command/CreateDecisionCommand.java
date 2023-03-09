package main.java.org.example.court.domain.decision.command;

import main.java.org.example.court.generic.Command;

import java.time.LocalDate;

public class CreateDecisionCommand extends Command {

    private String decisionID;
    private String title;
    private Integer pages;

    public CreateDecisionCommand(String decisionID, String title, Integer pages) {
        this.decisionID = decisionID;
        this.title = title;
        this.pages = pages;
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

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
