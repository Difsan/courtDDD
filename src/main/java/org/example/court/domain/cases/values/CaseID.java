package main.java.org.example.court.domain.cases.values;


import main.java.org.example.court.generic.Identity;

public class CaseID extends Identity {

        private CaseID(String caseID) {
            super(caseID);
        }

        public CaseID() {

        }

        public static CaseID of(String caseID) {
            return new CaseID(caseID);
        }
}
