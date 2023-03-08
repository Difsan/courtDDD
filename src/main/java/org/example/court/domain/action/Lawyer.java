package main.java.org.example.court.domain.action;

import main.java.org.example.court.domain.action.values.LawyerID;
import main.java.org.example.court.domain.action.values.ProfessionalCard;
import main.java.org.example.court.domain.commonValues.Name;
import main.java.org.example.court.domain.commonValues.Nit;
import main.java.org.example.court.generic.Entity;

public class Lawyer extends Entity<LawyerID> {

    private Name name;
    private Nit nit;
    private ProfessionalCard professionalCard;

    public Lawyer(LawyerID lawyerID, Name name, Nit nit, ProfessionalCard professionalCard) {
        super(lawyerID);
        this.name = name;
        this.nit = nit;
        this.professionalCard = professionalCard;
    }
    public void changeName(Name name){ this.name = name; }
    public Name name(){
        return name;
    }
    public Nit nit(){ return nit;}
    public ProfessionalCard professionalCard(){return  professionalCard;}
}
