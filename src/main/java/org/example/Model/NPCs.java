package org.example.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class NPCs implements Serializable {
    private String hub1Name;
    private String npc1Name;
    private String npc2Name;
    private List<String> QNormalDial;
    private List<String> QSpecDial;
    private List<String> ShopDial;
    private List<String> rNormalDial;

    private HashMap<String, Items> shopInventory = new HashMap<>();

    public NPCs(String npc1Name, List<String> qNormalDial, List<String> qSpecDial, List<String> shopDial) {
        this.npc1Name = npc1Name;
        this.QNormalDial = qNormalDial;
        this.QSpecDial = qSpecDial;
        this.ShopDial = shopDial;
    }

    public NPCs(String npc2Name, List<String> rNormalDial, List<String> shopDial) {
        this.npc2Name = npc2Name;
        this.rNormalDial = rNormalDial;
        this.ShopDial = shopDial;
    }

    public String getHub1Name() {
        return hub1Name;
    }

    public void setHub1Name(String hub1Name) {
        this.hub1Name = hub1Name;
    }

    public String getNpc1Name() {
        return npc1Name;
    }

    public void setNpc1Name(String npc1Name) {
        this.npc1Name = npc1Name;
    }

    public String getNpc2Name() {
        return npc2Name;
    }

    public void setNpc2Name(String npc2Name) {
        this.npc2Name = npc2Name;
    }

    public List<String> getQNormalDial() {
        return QNormalDial;
    }

    public void setQNormalDial(List<String> QNormalDial) {
        this.QNormalDial = QNormalDial;
    }

    public List<String> getQSpecDial() {
        return QSpecDial;
    }

    public void setQSpecDial(List<String> QSpecDial) {
        this.QSpecDial = QSpecDial;
    }

    public List<String> getShopDial() {
        return ShopDial;
    }

    public void setShopDial(List<String> shopDial) {
        this.ShopDial = shopDial;
    }

    public List<String> getrNormalDial() {
        return rNormalDial;
    }

    public void setrNormalDial(List<String> rNormalDial) {
        this.rNormalDial = rNormalDial;
    }

    @Override
    public String toString() {
        return " NPC Name 1: " + npc1Name + '\n' +
                " NPC Name 2: " + npc2Name + '\n' +
                "Quintella Normal Dial: " + QNormalDial +'\n' +
                "Quintella Special Dial: " +QSpecDial +'\n' +
                "Shop Dial: " + ShopDial +'\n';
    }

}

