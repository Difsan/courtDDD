package org.example.court.domain.action.events;

import org.example.court.generic.DomainEvent;

public class TitleChanged extends DomainEvent {
    private final String newTitle;

    public TitleChanged(String newTitle) {
        super("org.example.court.domain.action.events.TitleChanged");
        this.newTitle = newTitle;
    }

    public String getNewTitle() {
        return newTitle;
    }
}
