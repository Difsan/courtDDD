package main.java.org.example.court.domain.decision.events;

import main.java.org.example.court.generic.DomainEvent;

import java.time.LocalDate;

public class DecisionCreated extends DomainEvent {
    private final String title;

    public DecisionCreated(String title) {
        super("main.java.org.example.court.domain.decision.events.DecisionCreated");
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
