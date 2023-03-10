package main.java.org.example.court.domain.caso.events;

import main.java.org.example.court.generic.DomainEvent;

public class CasoOpened extends DomainEvent {
    private final String state;

    public CasoOpened(String state) {
        super("main.java.org.example.court.domain.cases.events.CaseOpen");
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
