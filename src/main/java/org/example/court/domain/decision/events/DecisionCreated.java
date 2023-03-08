package main.java.org.example.court.domain.decision.events;

import main.java.org.example.court.generic.DomainEvent;

import java.time.LocalDate;

public class DecisionCreated extends DomainEvent {

    private final LocalDate createDate;
    private final String title;

    public DecisionCreated(String title) {
        super("main.java.org.example.court.domain.decision.events.DecisionCreated");
        this.createDate = LocalDate.now();
        this.title = title;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public String getTitle() {
        return title;
    }
}
