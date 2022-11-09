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
    private boolean locked = false;
    private boolean needsRightDirection = false;
    private String roomDescription = "";
    private String lockedDescription = "";
    private String[] neighbor;
    private String lockedRequirement = "";



    private HashMap<String, Items> itemHash = new HashMap<>();
    private HashMap<String, Puzzle> puzzleHash = new HashMap<>();
    private HashMap<String, Monster> monsterHash = new HashMap<>();
    private HashMap<String, NPC> npcHash = new HashMap<>();


    public Rooms(String id, String roomName, boolean locked, String roomDescription, String lockedDescription, String[] neighbor,
                 String lockedRequirement, HashMap<String, Items> items, HashMap<String, Puzzle> puzzles,HashMap<String, Monster> monsters, HashMap<String, NPC> NPC) {

        this.roomID = id;
        this.roomName = roomName;
        this.locked = locked;
        this.needsRightDirection = needsRightDirection;
        this.roomDescription = roomDescription;
        this.lockedDescription = lockedDescription;
        this.neighbor = neighbor;
        this.lockedRequirement = lockedRequirement;


        linkItems(items);
        linkPuzzles(puzzles);
        linkMonsters(monsters);
        linkNPC(NPC);
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
    public String getLockedDescription() {
        return lockedDescription;
    }
    public boolean getLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String[] getNeighbor() {
        return neighbor;
    }

    public String getLockedRequirement() {
        return lockedRequirement;
    }

    public boolean getNeedsRightDirection() {
        return needsRightDirection;
    }

    public void setNeedsRightDirection(boolean needsRightDirection) {
        this.needsRightDirection = needsRightDirection;
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

    public void linkNPC(HashMap<String, NPC> npclink) {
        for (Map.Entry<String, NPC> elt : npclink.entrySet()) {
            if (elt.getValue().getnLocation().equals(roomID)) {
                npcHash.put(elt.getKey(), elt.getValue());
            }
        }
    }

    public void look() {
        System.out.println(roomDescription);

        System.out.println("Items in the room: ");
        if (itemHash.isEmpty()) {
            System.out.println("Nothing found.");
            System.out.println(itemHash);
        } else {
            for (Map.Entry<String, Items> elt : itemHash.entrySet()) {
                System.out.println(elt.getKey() + " [" + itemHash.get(elt.getKey()).getiAmount() + "] " );
            }
        }

    }

    public HashMap<String, Items> getInventory() {
        return itemHash;
    }

    public HashMap<String, Puzzle> getPuzzleHashMap() {
        return puzzleHash;
    }

    public HashMap<String, NPC> getNpcHash() {
        return npcHash;
    }

    public static HashMap<String, Rooms> createRooms(HashMap<String, Items> itemHash , HashMap<String, Puzzle> puzzleHash, HashMap<String, Monster> monsterHash, HashMap<String, NPC> npcHash) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Rooms.txt"));
            String line;
            HashMap<String, Rooms> rHash = new HashMap<>();
            while ((line = reader.readLine()) != null) {

                String id = line;
                String name = reader.readLine();
                boolean  locked = Boolean.parseBoolean(reader.readLine());
                boolean needsRightDirection = Boolean.parseBoolean(reader.readLine());
                String description = reader.readLine();
                String lockedDescription = reader.readLine();
                String[] neighbors = reader.readLine().split(",");
                for (int i = 0; i < neighbors.length; i++) {
                    neighbors[i] = neighbors[i].trim();
                }
                String lockedRequirement = reader.readLine();

                rHash.put(id, new Rooms(id, name, locked, description, lockedDescription, neighbors, lockedRequirement, itemHash, puzzleHash, monsterHash, npcHash));
            }
            return rHash;
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return null;
    }

}