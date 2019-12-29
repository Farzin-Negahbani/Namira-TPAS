package Classes;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Farzin.negahbani on 5/26/2016.
 */

public class Tuple {
        private final Integer groupNumber;
        private  Integer teamNumber;
        private final SimpleStringProperty teamBinaryLoc;
        private final SimpleStringProperty teamName;

        public Tuple(String teamName, String teamBinaryLoc,Integer groupNumber, Integer teamNumber) {
            this.groupNumber   = groupNumber;
            this.teamBinaryLoc = new SimpleStringProperty(teamBinaryLoc);
            this.teamName = new SimpleStringProperty(teamName);
            this.teamNumber = teamNumber;
        }


        public Integer getTeamNumber() {
            return teamNumber;
        }
        public String getTeamBinaryLoc() {
            return teamBinaryLoc.get();
        }

        public void setTeamNumber(Integer teamNumber) {
            this.teamNumber = teamNumber;
    }
        public void setTeamBinaryLoc(String fName) {
            teamBinaryLoc.set(fName);
        }

        public String getTeamName() {
            return teamName.get();
        }

        public void setTeamName(String fName) {
            teamName.set(fName);
        }


    }
