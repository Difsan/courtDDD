package main.java.org.example.court.domain.decision.command;

import main.java.org.example.court.generic.Command;

public class ChangeNameFromJudgeCommand extends Command {

    private String decisionID;
    private String judgeID;
    private String newName;

    public ChangeNameFromJudgeCommand(String decisionID, String judgeID, String newName) {
        this.decisionID = decisionID;
        this.judgeID = judgeID;
        this.newName = newName;
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

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
