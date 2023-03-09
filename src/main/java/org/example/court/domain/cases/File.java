package main.java.org.example.court.domain.cases;

import main.java.org.example.court.domain.action.values.ActionID;
import main.java.org.example.court.domain.cases.values.FileID;
import main.java.org.example.court.domain.commonValues.CreateDate;
import main.java.org.example.court.domain.decision.values.DecisionID;
import main.java.org.example.court.generic.Entity;

import java.util.ArrayList;
import java.util.List;

public class File extends Entity<FileID> {
    private CreateDate createDate;
    private List<ActionID> actionIDS;
    private List<DecisionID> decisionIDS;
    public File(FileID fileID) {

        super(fileID);
        this.createDate = new CreateDate();
        this.actionIDS = new ArrayList<>();
        this.decisionIDS = new ArrayList<>();

    }

    public void addActionID(ActionID actionID){
        actionIDS.add(actionID);
    }

    public void addDecisionID(DecisionID decisionID){
        decisionIDS.add(decisionID);
    }

    public CreateDate createDate(){return createDate;}
    public List<ActionID> actionIDS(){return  actionIDS;}
    public List<DecisionID> decisionIDS(){return decisionIDS;}

}
