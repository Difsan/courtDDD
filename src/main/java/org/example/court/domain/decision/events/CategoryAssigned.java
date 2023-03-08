package main.java.org.example.court.domain.decision.events;

import main.java.org.example.court.generic.DomainEvent;

public class CategoryAssigned extends DomainEvent {

    private final String categoryID;
    private final String type;

    public CategoryAssigned(String categoryID, String type) {
        super("main.java.org.example.court.domain.decision.events.CategoryCreated");
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
