package main.java.org.example.court.domain.action;


import main.java.org.example.court.domain.action.events.*;
import main.java.org.example.court.domain.action.values.Address;
import main.java.org.example.court.domain.action.values.LawyerID;
import main.java.org.example.court.domain.action.values.PartID;
import main.java.org.example.court.domain.action.values.ProfessionalCard;
import main.java.org.example.court.domain.commonValues.*;
import main.java.org.example.court.domain.decision.events.TitleChanged;
import main.java.org.example.court.generic.EventChange;

public class ActionChange extends EventChange {

    public ActionChange(Action action){
        apply((ActionPresented event) ->{
            action.createDate = new CreateDate();
            action.title = new Title(event.getTitle());
        });

        apply((TitleChanged event) -> action.title = new Title(event.getNewTitle()));

        apply((PartAssigned event)-> action.part = new Part(PartID.of(event.getPartID()),new Type(event.getType()),
                new Name(event.getName()), new Nit(event.getNit()),new Phone(event.getPhone()),
                new Email(event.getEmail()),new Address(event.getAddress())));

        apply((NameChangedFromPart event)-> action.part.changeName(new Name(event.getNewName())));

        apply((EmailChangedFromPart event)-> action.part.changeEmail(new Email(event.getNewEmail())));

        apply((AddressChangedFromPart event) -> action.part.changeAddress(new Address(event.getNewAddress())));

        apply((LawyerAssigned event) -> action.part.addLawyer(new Lawyer(LawyerID.of(event.getLawyerID()),
                new Name(event.getName()),new Nit(event.getNit()), new Phone(event.getPhone()),
                new Email(event.getEmail()), new ProfessionalCard(event.getProfessionalCard()))));

        apply((NameChangedFromLawyer event)-> action.part.lawyer().changeName(new Name(event.getNewName())));

        apply((PhoneChangedFromLawyer event) -> action.part.lawyer().changePhone(new Phone(event.getNewPhone())));

        apply((EmailChangedFromLawyer event) -> action.part.lawyer().changeEmail(new Email(event.getNewEmail())));
    }
}
