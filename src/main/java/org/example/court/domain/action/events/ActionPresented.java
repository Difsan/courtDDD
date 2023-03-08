package main.java.org.example.court.domain.action.events;

import main.java.org.example.court.generic.DomainEvent;

import java.time.LocalDate;

public class ActionPresented extends DomainEvent {

    private final LocalDate createDate;
    private final String title;

    public ActionPresented(String title) {
        super("main.java.org.example.court.domain.action.events.ActionCreated");
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
