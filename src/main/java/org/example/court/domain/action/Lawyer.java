package org.example.court.domain.action;

import org.example.court.domain.action.values.LawyerID;
import org.example.court.domain.action.values.ProfessionalCard;
import org.example.court.domain.commonValues.Email;
import org.example.court.domain.commonValues.Name;
import org.example.court.domain.commonValues.Nit;
import org.example.court.domain.commonValues.Phone;
import org.example.court.generic.Entity;

public class Lawyer extends Entity<LawyerID> {

    private Name name;
    private Nit nit;

    private Phone phone;

    private Email email;
    private ProfessionalCard professionalCard;

    public Lawyer(LawyerID lawyerID, Name name, Nit nit,
                  Phone phone, Email email, ProfessionalCard professionalCard) {
        super(lawyerID);
        this.name = name;
        this.nit = nit;
        this.phone = phone;
        this.email = email;
        this.professionalCard = professionalCard;
    }
    public void changeName(Name name){ this.name = name; }
    public void changePhone(Phone phone){ this.phone = phone; }

    public void changeEmail(Email email){ this.email = email; }
    public Name name(){
        return name;
    }
    public Nit nit(){ return nit;}

    public Phone phone(){return phone;}
    public Email email(){return email;}
    public ProfessionalCard professionalCard(){return  professionalCard;}
}
