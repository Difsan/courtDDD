package main.java.org.example.court.domain.decision.values;


import main.java.org.example.court.generic.Identity;

public class DecisionID extends Identity {

        public DecisionID(String decisionID) {
            super(decisionID);
        }

        public DecisionID() {

        }

        public static DecisionID of(String decisionID) {
            return new DecisionID(decisionID);
        }
}
