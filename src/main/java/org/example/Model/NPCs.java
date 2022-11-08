package org.example.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class NPCs implements Serializable {
    private String name;
    private String npcID;
    private String[] nDialogue;
    private String[] sDialogue;
    private String npcLocation;
    private HashMap<String, NPCs> shopIventory = new HashMap<>();

    public NPCs(String name, String npcID, String[] nDialogue, String[] sDialogue, String npcLocation) {
        this.name = name;
        this.npcID = npcID;
        this.nDialogue = nDialogue;
        this.sDialogue = sDialogue;
        this.npcLocation = "HUB_0";
    }

    public String getName() {
        return name;
    }

    public String getNpcID() {
        return npcID;
    }
    public String getNpcLocation() {
        return npcLocation;
    }

    public String[] getnDialogue() {
        return nDialogue;
    }

    public String[] getsDialogue() {
        return sDialogue;
    }

    public HashMap<String, NPCs> getShopIventory() {
        return shopIventory;
    }

    public static HashMap<String, NPCs> createNPCs() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("npc.txt"));
            String line;
            HashMap<String, NPCs> npcHash = new HashMap<>();
            while ((line = reader.readLine()) != null) {

                ArrayList<String> normalDialogue = new ArrayList<>();
                ArrayList<String> specialDialogue = new ArrayList<>();

                String name = line;
                String npcID = reader.readLine();
                String[] nDialogue = reader.readLine().split("~");
                for (int i =0; i < nDialogue.length; i++){
                    nDialogue[i] = nDialogue[i].trim();
                    String temp = nDialogue[i];
                    normalDialogue.add(temp);
                }
                String[] sDialogue = reader.readLine().split("~");
                for (int i =0; i < sDialogue.length; i++){
                    sDialogue[i] = sDialogue[i].trim();
                    String temp = sDialogue[i];
                    specialDialogue.add(temp);
                }
                String npcLocation = reader.readLine();
                npcHash.put(name, new NPCs(name, npcID, nDialogue, sDialogue, npcLocation));
            }
            return npcHash;
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return null;
    }
}



