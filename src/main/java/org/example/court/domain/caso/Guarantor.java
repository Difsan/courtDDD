package org.example.court.domain.caso;

import org.example.court.domain.caso.values.GuarantorID;
import org.example.court.domain.commonValues.Name;
import org.example.court.domain.commonValues.Nit;
import org.example.court.generic.Entity;

public class Guarantor extends Entity<GuarantorID> {

    private Name name;
    private Nit nit;

    public Guarantor(GuarantorID guarantorID, Name name, Nit nit) {
        super(guarantorID);
        this.name = name;
        this.nit = nit;
    }

    public void changeName(Name name){ this.name = name; }
    public Name name(){
        return name;
    }
    public Nit nit(){ return nit;}
}
