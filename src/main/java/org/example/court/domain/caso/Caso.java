package org.example.court.domain.caso;

import org.example.court.domain.action.values.ActionID;
import org.example.court.domain.caso.events.*;
import org.example.court.domain.caso.values.*;
import org.example.court.domain.commonValues.Name;
import org.example.court.domain.commonValues.Nit;
import org.example.court.domain.decision.values.DecisionID;
import org.example.court.generic.AggregateRoot;
import org.example.court.generic.DomainEvent;

import java.util.List;

public class Caso extends AggregateRoot<CasoID> {
    protected State state;
    protected File file;
    protected Guarantor guarantor;

    public Caso(CasoID casoID, State state) {
        super(casoID);
        subscribe(new CasoChange(this));
        appendChange(new CasoOpened(state.value())).apply();
    }
    private Caso(CasoID casoID) {
        super(casoID);
        subscribe(new CasoChange(this));
    }

    public static Caso from(CasoID casoID, List<DomainEvent> events){
        Caso caso = new Caso(casoID);
        events.forEach(event -> caso.applyEvent(event));
        return caso;
    }
    public void changeState(State newState){
        appendChange(new StateChanged(newState.value())).apply();
    }
    public void addFile(FileID fileID){
        appendChange(new FileCreated(fileID.value())).apply();
    }

    public void changeTotalPagesOfFile(FileID fileID, TotalPages newTotalPages){
        appendChange(new TotalPagesChangedFromFile(fileID.value(), newTotalPages.value())).apply();
    }
    public void addActionIdToFile (CasoID casoID, FileID fileID, ActionID actionID){
        appendChange(new ActionIDAdded(casoID.value(), fileID.value(), actionID.value())).apply();
    }
    public void addDecisionIdToFile (CasoID casoID, FileID fileID, DecisionID decisionID){
        appendChange(new DecisionIDAdded(casoID.value(), fileID.value(), decisionID.value())).apply();
    }
    public void assignGuarantor(GuarantorID guarantorID, Name name, Nit nit){
        appendChange(new GuarantorAssigned(guarantorID.value(),name.value(),
                nit.value())).apply();
    }
    public void changeNameOfGuarantor(GuarantorID guarantorID, Name newName){
        appendChange(new NameChangedFromGuarantor(guarantorID.value(), newName.value())).apply();
    }
}
