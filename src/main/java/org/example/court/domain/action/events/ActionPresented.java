package main.java.org.example.court.domain.action.events;

import main.java.org.example.court.generic.DomainEvent;

import java.time.LocalDate;

public class ActionPresented extends DomainEvent {
    private final String title;

    private final Integer pages;
    public ActionPresented(String title, Integer pages) {
        super("main.java.org.example.court.domain.action.events.ActionCreated");
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
