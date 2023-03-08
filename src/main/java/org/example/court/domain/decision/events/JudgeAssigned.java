package main.java.org.example.court.domain.decision.events;

import main.java.org.example.court.generic.DomainEvent;

public class JudgeAssigned extends DomainEvent {

    private final String judgeID;
    private final String name;

    public JudgeAssigned(String judgeID, String name) {
        super("main.java.org.example.court.domain.decision.events.JudgeCreated");
        this.judgeID = judgeID;
        this.name = name;
    }

    public String getJudgeID() {
        return judgeID;
    }

    public String getName() {
        return name;
    }
}
