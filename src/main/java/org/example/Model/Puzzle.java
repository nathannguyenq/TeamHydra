package org.example.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Puzzle {
    private String puzzleName;
    private String puzzleID;
    private String puzzleLocation;
    private int puzzleAttempts;
    private String puzzleDescription;
    private ArrayList<String> puzzleAnswer;

    public Puzzle(String puzzleNameC, String puzzleIDC, String puzzleLocationC, int puzzleAttemptsC, String puzzleDescriptionC, ArrayList<String> puzzleAnswerC) {
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

    public ArrayList<String> getPuzzleAnswer() {
        return puzzleAnswer;
    }

    public static HashMap<String, Puzzle> createPuzzles() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("puzzle.txt"));
            String line;
            HashMap<String, Puzzle> pHash = new HashMap<>();
            while ((line = reader.readLine()) != null) {

                ArrayList<String> pAnswers = new ArrayList<>();

                String pName = line;
                String pID = reader.readLine();
                String pLocation = reader.readLine();
                int pAttempts = Integer.parseInt(reader.readLine());
                String pDescription = reader.readLine();
                String[] puzzle = reader.readLine().split(",");
                for (int i = 0; i < puzzle.length; i++) {
                    puzzle[i] = puzzle[i].trim();
                    String tem = puzzle[i];
                    pAnswers.add(tem);
                }
                pHash.put(pLocation, new Puzzle(pName, pID, pLocation, pAttempts, pDescription, pAnswers));
            }
            return pHash;
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return null;
    }
}
