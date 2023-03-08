package main.java.org.example.court.domain.cases;

import main.java.org.example.court.domain.action.Action;
import main.java.org.example.court.domain.action.values.ActionID;
import main.java.org.example.court.domain.cases.values.FileID;
import main.java.org.example.court.domain.cases.values.UpdateDate;
import main.java.org.example.court.domain.commonValues.CreateDate;
import main.java.org.example.court.domain.decision.values.DecisionID;
import main.java.org.example.court.generic.Entity;

import java.util.ArrayList;
import java.util.List;

public class File extends Entity<FileID> {
    private CreateDate createDate;
    private List<UpdateDate> updateDates;
    private List<ActionID> actionIDS;
    private List<DecisionID> decisionIDS;
    public File(FileID fileID,CreateDate createDate) {

        super(fileID);
        this.createDate = createDate;
        this.updateDates = new ArrayList<>();
        this.actionIDS = new ArrayList<>();
        this.decisionIDS = new ArrayList<>();
    }

    public void addUpdateDate(UpdateDate updateDate){
        updateDates.add(updateDate);
    }

    public void addActionID(ActionID actionID){
        actionIDS.add(actionID);
    }

    public void addDecisionID(DecisionID decisionID){
        decisionIDS.add(decisionID);
    }

    public CreateDate createDate(){return createDate;}
    public List<UpdateDate> updateDates(){ return  updateDates;}
    public List<ActionID> actionIDS(){return  actionIDS;}
    public List<DecisionID> decisionIDS(){return decisionIDS;}

}
