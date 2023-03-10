package org.example.court.domain.caso;

import org.example.court.domain.action.values.ActionID;
import org.example.court.domain.caso.events.*;
import org.example.court.domain.caso.values.FileID;
import org.example.court.domain.caso.values.GuarantorID;
import org.example.court.domain.caso.values.State;
import org.example.court.domain.caso.values.TotalPages;
import org.example.court.domain.commonValues.Name;
import org.example.court.domain.commonValues.Nit;
import org.example.court.domain.decision.values.DecisionID;
import org.example.court.generic.EventChange;

public class CasoChange extends EventChange {
    public CasoChange(Caso caso){
        apply((CasoOpened event)-> caso.state = new State(event.getState()));

        apply((StateChanged event)-> caso.state = new State(event.getNewState()));

        apply((FileCreated event)-> caso.file = new File(FileID.of(event.getFileID())));

        apply((TotalPagesChangedFromFile event) -> caso.file.ChangeTotalPages(
                new TotalPages(caso.file.totalPages().value() + event.getNewTotalPages())
        ));

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
