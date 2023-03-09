package main.java.org.example.court.domain.decision;

import main.java.org.example.court.domain.commonValues.*;
import main.java.org.example.court.domain.decision.events.*;
import main.java.org.example.court.domain.decision.values.CategoryID;
import main.java.org.example.court.domain.decision.values.JudgeID;
import main.java.org.example.court.generic.EventChange;

public class DecisionChange extends EventChange {

    public DecisionChange(Decision decision){
        apply((DecisionCreated event) ->{
            decision.createDate = new CreateDate();
            decision.title = new Title(event.getTitle());
            decision.pages = new Pages(event.getPages());
        });
        apply((TitleChanged event) -> decision.title = new Title(event.getNewTitle()));
        apply((PagesChanged event)-> decision.pages = new Pages(event.getNewPages()));
        apply((CategoryAssigned event)-> decision.category = new Category(CategoryID.of(event.getCategoryID()),
                new Type(event.getType())));
        apply((TypeChangedFromCategory event) -> decision.category.changeType(new Type(event.getNewType())));
        apply((JudgeAssigned event)-> decision.judge = new Judge(JudgeID.of(event.getJudgeID()),
                new Name(event.getName())));
        apply((NameChangedFromJudge event)-> decision.judge.changeName(new Name(event.getNewName())));
    }
}
