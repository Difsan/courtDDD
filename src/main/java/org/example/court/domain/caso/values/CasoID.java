package org.example.court.domain.caso.values;


import org.example.court.generic.Identity;

public class CasoID extends Identity {

        private CasoID(String caseID) {
            super(caseID);
        }

        public CasoID() {

        }

        public static CasoID of(String caseID) {
            return new CasoID(caseID);
        }
}
