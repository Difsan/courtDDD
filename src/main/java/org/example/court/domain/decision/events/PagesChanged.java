package main.java.org.example.court.domain.decision.events;

import main.java.org.example.court.generic.DomainEvent;

public class PagesChanged extends DomainEvent {
    private final Integer newPages;

    public PagesChanged(Integer newPages) {
        super("main.java.org.example.court.domain.decision.events.PagesChanged");
        this.newPages = newPages;
    }

    public Integer getNewPages() {
        return newPages;
    }
}
