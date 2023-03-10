package org.example.court.domain.decision;

import org.example.court.domain.commonValues.*;
import org.example.court.domain.decision.events.*;
import org.example.court.domain.decision.values.CategoryID;
import org.example.court.domain.decision.values.DecisionID;
import org.example.court.domain.decision.values.JudgeID;
import org.example.court.generic.AggregateRoot;
import org.example.court.generic.DomainEvent;

import java.util.List;

public class Decision extends AggregateRoot<DecisionID> {

    protected CreateDate createDate;
    protected Title title;
    protected Pages pages;
    protected Category category;

    protected Judge judge;


    public Decision(DecisionID decisionID, Title title, Pages pages) {
        super(decisionID);
        subscribe(new DecisionChange(this));
        appendChange(new DecisionCreated(title.value(), pages.value())).apply();
    }

    private Decision(DecisionID decisionID) {
        super(decisionID);
        subscribe(new DecisionChange(this));
    }

    public static Decision from(DecisionID decisionID, List<DomainEvent> events){
        Decision decision = new Decision(decisionID);
        events.forEach(event -> decision.applyEvent(event));
        return decision;
    }

    public void changeTitle(Title newTitle){
        appendChange(new TitleChanged(newTitle.value())).apply();
    }
    public void changePages(Pages newPages){
        appendChange(new PagesChanged(newPages.value())).apply();
    }

    public void createCategory(CategoryID categoryID, Type type){
        appendChange(new CategoryAssigned(categoryID.value(), type.value())).apply();
    }

    public void changeTypeFromCategory(CategoryID categoryID, Type newType){
        appendChange(new TypeChangedFromCategory(categoryID.value(), newType.value())).apply();
    }

    public void createJudge(JudgeID judgeID, Name name){
        appendChange(new JudgeAssigned(judgeID.value(), name.value())).apply();
    }

    public void changeNameFromJudge(JudgeID judgeID, Name newName){
        appendChange(new NameChangedFromJudge(judgeID.value(), newName.value())).apply();
    }



}
