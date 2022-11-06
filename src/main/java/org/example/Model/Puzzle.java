package org.example.Model;

import java.io.Serializable;

public class Puzzle implements Serializable {
    private String puzzleName;
    private String puzzleID;
    private String puzzleLocation;
    private int puzzleAttempts;
    private String puzzleDescription;
    private String puzzleAnswer;

    public Puzzle(String puzzleNameC, String puzzleIDC, String puzzleLocationC, int puzzleAttemptsC, String puzzleDescriptionC, String puzzleAnswerC) {
        puzzleName = puzzleNameC;
        puzzleID = puzzleIDC;
        puzzleLocation = puzzleLocationC;
        puzzleAttempts = puzzleAttemptsC;
        puzzleDescription = puzzleDescriptionC;
        puzzleAnswer = puzzleAnswerC;
    }

    public String getPuzzleName() {
        return puzzleName;
    }

    public String getPuzzleID() {
        return puzzleID;
    }

    public String getPuzzleLocation() {
        return puzzleLocation;
    }

    public int getPuzzleAttempts() {
        return puzzleAttempts;
    }

    public String getPuzzleDescription() {
        return puzzleDescription;
    }

    public String getPuzzleAnswer() {
        return puzzleAnswer;
    }

}
