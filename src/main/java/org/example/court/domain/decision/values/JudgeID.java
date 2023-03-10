package org.example.court.domain.decision.values;


import org.example.court.generic.Identity;

public class JudgeID extends Identity {

        private JudgeID(String judgeID) {
            super(judgeID);
        }

        public JudgeID() {

        }

        public static JudgeID of(String judgeID) {
            return new JudgeID(judgeID);
        }
}
