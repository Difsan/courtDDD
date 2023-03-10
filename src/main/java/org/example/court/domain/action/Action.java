package org.example.court.domain.action;

import org.example.court.domain.action.events.*;
import org.example.court.domain.action.values.*;
import org.example.court.domain.commonValues.*;
import org.example.court.domain.decision.events.TitleChanged;
import org.example.court.generic.AggregateRoot;
import org.example.court.generic.DomainEvent;

import java.util.List;

public class Action extends AggregateRoot<ActionID> {

    protected Title title;

    protected Pages pages;
    protected CreateDate createDate;
    protected Part part;

    public Action(ActionID actionID, Title title, Pages pages) {
        super(actionID);
        subscribe(new ActionChange(this));
        appendChange(new ActionPresented(title.value(), pages.value())).apply();
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

    public void changePages(Pages newPages) {
        appendChange(new PagesChanged(newPages.value())).apply();
    }

    public void assignPart(PartID partID, Type type, Name name, Nit nit,
                           Phone phone, Email email, Address address) {
        appendChange(new PartAssigned(partID.value(), type.value(),
                name.value(), nit.value(), phone.value(), email.value(), address.value())).apply();
    }

    public void changeNamePart(PartID partID, Name newName) {
        appendChange(new NameChangedFromPart(partID.value(), newName.value())).apply();
    }
    public void changeEmailPart(PartID partID, Email newEmail) {
        appendChange(new EmailChangedFromPart(partID.value(), newEmail.value())).apply();
    }
    public void changeAddressPart(PartID partID, Address address) {
        appendChange(new AddressChangedFromPart(partID.value(), address.value())).apply();
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

    public void changeEmailOfLawyer(LawyerID lawyerID, Email newEmail) {
        appendChange(new EmailChangedFromLawyer(lawyerID.value(), newEmail.value()));
    }
}
