package org.example.court.domain.decision.values;


import org.example.court.generic.Identity;

public class CategoryID extends Identity {

        private CategoryID(String categoryID ) {
            super(categoryID );
        }

        public CategoryID() {

        }

        public static CategoryID of(String categoryID) {
            return new CategoryID(categoryID);
        }
}
