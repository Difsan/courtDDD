package main.java.org.example.court.domain.decision.events;

import main.java.org.example.court.generic.DomainEvent;

public class DecisionCreated extends DomainEvent {
    private final String title;
    private final Integer pages;
    public DecisionCreated(String title, Integer pages) {
        super("main.java.org.example.court.domain.decision.events.DecisionCreated");
        this.title = title;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPages() {
        return pages;
    }
}
