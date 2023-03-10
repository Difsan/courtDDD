package org.example.court.domain.action.values;


import org.example.court.generic.Identity;

public class ActionID extends Identity {

    private  ActionID(String actionID) {
            super(actionID);
        }

        public ActionID() {

        }

        public static ActionID of(String actionID) {
            return new ActionID(actionID);
        }
}
