package main.java.org.example.court.domain.cases;

import main.java.org.example.court.domain.action.values.ActionID;
import main.java.org.example.court.domain.cases.events.*;
import main.java.org.example.court.domain.cases.values.FileID;
import main.java.org.example.court.domain.cases.values.GuarantorID;
import main.java.org.example.court.domain.cases.values.State;
import main.java.org.example.court.domain.cases.values.UpdateDate;
import main.java.org.example.court.domain.commonValues.CreateDate;
import main.java.org.example.court.domain.commonValues.Name;
import main.java.org.example.court.domain.commonValues.Nit;
import main.java.org.example.court.domain.decision.values.DecisionID;
import main.java.org.example.court.generic.EventChange;

import java.time.LocalDate;
import java.util.ArrayList;

public class CaseChange extends EventChange {
    public CaseChange(Case caso){
        apply((CaseOpened event)-> caso.state = new State(event.getState()));
        apply((StateChanged event)-> caso.state = new State(event.getNewState()));
        apply((FileCreated event)->{
            caso.file = new File(FileID.of(event.getFileID()),
                    new CreateDate(event.getCreateDate()));
            caso.file.addUpdateDate(new UpdateDate(event.getCreateDate()));
        });
        apply((UpdateDateAdded event) ->
                caso.file.addUpdateDate(new UpdateDate(event.getUpdateDate())));
        apply((ActionIDAdded event)->{
            caso.file.addActionID(ActionID.of(event.getActionID()));
            caso.file.addUpdateDate(new UpdateDate(LocalDate.now()));
        });
        apply((DecisionIDAdded event)->{
            caso.file.addDecisionID(DecisionID.of(event.getDecisionID()));
            caso.file.addUpdateDate(new UpdateDate(LocalDate.now()));
        });
        apply((GuarantorAssigned event)-> caso.guarantor =
                new Guarantor(GuarantorID.of(event.getGuarantorID()),
                new Name(event.getName()), new Nit(event.getNit())));
        apply((NameChangedFromGuarantor event)->
                caso.guarantor.changeName(new Name(event.getNewName())));
    }

}
