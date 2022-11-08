package org.example;

<<<<<<< HEAD
import org.example.Controller.Controller;
import org.example.View.View;
=======
import org.example.Model.*;
>>>>>>> f901ffc4fd3ad59ccd2a3f31cb88723bd9e25ebd

import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.html.StyleSheet;
import java.io.*;
<<<<<<< HEAD
import java.util.Scanner;

public class Main implements Serializable {
    static Controller game;
    public static void main(String[] args) {
        View view = new View();
        Controller game = new Controller();
        String str = "";
        view.intro(str);
        boolean running = true;

        while (running)
            game.runGame();
=======
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        HashMap<String, Items> itemsHashMap = new HashMap<>();
        HashMap<String, Puzzle> puzzleHashMap = new HashMap<>();
        HashMap<String, Rooms> roomsHashMap = new HashMap<>();
        HashMap<String, NPCs> dialogueHashMap = new HashMap<>();
        ArrayList<String> pStorage = new ArrayList<>();

        ArrayList<String> flag = new ArrayList<>();
        flag.add("1");
        int tempAttempt;

        Player player = new Player();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("puzzle.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String pName = line;
                String pID = reader.readLine();
                String pLocation = reader.readLine();
                int pAttempts = Integer.parseInt(reader.readLine());
                String pDescription = reader.readLine();
                String pAnswer = reader.readLine();

                puzzleHashMap.put(pLocation, new Puzzle(pName, pID, pLocation, pAttempts, pDescription, pAnswer));
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Items.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String iName = line;
                String iDescription = reader.readLine();
                String iLocation = reader.readLine().trim();

                itemsHashMap.put(iName, new Items(iName, iDescription, iLocation));
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Rooms.txt"));
            String line;
            while ((line = reader.readLine()) != null) {

                String id = line;
                String name = reader.readLine();
                String description = reader.readLine();
                String[] neighbors = reader.readLine().split(",");
                for (int i = 0; i < neighbors.length; i++) {
                    neighbors[i] = neighbors[i].trim();
                }
                roomsHashMap.put(name, new Rooms(id, name, description, neighbors, itemsHashMap, puzzleHashMap));
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }

        try {
            BufferedReader bf = new BufferedReader(new FileReader("Dialogue.txt"));
            String hub1Name = bf.readLine();
            String npc1Name = bf.readLine();
            List<String> QNormalDial = new ArrayList<String>();
            String line = bf.readLine();
            while (line != null && !line.contains("END")) {
                QNormalDial.add(line);
                line = bf.readLine();
            }
            List<String> QSpecDial = new ArrayList<String>();
            String line1 = bf.readLine();
            while (line1 != null && !line1.contains("END1")) {
                QSpecDial.add(line1);
                line1 = bf.readLine();
            }
            List<String> ShopDial = new ArrayList<String>();
            String line2 = bf.readLine();
            while (line2 != null && !line2.contains("END2")){
                ShopDial.add(line2);
                line2 = bf.readLine();
            }
            String hub2Name = bf.readLine();
            String npc2Name = bf.readLine();
            List<String> RNormalDial = new ArrayList<String>();
            String line3 = bf.readLine();
            while (line3 != null && !line3.contains("END3")){
                RNormalDial.add(line3);
                line3 = bf.readLine();
            }

            dialogueHashMap.put(hub2Name, new NPCs(npc2Name,RNormalDial,ShopDial));
            dialogueHashMap.put(hub1Name, new NPCs(npc1Name, QNormalDial, QSpecDial, ShopDial));


            System.out.println(dialogueHashMap.entrySet());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        boolean running = true;

        System.out.println("\t" + "\t" + "#############################");
        System.out.println("\t" + "\t" + "#### Welcome to the game ####");
        System.out.println("\t" + "\t" + "#############################");
        System.out.println("\n");
        System.out.println("----------------------------------------");
        System.out.println("Which way do you want to go? (N,E,S,W)" + " or you can quit (q)");

        GAME:
        while (running) {
            String input = scanner.nextLine();
            input = input.toLowerCase();
            String[] command = input.split(" ");

            if (command[0].equals("q") || command[0].equals("quit"))
            {
                break;
            }


            else if (command[0].equalsIgnoreCase("observe"))
            {
                if (command.length >= 2) {
                    String temp = "";

                    for (int i = 1; i < command.length; i++) {
                        temp = temp + command[i] + "";
                    }
                    temp = temp.trim();

                    System.out.println(roomsHashMap.get(player.getLocation()).getRoomName());

                    player.observe(temp);
                } else if (command.length == 1) {
                    player.observe(roomsHashMap);
                } else {
                    System.out.println(command[1] + " is an Invalid Command");
                }


            } else if (command[0].equalsIgnoreCase("take")) {
                if (command.length >= 2) {
                    String temp = "";
                    for (int i = 1; i < command.length; i++) {
                        temp = temp + command[i] + " ";
                    }
                    temp = temp.trim();

                    player.take(temp, roomsHashMap);
                } else {
                    System.out.println("!# Invalid Command #!");
                    System.out.println("");
                    System.out.println(roomsHashMap.get(player.getLocation()).getRoomDescription());
                }

            } else if (command[0].equalsIgnoreCase("equip")) {
                if (command.length >= 2) {
                    String temp = "";
                    for (int i = 1; i < command.length; i++) {
                        temp = temp + command[i] + " ";
                    }
                    temp = temp.trim();

                    player.equip(temp, roomsHashMap);
                } else {
                    System.out.println("!# Invalid Command #!");
                    System.out.println("");
                    System.out.println(roomsHashMap.get(player.getLocation()).getRoomDescription());
                }

            } else if (command[0].equalsIgnoreCase("use")) {
                if (command.length >= 2) {
                    String temp = "";
                    for (int i = 1; i < command.length; i++) {
                        temp = temp + command[i] + " ";
                    }
                    temp = temp.trim();

                    player.use(temp, roomsHashMap);
                } else {
                    System.out.println("!# Invalid Command #!");
                    System.out.println("");
                    System.out.println(roomsHashMap.get(player.getLocation()).getRoomDescription());
                }

            } else if (command[0].equalsIgnoreCase("wear")) {
                if (command.length >= 2) {
                    String temp = "";
                    for (int i = 1; i < command.length; i++) {
                        temp = temp + command[i] + " ";
                    }
                    temp = temp.trim();

                    player.wear(temp, roomsHashMap);
                } else {
                    System.out.println("!# Invalid Command #!");
                    System.out.println("");
                    System.out.println(roomsHashMap.get(player.getLocation()).getRoomDescription());
                }

            } else if (command[0].equalsIgnoreCase("consume")) {
                if (command.length >= 2) {
                    String temp = "";
                    for (int i = 1; i < command.length; i++) {
                        temp = temp + command[i] + " ";
                    }
                    temp = temp.trim();

                    player.consume(temp, roomsHashMap);
                } else {
                    System.out.println("!# Invalid Command #!");
                    System.out.println("");
                    System.out.println(roomsHashMap.get(player.getLocation()).getRoomDescription());
                }


//            } else if (command[0].equalsIgnoreCase("inspect")) {
//                if (command.length >= 2) {
//                    String temp = "";
//
//                    for (int i = 1; i < command.length; i++) {
//                        temp = temp + command[i] + "";
//                    }
//                    temp = temp.trim();
//
//                    player.observe(temp);
//                } else {
//                    System.out.println(command[1] + " is an Invalid Command");
//                }

            } else if (command[0].equalsIgnoreCase("remove")) {
                if (command.length >= 2) {
                    String temp = "";
                    for (int i = 1; i < command.length; i++) {
                        temp = temp + command[i] + " ";
                    }
                    temp = temp.trim();
                    player.remove(temp, roomsHashMap);


                } else {
                    System.out.println(command[1] + " is an Invalid Command");
                }

            } else if (command.length == 1) {
                if (command[0].equalsIgnoreCase("inventory")) {
                    player.getInventory();
                }
            }
            if (command[0].equalsIgnoreCase("north") || command[0].equalsIgnoreCase("south") || command[0].equalsIgnoreCase("east")
                    || command[0].equalsIgnoreCase("west") || command[0].equalsIgnoreCase("n") || command[0].equalsIgnoreCase("s") || command[0].equalsIgnoreCase("e") || command[0].equalsIgnoreCase("w"))
            {
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
                System.out.print(roomsHashMap.get(player.getLocation()).getRoomName());
                if (!flag.contains(roomsHashMap.get(player.getLocation()).getRoomID())) {
                    System.out.println(" never been here" + '\n');

                    flag.add((roomsHashMap.get(player.getLocation()).getRoomID()));
                } else {
                    System.out.println(" \"You have been here before\"" + '\n');

                }
                System.out.println(roomsHashMap.get(player.getLocation()).getRoomDescription());
            }
>>>>>>> f901ffc4fd3ad59ccd2a3f31cb88723bd9e25ebd
        }

}