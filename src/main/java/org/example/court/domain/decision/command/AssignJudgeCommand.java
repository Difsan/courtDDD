package org.example.court.domain.decision.command;

import org.example.court.generic.Command;

public class AssignJudgeCommand extends Command {

    private String decisionID;
    private String judgeID;
    private String name;

    public AssignJudgeCommand(String decisionID, String judgeID, String name) {
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
