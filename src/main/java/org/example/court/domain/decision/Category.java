package main.java.org.example.court.domain.decision;

import main.java.org.example.court.domain.commonValues.Type;
import main.java.org.example.court.domain.decision.values.CategoryID;
import main.java.org.example.court.generic.Entity;

public class Category extends Entity<CategoryID> {

    private Type type;
    public Category(CategoryID categoryID, Type type) {
        super(categoryID);
        this.type = type;
    }

    public void changeType(Type type){ this.type = type; }

    public Type type(){
        return type;
    }
}
