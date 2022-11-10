package org.example.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class NPC {
    private String nName;
    private String nID;
    private String nLocation;
    private ArrayList<String> nDialogue;
    private ArrayList<String> specDialogue;

    private ArrayList<String> npcInventory;

    public NPC(String nName, String nID, String nLocation, ArrayList<String> nDialogue, ArrayList<String> specDialogue, ArrayList<String> npcInventory) {
        this.nName = nName;
        this.nID = nID;
        this.nLocation = nLocation;
        this.nDialogue = nDialogue;
        this.specDialogue = specDialogue;
        this.npcInventory = npcInventory;
    }

    public String getnName() {
        return nName;
    }

    public String getnID() {
        return nID;
    }

    public String getnLocation() {
        return nLocation;
    }

    public ArrayList<String> getnDialogue() {
        return nDialogue;
    }

    public ArrayList<String> getSpecDialogue() {
        return specDialogue;
    }

    public ArrayList<String> getNpcInventory() {
        return npcInventory;
    }

    public static HashMap<String, NPC> createNPC() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("npc.txt"));
            String line;
            HashMap<String, NPC> npcHash = new HashMap<>();
            while ((line = reader.readLine()) != null) {

                ArrayList<String> normDialogue = new ArrayList<>();
                ArrayList<String> specDialogue = new ArrayList<>();
                ArrayList<String> npcInv = new ArrayList<>();

                String nName = line.toLowerCase();
                String nID = reader.readLine();
                String nLocation = reader.readLine();
                String[] nDialogue = reader.readLine().split("~");
                for (int i = 0; i < nDialogue.length; i++) {
                    nDialogue[i] = nDialogue[i].trim();
                    String tem = nDialogue[i];
                    normDialogue.add(tem);
                }
                String[] sDialogue = reader.readLine().split("~");
                for (int i = 0; i < sDialogue.length; i++) {
                    sDialogue[i] = sDialogue[i].trim();
                    String temp = sDialogue[i];
                    specDialogue.add(temp);
                }
                String[] Inv = reader.readLine().split("~");
                for (int i = 0; i < Inv.length; i++) {
                    Inv[i] = Inv[i].trim();
                    String tempp = Inv[i];
                    npcInv.add(tempp);
                }
                npcHash.put(nName, new NPC(nName, nID, nLocation, normDialogue, specDialogue, npcInv));
            }
            return npcHash;
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return null;
    }
}
