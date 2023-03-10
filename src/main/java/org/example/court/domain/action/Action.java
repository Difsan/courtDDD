package main.java.org.example.court.domain.action;

import main.java.org.example.court.domain.action.events.*;
import main.java.org.example.court.domain.action.values.ActionID;
import main.java.org.example.court.domain.action.values.LawyerID;
import main.java.org.example.court.domain.action.values.PartID;
import main.java.org.example.court.domain.action.values.ProfessionalCard;
import main.java.org.example.court.domain.commonValues.*;
import main.java.org.example.court.domain.decision.events.TitleChanged;
import main.java.org.example.court.generic.AggregateRoot;
import main.java.org.example.court.generic.DomainEvent;

import java.util.List;

public class Action extends AggregateRoot<ActionID> {

    protected Title title;
    protected CreateDate createDate;
    protected Part part;

    public Action(ActionID actionID, Title title) {
        super(actionID);
        subscribe(new ActionChange(this));
        appendChange(new ActionPresented(title.value())).apply();
    }

    private Action(ActionID actionID) {
        super(actionID);
        subscribe(new ActionChange(this));
    }

    public static Action from(ActionID actionID, List<DomainEvent> events) {
        Action action = new Action(actionID);
        events.forEach(event -> action.applyEvent(event));
        return action;
    }

    public void changeTitle(Title newTitle) {
        appendChange(new TitleChanged(newTitle.value())).apply();
    }

    public void assignPart(PartID partID, Type type, Name name, Nit nit) {
        appendChange(new PartAssigned(partID.value(), type.value(),
                name.value(), nit.value())).apply();
    }

    public void changeNamePart(PartID partID, Name newName) {
        appendChange(new NameChangedFromPart(partID.value(), newName.value())).apply();
    }

    public void assignLawyer(LawyerID lawyerID, Name name, Nit nit,
                             Phone phone, Email email, ProfessionalCard professionalCard) {
        appendChange(new LawyerAssigned(lawyerID.value(), name.value(),
                nit.value(), phone.value(), email.value(), professionalCard.value())).apply();
    }

    public void changeNameOfLawyer(LawyerID lawyerID, Name newName) {
        appendChange(new NameChangedFromLawyer(lawyerID.value(), newName.value()));
    }

    public void changePhoneOfLawyer(LawyerID lawyerID, Phone newPhone) {
        appendChange(new PhoneChangedFromLawyer(lawyerID.value(), newPhone.value()));
    }
}
