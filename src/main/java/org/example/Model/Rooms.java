package org.example.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Rooms {

    private String roomID;
    private String roomName = "";
    private String roomDescription = "";
    private String[] neighbor;

    private HashMap<String, Items> itemHash = new HashMap<>();
    private HashMap<String, Puzzle> puzzleHash = new HashMap<>();
    private HashMap<String, Monster> monsterHash = new HashMap<>();


    public Rooms(String id, String roomName, String roomDescription, String[] neighbor, HashMap<String, Items> items, HashMap<String, Puzzle> puzzles,HashMap<String, Monster> monsters) {
        this.roomID = id;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.neighbor = neighbor;

        linkItems(items);
        linkPuzzles(puzzles);
        linkMonsters(monsters);
    }

    public String getRoomID() {
        return roomID;
    }

    public String getRoomName() {
        return roomName;
    }


    public String getRoomDescription() {
        return roomDescription;
    }


    public String[] getNeighbor() {
        return neighbor;
    }


    public void linkItems(HashMap<String, Items> items) {
        for (Map.Entry<String, Items> elt : items.entrySet()) {

            if (elt.getValue().getiLocation().contains(roomID)) {
                itemHash.put(elt.getKey(), elt.getValue());
            }
        }
    }

    public void linkPuzzles(HashMap<String, Puzzle> puzzlelink) {
        for (Map.Entry<String, Puzzle> elt : puzzlelink.entrySet()) {
            if (elt.getValue().getPuzzleLocation().equals(roomID)) {
                puzzleHash.put(elt.getKey(), elt.getValue());
            }
        }
    }

    public void linkMonsters(HashMap<String, Monster> monsterlink) {
        for (Map.Entry<String, Monster> elt : monsterlink.entrySet()) {
            if (elt.getValue().getSpawnLocation().equals(roomID)) {
                monsterHash.put(elt.getKey(), elt.getValue());
            }
        }
    }

    public void look() {
        System.out.println(roomDescription);

        if (itemHash.isEmpty()) {
            System.out.println("Nothing found.");
            System.out.println(itemHash);
        } else {
            for (Map.Entry<String, Items> elt : itemHash.entrySet()) {
                System.out.print(elt.getKey() + ", ");
            }
            System.out.println(" are the current item(s) in the room.");
        }

    }

    public HashMap<String, Items> getInventory() {
        return itemHash;
    }

    public HashMap<String, Puzzle> getPuzzleHashMap() {
        return puzzleHash;
    }

    public static HashMap<String, Rooms> createRooms(HashMap<String, Items> itemHash , HashMap<String, Puzzle> puzzleHash,HashMap<String, Monster> monsterHash) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Rooms.txt"));
            String line;
            HashMap<String, Rooms> rHash = new HashMap<>();
            while ((line = reader.readLine()) != null) {

                String id = line;
                String name = reader.readLine();
                String description = reader.readLine();
                String[] neighbors = reader.readLine().split(",");
                for (int i = 0; i < neighbors.length; i++) {
                    neighbors[i] = neighbors[i].trim();
                }
                rHash.put(id, new Rooms(id, name, description, neighbors, itemHash, puzzleHash, monsterHash));
            }
            return rHash;
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return null;
    }
}