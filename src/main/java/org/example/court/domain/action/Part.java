package main.java.org.example.court.domain.action;

import main.java.org.example.court.domain.action.values.Address;
import main.java.org.example.court.domain.action.values.LawyerID;
import main.java.org.example.court.domain.action.values.PartID;
import main.java.org.example.court.domain.commonValues.*;
import main.java.org.example.court.generic.Entity;

public class Part extends Entity<PartID> {
    private Type type;
    private Name name;
    private Nit nit;

    private Phone phone;

    private Email email;

    private Address address;
    private Lawyer lawyer;

    public Part(PartID id, Type type, Name name, Nit nit,
                Phone phone, Email email, Address address) {
        super(id);
        this.type = type;
        this.name = name;
        this.nit = nit;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public void changeName(Name name){ this.name = name; }
    public void changeEmail(Email email){
        this.email = email;
    }
    public void changeAddress(Address address){
        this.address = address;
    }
    public void addLawyer(Lawyer lawyer){
        this.lawyer = lawyer;
    }


    public Name name(){
        return name;
    }
    public Nit nit(){ return nit;}

    public Phone phone(){return phone;}

    public Email email(){return email;}

    public Address address(){return address;}

    public Lawyer lawyer(){ return lawyer;}
}
