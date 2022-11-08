package org.example.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Items {

    private String iID;
    private String iName;
    private String iDescription;
    private String iType;
    private ArrayList<String> iLocation;

    private int iAttack;
    private int iHeal;
    private int mHealth;


    public Items(String iId, String iName, String iDescription,String iType, ArrayList<String> iLocation, int iAttack, int iHeal, int mHealth) {
        this.iID = iId;
        this.iName = iName;
        this.iDescription = iDescription;
        this.iType = iType;
        this.iLocation = iLocation;
        this.iAttack = iAttack;
        this.iHeal = iHeal;
        this.mHealth = mHealth;
    }

    public String getiName() {
        return iName;
    }

    public String getiDescription() {
        return iDescription;
    }

    public ArrayList<String> getiLocation() {
        return iLocation;
    }

    public Integer getiAttack() {
        return iAttack;
    }

    public Integer getiHeal() {
        return iHeal;
    }

    public int getmHealth() {
        return mHealth;
    }

    public String getiType() {
        return iType;
    }

    public void look() {
        System.out.println(iDescription);
    }

    public static HashMap<String, Items> createItems() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Items.txt"));
            String line;
            HashMap<String, Items> iHash = new HashMap<>();
            while ((line = reader.readLine()) != null) {

                ArrayList<String> iLocation = new ArrayList<>();

                String iID = line;
                String iName = reader.readLine();
                String iDescription = reader.readLine();
                String iType = reader.readLine();
                String[] neighbors = reader.readLine().split(",");
                for (int i = 0; i < neighbors.length; i++) {
                    neighbors[i] = neighbors[i].trim();
                    String tem = neighbors[i];
                    iLocation.add(tem);
                }
                int iAttack = Integer.parseInt(reader.readLine());
                int iHeal = Integer.parseInt(reader.readLine());
                int mHealth = Integer.parseInt(reader.readLine());

                iHash.put(iName, new Items(iID,iName, iDescription,iType,iLocation,iAttack,iHeal,mHealth));
            }
            return iHash;
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return null;
    }
}
