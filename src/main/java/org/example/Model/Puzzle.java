package org.example.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Puzzle
{
    private String puzzleName;
    private String puzzleID;
    private String puzzleLocation;
    private int puzzleAttempts;
    private String puzzleDescription;
    private ArrayList<String> puzzleAnswer;
    private String puzzleReward;

    public Puzzle(String puzzleName, String puzzleID, String puzzleLocation, int puzzleAttempts, String puzzleDescription, ArrayList<String> puzzleAnswer, String puzzleReward) {
        this.puzzleName = puzzleName;
        this.puzzleID = puzzleID;
        this.puzzleLocation = puzzleLocation;
        this.puzzleAttempts = puzzleAttempts;
        this.puzzleDescription = puzzleDescription;
        this.puzzleAnswer = puzzleAnswer;
        this.puzzleReward = puzzleReward;
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

    public String getPuzzleReward() {
        return puzzleReward;
    }

    public void setPuzzleReward(String reward) {
        this.puzzleReward = reward;
    }

    public static HashMap<String, Puzzle> createPuzzles()
    {
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
                String pReward = reader.readLine();

                pHash.put(pLocation, new Puzzle(pName, pID, pLocation, pAttempts, pDescription, pAnswers, pReward));
            }
            return pHash;
        } catch (IOException e)
        {
            System.out.println("File not found");
        }
        return null;
    }
}
