package org.example.court.domain.caso.values;


import org.example.court.generic.Identity;

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
