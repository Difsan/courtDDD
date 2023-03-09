package main.java.org.example.court.domain.cases;

import main.java.org.example.court.domain.action.values.ActionID;
import main.java.org.example.court.domain.cases.events.*;
import main.java.org.example.court.domain.cases.values.*;
import main.java.org.example.court.domain.commonValues.Name;
import main.java.org.example.court.domain.commonValues.Nit;
import main.java.org.example.court.domain.decision.values.DecisionID;
import main.java.org.example.court.generic.AggregateRoot;
import main.java.org.example.court.generic.DomainEvent;

import java.util.List;

public class Caso extends AggregateRoot<CaseID> {
    protected State state;
    protected File file;
    protected Guarantor guarantor;

    public Caso(CaseID caseID, State state) {
        super(caseID);
        subscribe(new CaseChange(this));
        appendChange(new CaseOpened(state.value())).apply();
    }
    private Caso(CaseID caseID) {
        super(caseID);
        subscribe(new CaseChange(this));
    }

    public static Caso from(CaseID caseID, List<DomainEvent> events){
        Caso caso = new Caso(caseID);
        events.forEach(event -> caso.applyEvent(event));
        return caso;
    }
    public void changeState(State newState){
        appendChange(new StateChanged(newState.value())).apply();
    }
    public void addFile(FileID fileID){
        appendChange(new FileCreated(fileID.value())).apply();
    }
    public void addActionIdToFile (CaseID caseID, FileID fileID, ActionID actionID){
        appendChange(new ActionIDAdded(caseID.value(), fileID.value(), actionID.value())).apply();
    }
    public void addDecisionIdToFile (CaseID caseID, FileID fileID, DecisionID decisionID){
        appendChange(new DecisionIDAdded(caseID.value(), fileID.value(), decisionID.value())).apply();
    }
    public void assignGuarantor(GuarantorID guarantorID, Name name, Nit nit){
        appendChange(new GuarantorAssigned(guarantorID.value(),name.value(),
                nit.value())).apply();
    }
    public void changeNameOfGuarantor(GuarantorID guarantorID, Name newName){
        appendChange(new NameChangedFromGuarantor(guarantorID.value(), newName.value())).apply();
    }
}
