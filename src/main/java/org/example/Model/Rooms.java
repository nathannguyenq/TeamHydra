package org.example.Model;

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
=======
import java.io.Serializable;
import java.util.Arrays;
>>>>>>> f901ffc4fd3ad59ccd2a3f31cb88723bd9e25ebd
import java.util.HashMap;
import java.util.Map;

public class Rooms implements Serializable {

    private String roomID;
    private String roomName = "";
    private boolean locked = false;
    private String roomDescription = "";
    private String lockedDescription = "";
    private String[] neighbor;
<<<<<<< HEAD
    private String lockedRequirement = "";
=======

    private HashMap<String, Items> roomItems = new HashMap<>();
    private HashMap<String, Puzzle> puzzleHash = new HashMap<>();
>>>>>>> f901ffc4fd3ad59ccd2a3f31cb88723bd9e25ebd


    private HashMap<String, Items> itemHash = new HashMap<>();
    private HashMap<String, Puzzle> puzzleHash = new HashMap<>();
    private HashMap<String, Monster> monsterHash = new HashMap<>();
    private HashMap<String, NPCs> NPCHash = new HashMap<>();


    public Rooms(String id, String roomName, boolean locked, String roomDescription, String lockedDescription, String[] neighbor, String lockedRequirement, HashMap<String, Items> items, HashMap<String, Puzzle> puzzles,HashMap<String, Monster> monsters, HashMap<String, NPCs> NPC) {
        this.roomID = id;
        this.roomName = roomName;
        this.locked = locked;
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

    public void linkNPC(HashMap<String, NPCs> NPCLink) {
        for (Map.Entry<String, NPCs> elt : NPCLink.entrySet()) {
            if (elt.getValue().getNpcLocation().equals(roomID)) {
                NPCHash.put(elt.getKey(), elt.getValue());
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


    public static HashMap<String, Rooms> createRooms(HashMap<String, Items> itemHash , HashMap<String, Puzzle> puzzleHash,HashMap<String, Monster> monsterHash, HashMap<String, NPCs> NPCHash) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Rooms.txt"));
            String line;
            HashMap<String, Rooms> rHash = new HashMap<>();
            while ((line = reader.readLine()) != null) {

                String id = line;
                String name = reader.readLine();
                boolean  locked = Boolean.parseBoolean(reader.readLine());
                String description = reader.readLine();
                String lockedDescription = reader.readLine();
                String[] neighbors = reader.readLine().split(",");
                for (int i = 0; i < neighbors.length; i++) {
                    neighbors[i] = neighbors[i].trim();
                }
                String lockedRequirement = reader.readLine();
                rHash.put(id, new Rooms(id, name, locked, description, lockedDescription, neighbors, lockedRequirement, itemHash, puzzleHash, monsterHash, NPCHash));
            }
            return rHash;
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return null;
    }

}