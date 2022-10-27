package org.example.Model;

import java.util.ArrayList;

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
}
	
	
