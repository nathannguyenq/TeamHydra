package org.example.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Player implements Serializable {
    private static String location;
    private HashMap<String, Items> playerInventory = new HashMap<>();

    public Player() {
        location = "SW_0";
    }

    public void move(String direction, HashMap<String, Rooms> roomsHashMap) {
        direction = direction.toLowerCase();
        Rooms current = roomsHashMap.get(location);

        String[] temp = current.getNeighbor();
        if (direction.equals("n")) {
            direction = "north";
        } else if (direction.equals("s")) {
            direction = "south";
        } else if (direction.equals("e")) {
            direction = "east";
        } else if (direction.equals("w")) {
            direction = "west";
        }

        switch (direction) {
            case "north":
                if (!temp[0].equals("-")) {
                    location = (temp[0]);
                } else {
                    System.out.println("No Exit, Please try a different way.\n");
                }
                break;
            case "south":
                if (!temp[1].equals("-")) {
                    location = (temp[1]);
                } else {
                    System.out.println("No Exit, Please try a different way.\n");
                }
                break;
            case "east":
                if (!temp[2].equals("-")) {
                    location = (temp[2]);
                } else {
                    System.out.println("No Exit, Please try a different way.\n");
                }
                break;
            case "west":
                if (!temp[3].equals("-")) {
                    location = (temp[3]);
                } else {
                    System.out.println("No Exit, Please try a different way.\n");
                }
                break;
            default:
                System.out.println("No Exit, Please try a different way.\n");
                break;

        }

    }

    public void take(String item, HashMap<String, Rooms> rooms) {
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

    public void equip(String item, HashMap<String, Rooms> rooms){
        Rooms current = rooms.get(location);

        Items temp = null;

        if (current.getInventory().containsKey(item)) {
            temp = current.getInventory().get(item);

            current.getInventory().remove(item);
            playerInventory.put(temp.getiName(), temp);
            System.out.println(item + " is now equipped");
        } else {
            System.out.println("Sorry, " + item + " is not to equip.");
        }
    }

    public void use(String item, HashMap<String, Rooms> rooms){
        Rooms current = rooms.get(location);

        Items temp = null;

        if (current.getInventory().containsKey(item)) {
            temp = current.getInventory().get(item);

            current.getInventory().remove(item);
            playerInventory.put(temp.getiName(), temp);
            System.out.println(item + " is now being used");
        } else {
            System.out.println("Sorry, " + item + " is not for use here.");
        }
    }

    public void consume(String item, HashMap<String, Rooms> rooms){
        Rooms current = rooms.get(location);

        Items temp = null;

        if (current.getInventory().containsKey(item)) {
            temp = current.getInventory().get(item);

            current.getInventory().remove(item);
            playerInventory.put(temp.getiName(), temp);
            System.out.println(item + " is now consumed");
        } else {
            System.out.println("Sorry, " + item + " is not for use here.");
        }
    }

    public void wear(String item, HashMap<String, Rooms> rooms){
        Rooms current = rooms.get(location);

        Items temp = null;

        if (current.getInventory().containsKey(item)) {
            temp = current.getInventory().get(item);

            current.getInventory().remove(item);
            playerInventory.put(temp.getiName(), temp);
            System.out.println(item + " is now worn");
        } else {
            System.out.println("Sorry, " + item + " is not for use here.");
        }
    }

    public void remove(String item, HashMap<String, Rooms> rooms) {
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

    public void getInventory() {
        if (playerInventory.isEmpty()) {
            System.out.println("No items currently in inventory.");
        } else {
            for (Map.Entry<String, Items> elt : playerInventory.entrySet()) {
                System.out.println(elt.getKey());
            }
        }
    }

    public String getLocation() {
        return location;
    }

    public void observe(String item) {
        System.out.println(playerInventory.get(item).getiDescription());
    }

    public void observe(HashMap<String, Rooms> rooms) {
        rooms.get(location).look();
    }

}
