package main.java.org.example.court.domain.cases.events;

import main.java.org.example.court.generic.DomainEvent;

public class StateChanged extends DomainEvent {
    private final String newState;

    public StateChanged(String newState) {
        super("main.java.org.example.court.domain.cases.events.StateChanged");
        this.newState = newState;
    }

    public String getNewState() {
        return newState;
    }
}
