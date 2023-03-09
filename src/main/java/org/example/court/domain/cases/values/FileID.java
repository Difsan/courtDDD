package main.java.org.example.court.domain.cases.values;


import main.java.org.example.court.generic.Identity;

public class FileID extends Identity {

    private  FileID(String fileID) {
            super(fileID);
        }

        public FileID() {

        }

        public static FileID of(String fileID) {
            return new FileID(fileID);
        }
}
