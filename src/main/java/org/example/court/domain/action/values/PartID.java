package main.java.org.example.court.domain.action.values;


import main.java.org.example.court.generic.Identity;

public class PartID extends Identity {

        public PartID(String partID) {
            super(partID);
        }

        public PartID() {

        }

        public static PartID of(String partID) {
            return new PartID(partID);
        }
}
