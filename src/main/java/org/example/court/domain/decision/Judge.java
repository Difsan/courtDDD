package main.java.org.example.court.domain.decision;

import main.java.org.example.court.domain.commonValues.Name;
import main.java.org.example.court.domain.commonValues.Type;
import main.java.org.example.court.domain.decision.values.CategoryID;
import main.java.org.example.court.domain.decision.values.JudgeID;
import main.java.org.example.court.generic.Entity;

public class Judge extends Entity<JudgeID> {

    private Name name;

    public Judge(JudgeID judgeID, Name name) {
        super(judgeID);
        this.name = name;
    }

    public void changeName(Name name){ this.name = name; }

    public Name name(){
        return name;
    }

}
