package org.example.Controller;

import org.example.Model.*;
import org.example.View.View;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.example.Main.loadGame;
import static org.example.Main.saveGame;

public class Controller implements Serializable{
    Scanner scanner = new Scanner(System.in);
    View view = new View();
    Player player = new Player(100, 50);
    String str = "";
    ArrayList<String> pStorage = new ArrayList<>();
    ArrayList<String> flag = new ArrayList<>();
    int tempAttempt;

    HashMap<String, NPCs> npcHashMap = NPCs.createNPCs();
    HashMap<String, Items> itemsHashMap = Items.createItems();
    HashMap<String, Puzzle> puzzleHashMap = Puzzle.createPuzzles();

    HashMap<String, Monster> monsterHashMap = new HashMap<>();
    HashMap<String, Rooms> roomsHashMap = Rooms.createRooms(itemsHashMap, puzzleHashMap, monsterHashMap, npcHashMap);
    String currRoom = "";
    String prevRoom = "";


    public static void main(String[] args) {
    }

    public void runGame() {
        String input = scanner.nextLine();
        input = input.toLowerCase();
        String[] command = input.split(" ");

        if (command[0].equals("q") || command[0].equals("quit")) {

        } else if (command[0].equals("explore")) {
            if (command.length == 1) {
                player.explore(roomsHashMap);
            } else {
                view.invalid(str);
            }
        } else if (command[0].equals("pickup")) {
            if (command.length >= 2) {
                String temp = "";
                for (int i = 1; i < command.length; i++) {
                    temp = temp + command[i] + " ";
                }
                temp = temp.trim();

                player.add(temp, roomsHashMap);
            } else {
                view.invalid(str);
            }
        } else if (command[0].equals("inspect")) {
            if (command.length >= 2) {
                String temp = "";

                for (int i = 1; i < command.length; i++) {
                    temp = temp + command[i] + "";
                }
                temp = temp.trim();

                player.explore(temp);
            } else {
                view.invalid(str);
            }
        } else if (command[0].equals("drop")) {
            if (command.length >= 2) {
                String temp = "";
                for (int i = 1; i < command.length; i++) {
                    temp = temp + command[i] + " ";
                }
                temp = temp.trim();
                player.drop(temp, roomsHashMap);


            } else {
                view.invalid(str);
            }
        } else if (command.length == 1) {
            if (command[0].equals("inventory")) {
                player.getInventory();
            }
        }

     else if (command[0].equals("talk")) {
        if (command.length >= 2) {
            String temp = "";
            for (int i = 1; i < command.length; i++) {
                temp = temp + command[i] + " ";
            }
            temp = temp.trim();
            player.talk(temp,npcHashMap);


        } else {
            view.invalid(str);
        }
    }

        String currentLocation = player.getLocation();
        if (command[0].equals("ladder-up") || command[0].equals("ladder-down") || command[0].equals("north") || command[0].equals("south") || command[0].equals("east") || command[0].equals("west") || command[0].equals("u") || command[0].equals("d") || command[0].equals("n") || command[0].equals("s") || command[0].equals("e") || command[0].equals("w")) {
            player.move(command[0], roomsHashMap);
            if (roomsHashMap.get(player.getLocation()).getPuzzleHashMap().containsKey(player.getLocation())) {
                tempAttempt = roomsHashMap.get(player.getLocation()).getPuzzleHashMap().get(player.getLocation()).getPuzzleAttempts();
                while (tempAttempt > 0 && !pStorage.contains(roomsHashMap.get(player.getLocation()).getRoomID())) {
                    System.out.println(roomsHashMap.get(player.getLocation()).getPuzzleHashMap().get(player.getLocation()).getPuzzleDescription());
                    String Solution = roomsHashMap.get(player.getLocation()).getPuzzleHashMap().get(player.getLocation()).getPuzzleAnswer();
                    String s = scanner.nextLine();
                    if (!s.equals(Solution)) {
                        tempAttempt--;
                        System.out.println("Wrong Answer Attempt(s) left: " + tempAttempt + '\n');

                    } else {
                        System.out.println("Puzzle Completed" + '\n');
                        pStorage.add(roomsHashMap.get(player.getLocation()).getRoomID());

                    }

                }
            }
            if (roomsHashMap.get(player.getLocation()).getLocked() == false) {
                prevRoom = currRoom;
                currRoom = player.getLocation();
            }
            if (roomsHashMap.get(player.getLocation()).getLocked() == true) {
                String str2 = roomsHashMap.get(player.getLocation()).getLockedRequirement();
                while (roomsHashMap.get(player.getLocation()).getLocked() == true) {
                    System.out.println(roomsHashMap.get(player.getLocation()).getLockedDescription());
                    view.leave(str);
                    view.useItem(str);
                    System.out.print(str2);
                    System.out.println();
                    String lockInput = scanner.nextLine();
                    lockInput = lockInput.toLowerCase();
                    String[] command2 = lockInput.split(" ");
                    if (lockInput.equals("leave")) {
                        player.setLocation(currRoom);
                    } else if (command2[0].equals("use") && player.getPlayerInventory().containsKey(roomsHashMap.get(player.getLocation()).getLockedRequirement())) {
                        if (command2.length >= 2) {
                            String temp = "";
                            for (int i = 1; i < command2.length; i++) {
                                temp = temp + command2[i] + " ";
                            }
                            temp = temp.trim();
                            player.use(temp, player.getPlayerInventory());
                            roomsHashMap.get(player.getLocation()).setLocked(false);


                        } else {
                            view.invalid(str);
                        }

                    }
                }
            }

            System.out.println(roomsHashMap.get(player.getLocation()).getRoomName());
            if (!flag.contains(roomsHashMap.get(player.getLocation()).getRoomID())) {
                view.notVisited(str);

                flag.add((roomsHashMap.get(player.getLocation()).getRoomID()));
            } else {
                view.visited(str);

            }
            System.out.println(roomsHashMap.get(player.getLocation()).getRoomDescription());

        }

    }

}

