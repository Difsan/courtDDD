package org.example.court.domain.decision.events;

import org.example.court.generic.DomainEvent;

public class CategoryAssigned extends DomainEvent {

    private final String categoryID;
    private final String type;

    public CategoryAssigned(String categoryID, String type) {
        super("org.example.court.domain.decision.events.CategoryCreated");
        this.categoryID = categoryID;
        this.type = type;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getType() {
        return type;
    }
}
