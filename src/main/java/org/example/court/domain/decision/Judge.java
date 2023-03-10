package org.example.court.domain.decision;

import org.example.court.domain.commonValues.Name;
import org.example.court.domain.decision.values.JudgeID;
import org.example.court.generic.Entity;

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
