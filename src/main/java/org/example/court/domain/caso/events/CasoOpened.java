package org.example.court.domain.caso.events;

import org.example.court.generic.DomainEvent;

public class CasoOpened extends DomainEvent {
    private final String state;

    public CasoOpened(String state) {
        super("org.example.court.domain.cases.events.CaseOpen");
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
