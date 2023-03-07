package main.java.org.example.court.domain.action.values;


import main.java.org.example.court.generic.Identity;

public class ActionID extends Identity {

        public ActionID(String actionID) {
            super(actionID);
        }

        public ActionID() {

        }

        public static ActionID of(String actionID) {
            return new ActionID(actionID);
        }
}