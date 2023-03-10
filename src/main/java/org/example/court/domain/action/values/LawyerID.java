package org.example.court.domain.action.values;


import org.example.court.generic.Identity;

public class LawyerID extends Identity {
    private LawyerID(String lawyerID) {
            super(lawyerID);
        }

        public LawyerID() {

        }

        public static LawyerID of(String lawyerID) {
            return new LawyerID(lawyerID);
        }
}
