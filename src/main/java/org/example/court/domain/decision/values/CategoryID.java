package main.java.org.example.court.domain.decision.values;


import main.java.org.example.court.generic.Identity;

public class CategoryID extends Identity {

        public CategoryID(String categoryID ) {
            super(categoryID );
        }

        public CategoryID() {

        }

        public static CategoryID of(String categoryID) {
            return new CategoryID(categoryID);
        }
}
