package org.example.court.domain.decision.events;

import org.example.court.generic.DomainEvent;

public class TypeChangedFromCategory extends DomainEvent {
    private final String categoryID;
    private final String newType;

    public TypeChangedFromCategory(String categoryID, String newType) {
        super("org.example.court.domain.decision.events.TypeChangedFromCategory");
        this.categoryID = categoryID;
        this.newType = newType;
    }

    public String getCategoryID() {
        return categoryID;
    }
    public String getNewType() {
        return newType;
    }
}
