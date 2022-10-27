package org.example;

import org.example.Model.Items;
import org.example.Model.Monster;
import org.example.Model.Player;
import org.example.Model.Puzzle;
import org.example.Model.Rooms;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        HashMap<String, Monster> MonsterHashMap = new HashMap<>(); /////////- BAUDEL

        HashMap<String, Items> itemsHashMap = new HashMap<>();
        HashMap<String, Puzzle> puzzleHashMap = new HashMap<>();
        HashMap<String, Rooms> roomsHashMap = new HashMap<>();
        
        ArrayList<String> pStorage = new ArrayList<>();

        ArrayList<String> flag = new ArrayList<>();
        flag.add("1");
        int tempAttempt;

        Player player = new Player();
    ///////////////////////////////////////////////////////////////////////////////////////////////--BAUDEL    
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Monsters.txt"));
            String line;
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

                MonsterHashMap.put(mName, new Monster(mName,mDescription,pLocation,temp,pDescription,pAnswer,pnswer,pLocion,pcation));
//                System.out.print(MonsterHashMap.get(mName).getName());
//                System.out.print(" = ");
//                System.out.println(MonsterHashMap.get(mName).getGoldReward());
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////

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
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        
        
        
        
        
        
        
        
        
        
        
        
        
        
       
        
        
        
        
        
        
        
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        
        
        
        
        

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
            else if (command[0].equals("explore"))
            {
                if (command.length >= 2) {
                    String temp = "";

                    for (int i = 1; i < command.length; i++) {
                        temp = temp + command[i] + "";
                    }
                    temp = temp.trim();

                    System.out.println(roomsHashMap.get(player.getLocation()).getRoomName());

                    player.explore(temp);
                } else if (command.length == 1) {
                    player.explore(roomsHashMap);
                } else {
                    System.out.println(command[1] + " is an Invalid Command");
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
                    System.out.println("!# Invalid Command #!");
                    System.out.println("");
                    System.out.println(roomsHashMap.get(player.getLocation()).getRoomDescription());
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
                    System.out.println(command[1] + " is an Invalid Command");
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
                    System.out.println(command[1] + " is an Invalid Command");
                }
            } else if (command.length == 1) {
                if (command[0].equals("inventory")) {
                    player.getInventory();
                }
            }
            if (command[0].equals("north") || command[0].equals("south") || command[0].equals("east") || command[0].equals("west") || command[0].equals("n") || command[0].equals("s") || command[0].equals("e") || command[0].equals("w"))
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
        }
    }
}

