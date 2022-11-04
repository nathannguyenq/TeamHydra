package org.example.Model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Rooms {

    private String roomID;
    private String roomName = "";
    private String roomDescription = "";
    private String[] neighbor;

    private HashMap<String, Items> roomItems = new HashMap<>();
    private HashMap<String, Puzzle> puzzleHash = new HashMap<String, Puzzle>();


    public Rooms(String id, String roomName, String roomDescription, String[] neighbor, HashMap<String, Items> items, HashMap<String, Puzzle> puzzles) {
        this.roomID = id;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.neighbor = neighbor;

        linkItems(items);
        linkPuzzles(puzzles);
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
                roomItems.put(elt.getKey(), elt.getValue());
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

    public void look() {
        System.out.println(roomDescription);

        if (roomItems.isEmpty()) {
            System.out.println("Nothing found.");
            System.out.println(roomItems);
        } else {
            for (Map.Entry<String, Items> elt : roomItems.entrySet()) {
                System.out.print(elt.getKey() + ", ");
            }
            System.out.println(" are the current item(s) in the room.");
        }

    }

    public HashMap<String, Items> getInventory() {
        return roomItems;
    }

    public HashMap<String, Puzzle> getPuzzleHashMap() {
        return puzzleHash;
    }

}
