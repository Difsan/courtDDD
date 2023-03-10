package main.java.org.example.court.domain.caso;

import main.java.org.example.court.domain.action.values.ActionID;
import main.java.org.example.court.domain.caso.values.FileID;
import main.java.org.example.court.domain.caso.values.TotalPages;
import main.java.org.example.court.domain.commonValues.CreateDate;
import main.java.org.example.court.domain.decision.values.DecisionID;
import main.java.org.example.court.generic.Entity;

import java.util.ArrayList;
import java.util.List;

public class File extends Entity<FileID> {
    private CreateDate createDate;

    private TotalPages totalPages;
    private List<ActionID> actionIDS;
    private List<DecisionID> decisionIDS;
    public File(FileID fileID) {

        super(fileID);
        this.createDate = new CreateDate();
        this.totalPages = new TotalPages(0);
        this.actionIDS = new ArrayList<>();
        this.decisionIDS = new ArrayList<>();

    }

    public void addActionID(ActionID actionID){
        actionIDS.add(actionID);
    }
    public void addDecisionID(DecisionID decisionID){
        decisionIDS.add(decisionID);
    }

    public void ChangeTotalPages(TotalPages newTotalPages){
        this.totalPages = newTotalPages;
    }

    public CreateDate createDate(){return createDate;}

    public TotalPages totalPages(){ return totalPages;}
    public List<ActionID> actionIDS(){return  actionIDS;}
    public List<DecisionID> decisionIDS(){return decisionIDS;}

}
