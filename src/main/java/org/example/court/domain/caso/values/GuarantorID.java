package main.java.org.example.court.domain.caso.values;


import main.java.org.example.court.generic.Identity;

public class GuarantorID extends Identity {

    private  GuarantorID(String guarantorID) {
            super(guarantorID);
        }

        public GuarantorID() {

        }

        public static GuarantorID of(String guarantorID) {
            return new GuarantorID(guarantorID);
        }
}
