package org.example.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Player {
    private static String location;
    private HashMap<String, Items> playerInventory = new HashMap<>();
    private HashMap<String, Items> playerEquipment = new HashMap<>();
    private int plyhealth;
    private static int plyattack;
    private int plyMHealth;
    private int plyMoney;
    public static String playerName;

    public Player(Integer health, Integer attack, Integer mHealth, Integer plyMoney) {
        location = "SW_0";
        this.plyhealth = health;
        this.plyattack = attack;
        this.plyMHealth = mHealth;
        this.plyMoney = plyMoney;
    }
    

    public String getLocation() {
        return location;
    }

    public static void setLocation(String location) {
        Player.location = location;
    }
    public int getPlyhealth() {
        return plyhealth;
    }
    public void setPlyhealth(int plyhealth) {
        this.plyhealth = plyhealth;
    }
    public int getPlyattack() {
        return plyattack;
    }
    public void setPlyattack(int plyattack) {
        this.plyattack = plyattack;
    }

    public int getPlyMHealth() {
        return plyMHealth;
    }

    public void setPlyMHealth(int plyMHealth) {
        this.plyMHealth = plyMHealth;
    }

    public void setplyMoney(int plyMoney) {
        this.plyMoney = plyMoney;
    }
    

    public static void EnterPlayerName(){
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Please enter player name: ");
        playerName = sc.nextLine().toUpperCase();
    }

    public int getPlyMoney() {
        return plyMoney;
    }

    public void setPlyMoney(int plyMoney) {
        this.plyMoney = plyMoney;
    }

    public void getInventory() {
        if (playerInventory.isEmpty()) {
            System.out.println("No items currently in inventory.");
        } else {
            for (Map.Entry<String, Items> elt : playerInventory.entrySet()) {
                System.out.println(elt.getKey() + " [" + playerInventory.get(elt.getKey()).getInvAmount() + "]");
            }
        }
    }

    public void move(String direction, HashMap<String, Rooms> roomsHashMap) {
        direction = direction.toLowerCase();
        Rooms current = roomsHashMap.get(location);

        String[] temp = current.getNeighbor();
        if (direction.equals("u")) {
            direction = "ladder-up";
        } else if (direction.equals("d")) {
            direction = "ladder-down";
        } else if (direction.equals("n")) {
            direction = "north";
        } else if (direction.equals("s")) {
            direction = "south";
        } else if (direction.equals("e")) {
            direction = "east";
        } else if (direction.equals("w")) {
            direction = "west";
        }

        switch (direction) {
            case "ladder-up":
                if (!temp[0].equals("-")) {
                    location = (temp[0]);
                } else {
                    System.out.println("No Exit, Please try a different way.\n");
                }
                break;
            case "ladder-down":
                if (!temp[1].equals("-")) {
                    location = (temp[1]);
                } else {
                    System.out.println("No Exit, Please try a different way.\n");
                }
                break;
            case "north":
                if (!temp[2].equals("-")) {
                    location = (temp[2]);
                } else {
                    System.out.println("No Exit, Please try a different way.\n");
                }
                break;
            case "south":
                if (!temp[3].equals("-")) {
                    location = (temp[3]);
                } else {
                    System.out.println("No Exit, Please try a different way.\n");
                }
                break;
            case "east":
                if (!temp[4].equals("-")) {
                    location = (temp[4]);
                } else {
                    System.out.println("No Exit, Please try a different way.\n");
                }
                break;
            case "west":
                if (!temp[5].equals("-")) {
                    location = (temp[5]);
                } else {
                    System.out.println("No Exit, Please try a different way.\n");
                }
                break;
            default:
                System.out.println("No Exit, Please try a different way.\n");
                break;

        }

    }

    public void add(String item, HashMap<String, Rooms> rooms) {
        Rooms current = rooms.get(location);

        Items temp = null;

        if (current.getInventory().containsKey(item)) {
            temp = current.getInventory().get(item);

            current.getInventory().remove(item);
            playerInventory.put(temp.getiName(), temp);
            System.out.println(item + " added to inventory");
        } else {
            System.out.println("Sorry, " + item + " is not found here.");
        }
    }

    public void remove(String item) {
        playerInventory.remove(item);
    }
    public void drop(String item, HashMap<String, Rooms> rooms) {
        Items temp = null;

        if (playerInventory.containsKey(item)) {
            temp = playerInventory.get(item);
            playerInventory.remove(item);

            Rooms current = rooms.get(location);

            current.getInventory().put(temp.getiName(), temp);
            System.out.println(item + " dropped");
        } else {
            System.out.println("You don't have " + item + " yet.");
        }
    }

    public void dropOne(String item, HashMap<String, Rooms> rooms) {
        Items temp = null;

        if (playerInventory.containsKey(item)) {
            temp = playerInventory.get(item);

            Rooms current = rooms.get(location);

            current.getInventory().put(temp.getiName(), temp);
            System.out.println(item + " dropped");
        } else {
            System.out.println("You don't have " + item + " yet.");
        }
    }
    public void puzzleReward(Items str){
        playerInventory.put(str.getiName(), str);
    }

    public void explore(String item) {
        System.out.println(playerInventory.get(item).getiDescription());
    }

    public String itemType(String item) {
        return playerInventory.get(item).getiType();
    }

    public int itemCost(String item) {
        return playerInventory.get(item).getiCost();
    }

    public int itemAmount(String item) {
        return playerInventory.get(item).getiAmount();
    }

    public int invAmount(String item) {
        return playerInventory.get(item).getInvAmount();
    }

    public void explore(HashMap<String, Rooms> rooms) {
        rooms.get(location).look();
    }

    public int maxAmount(String item) {
        return playerInventory.get(item).getmHealth();
    }

    public HashMap<String, Items> getPlayerInventory() {
        return playerInventory;
    }

    public void use(String itemName, Map<String,Items> playerInventory) {
        Items temp = null;

        if (playerInventory.containsKey(itemName)) {
            temp = playerInventory.get(itemName);
            playerInventory.remove(itemName);
        } else {
            System.out.println("You don't have " + itemName + " yet.");
        }

    }
    public void addItem(String item, Items value) {
        playerInventory.put(item, value);
    }

    public void equip(String item, Map<String, Items> inventory) {
        Items temp = null;

        if (inventory.containsKey(item)) {
            temp = inventory.get(item);
            inventory.remove(item);
            playerEquipment.put(temp.getiName(), temp);
            setPlyattack(getPlyattack() + playerEquipment.get(item).getiAttack());

            System.out.println("Attack damage increased " + getPlyattack());
            System.out.println(item + " was successfully equipped");
            System.out.println('\n');
        }else {
            System.out.println("Sorry, " + item + " is unable to be equipped" + '\n');
        }
    }
    public void wear(String item, Map<String, Items> inventory) {
        Items temp = null;

        if (inventory.containsKey(item)) {
            temp = inventory.get(item);
            inventory.remove(item);
            playerEquipment.put(temp.getiName(), temp);
            setPlyMHealth(getPlyMHealth() + playerEquipment.get(item).getmHealth());

            System.out.println("Max Health increased " + getPlyMHealth());
            System.out.println(item + " was successfully equipped");
            System.out.println('\n');
        }else {
            System.out.println("Sorry, " + item + " is unable to be worn" + '\n');
        }
    }

    public void heal(String item, Map<String, Items> inventory_) {

        if (inventory_.containsKey(item) && !inventory_.get(item).getiHeal().equals(0)) {
            if (plyMHealth < (getPlyhealth() + inventory_.get(item).getiHeal())) {
                System.out.println("Player was healed by " + (plyMHealth - getPlyhealth()));
                System.out.println("Player was  healed");
                setPlyhealth(plyMHealth);
            } else {
                System.out.println("Health of the player was healed by " + inventory_.get(item).getiHeal());
                System.out.println("Player was  healed");
                setPlyhealth(getPlyhealth() + inventory_.get(item).getiHeal());
            }
            inventory_.remove(item);
        }
    }
    
    public static int attack (int monsterHP) {
    return monsterHP - plyattack;
    
    }
//    public void unequip(String item, Map<String,Items> equipment){
//        Items temp = null;
//
//
//        if (equipment.containsKey(item)) {
//            temp = equipment.get(item);
//            equipment.remove(item);
//
//            getPlayerInventory().put(temp.getiName(), temp);
//            setPlyattack(0);
//            System.out.println("Attack damage of the player decreased to " + getPlyattack());
//            System.out.println(item + " was successfully unequipped");
//            System.out.println('\n');
//        } else {
//            System.out.println("Sorry, " + item + " is not in your equipment." + '\n');
//        }
//    }
}
