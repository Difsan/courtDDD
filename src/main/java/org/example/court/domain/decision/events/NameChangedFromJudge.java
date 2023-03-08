package main.java.org.example.court.domain.decision.events;

import main.java.org.example.court.generic.DomainEvent;

public class NameChangedFromJudge extends DomainEvent {

    private final String judgeID;
    private final String newName;

    public NameChangedFromJudge(String judgeID, String newName) {
        super("main.java.org.example.court.domain.decision.events.NameChangedFromJudge");
        this.judgeID = judgeID;
        this.newName = newName;
    }

    public String getJudgeID() {
        return judgeID;
    }

    public String getNewName() {
        return newName;
    }
}
