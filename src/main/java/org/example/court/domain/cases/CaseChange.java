package main.java.org.example.court.domain.cases;

import main.java.org.example.court.domain.action.values.ActionID;
import main.java.org.example.court.domain.cases.events.*;
import main.java.org.example.court.domain.cases.values.FileID;
import main.java.org.example.court.domain.cases.values.GuarantorID;
import main.java.org.example.court.domain.cases.values.State;
import main.java.org.example.court.domain.commonValues.Name;
import main.java.org.example.court.domain.commonValues.Nit;
import main.java.org.example.court.domain.decision.values.DecisionID;
import main.java.org.example.court.generic.EventChange;

public class CaseChange extends EventChange {
    public CaseChange(Caso caso){
        apply((CaseOpened event)-> caso.state = new State(event.getState()));
        apply((StateChanged event)-> caso.state = new State(event.getNewState()));
        apply((FileCreated event)->{
            caso.file = new File(FileID.of(event.getFileID()));
        });
        apply((ActionIDAdded event)->{
            caso.file.addActionID(ActionID.of(event.getActionID()));
        });
        apply((DecisionIDAdded event)->{
            caso.file.addDecisionID(DecisionID.of(event.getDecisionID()));
        });
        apply((GuarantorAssigned event)-> caso.guarantor =
                new Guarantor(GuarantorID.of(event.getGuarantorID()),
                new Name(event.getName()), new Nit(event.getNit())));
        apply((NameChangedFromGuarantor event)->
                caso.guarantor.changeName(new Name(event.getNewName())));
    }

}
