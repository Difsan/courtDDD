package main.java.org.example.court.domain.action;


import main.java.org.example.court.domain.action.events.*;
import main.java.org.example.court.domain.action.values.LawyerID;
import main.java.org.example.court.domain.action.values.PartID;
import main.java.org.example.court.domain.action.values.ProfessionalCard;
import main.java.org.example.court.domain.commonValues.*;
import main.java.org.example.court.domain.decision.events.TitleChanged;
import main.java.org.example.court.generic.EventChange;

public class ActionChange extends EventChange {

    public ActionChange(Action action){
        apply((ActionPresented event) ->{
            action.createDate = new CreateDate(event.getCreateDate());
            action.title = new Title(event.getTitle());
        });
        apply((TitleChanged event) -> action.title = new Title(event.getNewTitle()));
        apply((PartAssigned event)-> action.part = new Part(PartID.of(event.getPartID()),new Type(event.getType()),
                new Name(event.getName()), new Nit(event.getNit())));
        apply((NameChangedFromPart event)-> action.part.changeName(new Name(event.getNewName())));
        apply((LawyerAssigned event) -> action.part.addLawyer(new Lawyer(LawyerID.of(event.getLawyerID()),new Name(event.getName()),
                new Nit(event.getNit()), new ProfessionalCard(event.getProfessionalCard()))));
        apply((NameChangedFromLawyer event)-> action.part.lawyer().changeName(new Name(event.getNewName())));
    }
}
