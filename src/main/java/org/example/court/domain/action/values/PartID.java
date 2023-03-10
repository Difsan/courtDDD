package org.example.court.domain.action.values;


import org.example.court.generic.Identity;

public class PartID extends Identity {

        private PartID(String partID) {
            super(partID);
        }

        public PartID() {

        }

        public static PartID of(String partID) {
            return new PartID(partID);
        }
}
