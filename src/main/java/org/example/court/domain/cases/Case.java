package main.java.org.example.court.domain.cases;

import main.java.org.example.court.domain.action.Action;
import main.java.org.example.court.domain.action.ActionChange;
import main.java.org.example.court.domain.action.events.ActionPresented;
import main.java.org.example.court.domain.action.values.ActionID;
import main.java.org.example.court.domain.cases.events.*;
import main.java.org.example.court.domain.cases.values.*;
import main.java.org.example.court.domain.commonValues.Name;
import main.java.org.example.court.domain.commonValues.Nit;
import main.java.org.example.court.domain.decision.values.DecisionID;
import main.java.org.example.court.generic.AggregateRoot;
import main.java.org.example.court.generic.DomainEvent;

import java.util.List;

public class Case extends AggregateRoot<CaseID> {
    protected State state;
    protected File file;
    protected Guarantor guarantor;

    public Case(CaseID caseID, State state) {
        super(caseID);
        subscribe(new CaseChange(this));
        appendChange(new CaseOpened(state.value())).apply();
    }
    public Case(CaseID caseID) {
        super(caseID);
        subscribe(new CaseChange(this));
    }

    public static Case from(CaseID caseID, List<DomainEvent> events){
        Case caso = new Case(caseID);
        events.forEach(event -> caso.applyEvent(event));
        return caso;
    }
    public void changeState(State newState){
        appendChange(new StateChanged(newState.value())).apply();
    }
    public void addFile(FileID fileID){
        appendChange(new FileCreated(fileID.value())).apply();
    }
    public void addUpdateDataToFile(FileID fileID, UpdateDate updateDate){
        appendChange(new UpdateDateAdded(fileID.value(), updateDate.value())).apply();
    }
    public void addActionIdToFile (FileID fileID, ActionID actionID){
        appendChange(new ActionIDAdded(fileID.value(), actionID.value())).apply();
    }
    public void addDecisionIdToFile (FileID fileID, DecisionID decisionID){
        appendChange(new DecisionIDAdded(fileID.value(), decisionID.value())).apply();
    }
    public void assignGuarantor(GuarantorID guarantorID, Name name, Nit nit){
        appendChange(new GuarantorAssigned(guarantorID.value(),name.value(),
                nit.value())).apply();
    }
    public void changeNameOfGuarantor(GuarantorID guarantorID, Name newName){
        appendChange(new NameChangedFromGuarantor(guarantorID.value(), newName.value())).apply();
    }
}
