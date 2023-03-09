package main.java.org.example.court.domain.action.events;

import main.java.org.example.court.generic.DomainEvent;

import java.time.LocalDate;

public class ActionPresented extends DomainEvent {
    private final String title;

    public ActionPresented(String title) {
        super("main.java.org.example.court.domain.action.events.ActionCreated");
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
