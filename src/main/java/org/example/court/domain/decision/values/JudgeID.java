package main.java.org.example.court.domain.decision.values;


import main.java.org.example.court.generic.Identity;

public class JudgeID extends Identity {

        public JudgeID(String judgeID) {
            super(judgeID);
        }

        public JudgeID() {

        }

        public static JudgeID of(String judgeID) {
            return new JudgeID(judgeID);
        }
}
