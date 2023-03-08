package main.java.org.example.court.domain.decision.command;

import main.java.org.example.court.generic.Command;

public class AssingJudgeCommand extends Command {

    private String decisionID;
    private String judgeID;
    private String name;

    public AssingJudgeCommand(String decisionID, String judgeID, String name) {
        this.decisionID = decisionID;
        this.judgeID = judgeID;
        this.name = name;
    }

    public String getDecisionID() {
        return decisionID;
    }

    public void setDecisionID(String decisionID) {
        this.decisionID = decisionID;
    }

    public String getJudgeID() {
        return judgeID;
    }

    public void setJudgeID(String judgeID) {
        this.judgeID = judgeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
