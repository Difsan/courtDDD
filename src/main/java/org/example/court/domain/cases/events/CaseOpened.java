package main.java.org.example.court.domain.cases.events;

import main.java.org.example.court.generic.DomainEvent;

public class CaseOpened extends DomainEvent {
    private final String state;

    public CaseOpened(String state) {
        super("main.java.org.example.court.domain.cases.events.CaseOpen");
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
