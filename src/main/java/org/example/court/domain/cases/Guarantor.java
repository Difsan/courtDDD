package main.java.org.example.court.domain.cases;

import main.java.org.example.court.domain.cases.values.GuarantorID;
import main.java.org.example.court.domain.commonValues.Name;
import main.java.org.example.court.domain.commonValues.Nit;
import main.java.org.example.court.generic.Entity;

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
