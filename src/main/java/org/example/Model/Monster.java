package org.example.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Monster {

    private String name;
    private String mDescription;
    private int HP;
    private ArrayList<String> spawnLocation = new ArrayList<>();
    private String winMessage;
    private String lossMessage;
    private String enterMessage;
    private int goldReward;
    private int AttackDamage;


    public Monster(String name, String mDescription, int hP, ArrayList<String> spawnLocation, String winMessage,
                   String lossMessage, String enterMessage, int goldReward, int attackDamage) {

        this.name = name;
        this.mDescription = mDescription;
        this.HP = hP;
        this.spawnLocation = spawnLocation;
        this.winMessage = winMessage;
        this.lossMessage = lossMessage;
        this.enterMessage = enterMessage;
        this.goldReward = goldReward;
        this.AttackDamage = attackDamage;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", HP=" + HP +
                ", spawnLocation=" + spawnLocation +
                ", winMessage='" + winMessage + '\'' +
                ", lossMessage='" + lossMessage + '\'' +
                ", enterMessage='" + enterMessage + '\'' +
                ", goldReward=" + goldReward +
                ", AttackDamage=" + AttackDamage +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getmDescription() {
        return mDescription;
    }

    public int getHP() {
        return HP;
    }

    public ArrayList<String> getSpawnLocation() {
        return spawnLocation;
    }

    public String getWinMessage() {
        return winMessage;
    }

    public String getLossMessage() {
        return lossMessage;
    }

    public String getEnterMessage() {
        return enterMessage;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public int getAttackDamage() {
        return AttackDamage;
    }

    public static HashMap<String, Items> createMonsters() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Monsters.txt"));
            String line;
            HashMap<String, Monster> mHash = new HashMap<>();
            while ((line = reader.readLine()) != null) {

                ArrayList<String> temp = new ArrayList<>();

                String mName = line;
                String mDescription = reader.readLine();
                int pLocation = Integer.parseInt(reader.readLine());
                String[] neighbors = reader.readLine().split(" ");
                for (int i = 0; i < neighbors.length; i++) {
                    neighbors[i] = neighbors[i].trim();
                    String tem = neighbors[i];
                    temp.add(tem);
                }
                String pDescription = reader.readLine();
                String pAnswer = reader.readLine();
                String pnswer = reader.readLine();
                int pLocion = Integer.parseInt(reader.readLine());
                int pcation = Integer.parseInt(reader.readLine());

                mHash.put(mName, new Monster(mName,mDescription,pLocation,temp,pDescription,pAnswer,pnswer,pLocion,pcation));
//                System.out.print(MonsterHashMap.get(mName).getName());
//                System.out.print(" = ");
//                System.out.println(MonsterHashMap.get(mName).getGoldReward());
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return null;
    }
}
	
	
