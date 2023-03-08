package main.java.org.example.court.domain.action;

import main.java.org.example.court.domain.action.values.LawyerID;
import main.java.org.example.court.domain.action.values.PartID;
import main.java.org.example.court.domain.commonValues.Name;
import main.java.org.example.court.domain.commonValues.Nit;
import main.java.org.example.court.domain.commonValues.Type;
import main.java.org.example.court.generic.Entity;

public class Part extends Entity<PartID> {
    private Type type;
    private Name name;
    private Nit nit;

    private Lawyer lawyer;
    public Part(PartID partID, Type type, Name name, Nit nit) {
        super(partID);
        this.type = type;
        this.name = name;
        this.nit = nit;
    }
    public void changeName(Name name){ this.name = name; }
    public void addLawyer(Lawyer lawyer){
        this.lawyer = lawyer;
    }

    public void changeLawyer(Lawyer lawyer){
        this.lawyer = lawyer;
    }
    public Name name(){
        return name;
    }
    public Nit nit(){ return nit;}
    public Lawyer lawyer(){ return lawyer;}
}
