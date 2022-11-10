package org.example.Controller;

import org.example.Model.*;
import org.example.View.View;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Controller {
    Scanner scanner = new Scanner(System.in);
    View view = new View();
    Player player = new Player(100, 0, 100, 0);
    String str = "";
    ArrayList<String> pStorage = new ArrayList<>();
    ArrayList<String> flag = new ArrayList<>();
    int tempAttempt;

    HashMap<String, Items> itemsHashMap = Items.createItems();
    HashMap<String, Puzzle> puzzleHashMap = Puzzle.createPuzzles();
    HashMap<String, Monster> monsterHashMap = new HashMap<>();
    HashMap<String, NPC> npcHashMap = NPC.createNPC(itemsHashMap);
    HashMap<String, Rooms> roomsHashMap = Rooms.createRooms(itemsHashMap, puzzleHashMap, monsterHashMap, npcHashMap);

    ArrayList<String> MonsterFlags = new ArrayList<>();


    String currRoom = "";
    String prevRoom = "";
    String cuRoom = "";
    String prRoom = "";
    String cRoom = "";
    String pRoom = "";

    public static void main(String[] args) {
    }

    public void runGame() {
        String input = scanner.nextLine();
        input = input.toLowerCase();
        String[] command = input.split(" ");

        if (command[0].equals("add")) {
            if (command.length >= 2) {
                String temp = "";
                for (int i = 1; i < command.length; i++) {
                    temp = temp + command[i] + " ";
                }
                temp = temp.trim();

                player.addItem(temp, itemsHashMap.get(temp));

            } else {
                view.invalid(str);
            }
        }

        if (command[0].equals("q") || command[0].equals("quit")) {

        } else if (command[0].equals("explore")) {
            if (command.length == 1) {
                player.explore(roomsHashMap);
            } else {
                view.invalid(str);
            }
        } else if (command[0].equals("take")) {
            if (command.length >= 2) {
                String temp = "";
                for (int i = 1; i < command.length; i++) {
                    temp = temp + command[i] + " ";
                }
                temp = temp.trim();
                if (!roomsHashMap.get(player.getLocation()).getInventory().containsKey(temp)) {
                    view.invalid(str);
                }
                else {
                    player.add(temp, roomsHashMap);
                    int rTotal = player.invAmount(temp);
                    rTotal++;
                    player.getPlayerInventory().get(temp).setInvAmount(rTotal);
                    System.out.println(player.invAmount(temp));
                }
            } else {
                view.invalid(str);
            }
        }
        else if (command[0].equals("remove")) {
            if (command.length >= 2) {
                String temp = "";
                for (int i = 1; i < command.length; i++) {
                    temp = temp + command[i] + " ";
                }
                temp = temp.trim();
                if (!player.getPlayerInventory().containsKey(temp)) {
                    view.invalid(str);
                }
                else if (player.getPlayerInventory().get(temp).getInvAmount() == 1) {
                    int iTotal = player.itemAmount(temp);
                    player.getPlayerInventory().get(temp).setInvAmount(0);
                    player.drop(temp, roomsHashMap);
                    iTotal++;
                    roomsHashMap.get(player.getLocation()).getInventory().get(temp).setiAmount(iTotal);
                } else if (player.getPlayerInventory().get(temp).getInvAmount() > 1) {
                    int rTotal = player.invAmount(temp);
                    rTotal--;
                    player.getPlayerInventory().get(temp).setInvAmount(rTotal);
                    player.dropOne(temp, roomsHashMap);
                } else if (roomsHashMap.get(player.getLocation()).getInventory().get(temp).getiAmount() == 1) {
                    player.drop(temp, roomsHashMap);
                } else {
                    view.invalid(str);
                }
            }
            else {
                view.invalid(str);
            }
        } else if (command[0].equals("observe")) {
            if (command.length >= 2) {
                String temp = "";
                for (int i = 1; i < command.length; i++) {
                    temp = temp + command[i] + "";
                }
                temp = temp.trim();
                if (!player.getPlayerInventory().containsKey(temp)) {
                    view.invalid(str);
                }
                else {
                    player.explore(temp);
                }
            } else {
                view.invalid(str);
            }
        } else if (command.length == 1) {
            if (command[0].equals("inventory")) {
                player.getInventory();
            }
            if (command[0].equals("stats")) {
                System.out.println("Player Attack: " + player.getPlyattack());
                System.out.println("Player Health: " + player.getPlyhealth());
                System.out.println("Player Max Health: " + player.getPlyMHealth());
//                System.out.println(player.getEquipment());
            }
            if (command[0].equals("help")) {
                System.out.println();
            }
        } else if (command[0].equals("equip") || command[0].equals("eq")) {
            if (command.length >= 2) {
                String temp = "";
                for (int i = 1; i < command.length; i++) {
                    temp = temp + command[i] + " ";
                }
                temp = temp.trim();
                if (!player.getPlayerInventory().containsKey(temp)) {
                    view.invalid(str);
                }
                else if (player.itemType(temp).equals("Weapon")) {
                    player.equip(temp, player.getPlayerInventory());
                }
                else if (player.itemType(temp).equals("Armor")) {
                    player.wear(temp,player.getPlayerInventory());
                }
                else {
                    System.out.println("You can not equip " + temp);
                }
            }
            else {
                System.out.println("You do not have " + command[1]);
            }
        }

        else if (command[0].equals("wear")) {
            if (command.length >= 2) {
                String temp = "";
                for (int i = 1; i < command.length; i++) {
                    temp = temp + command[i] + " ";
                }
                temp = temp.trim();
                if (!player.getPlayerInventory().containsKey(temp)) {
                    view.invalid(str);
                }
                else if (player.itemType(temp).equals("Armor")) {
                    player.wear(temp,player.getPlayerInventory());
                }
                else {
                    System.out.println("You can not wear " + temp);
                }
            } else {
                System.out.println("You do not have " + command[1]);
            }
        }
        else if (command[0].equals("consume") || command[0].equals("eat")) {
            if (command.length >= 2) {
                String temp = "";
                for (int i = 1; i < command.length; i++) {
                    temp = temp + command[i] + " ";
                }
                temp = temp.trim();
                if (!player.getPlayerInventory().containsKey(temp)) {
                    view.invalid(str);
                }
                else if (player.itemType(temp).equals("Food")) {
                    player.heal(temp, player.getPlayerInventory());
                }
                else {
                    System.out.println("You can't eat " + temp);
                }
            } else {
                System.out.println("You do not have " + command[1]);
            }
        }
        else if (command[0].equals("read")) {
            if (command.length >= 2) {
                String temp = "";
                for (int i = 1; i < command.length; i++) {
                    temp = temp + command[i] + " ";
                }
                temp = temp.trim();
                if (!player.getPlayerInventory().containsKey(temp)) {
                    view.invalid(str);
                }
                else if (player.itemType(temp).equals("Document")) {
                    player.explore(temp);
                }
                else {
                    System.out.println("You can't read " + temp);
                }
            } else {
                System.out.println("You do not have " + command[1]);
            }
        }
//        else if (command[0].equals("unequip") || command[0].equals("un")) {
//            if (command.length >= 2) {
//                String temp = "";
//                for (int i = 1; i < command.length; i++) {
//                    temp = temp + command[i] + " ";
//                }
//                temp = temp.trim();
//                player.unequip(temp,player.getPlayerEquipment());
//
//
//            }
        else if (command[0].equals("talk")) {
            if (command.length >= 2) {
                String temp = "";
                for (int i = 1; i < command.length; i++) {
                    temp = temp + command[i] + " ";
                }
                temp = temp.trim();
                if (!roomsHashMap.get(player.getLocation()).getNpcHash().containsKey(temp)) {
                    System.out.println(temp + " is not here");
                }
                else if (roomsHashMap.get(player.getLocation()).getNpcHash().containsKey(temp)) {
                    while (roomsHashMap.get(player.getLocation()).getNpcHash().containsKey(temp)) {

                    }
                }
                else {
                    System.out.println(temp + " is not here");
                }
            } else {
                view.invalid(str);
            }
        }
        else if (command[0].equals("goto")) {
            if (command.length >= 2) {
                String temp = "";
                for (int i = 1; i < command.length; i++) {
                    temp = temp + command[i] + " ";
                }
                temp = temp.trim();
                if(flag.contains(temp.toUpperCase())) {
                    player.setLocation(temp.toUpperCase());
                    System.out.println(player.getLocation());
                    System.out.println(roomsHashMap.get(player.getLocation()).getRoomDescription());
                }
                else{
                    System.out.println("you havent been thee before");
                }
            }
        }
        if (command[0].equals("ladder-up") || command[0].equals("ladder-down") || command[0].equals("north") || command[0].equals("south") || command[0].equals("east") || command[0].equals("west") || command[0].equals("u") || command[0].equals("d") || command[0].equals("n") || command[0].equals("s") || command[0].equals("e") || command[0].equals("w")) {
            player.move(command[0], roomsHashMap);
            if (roomsHashMap.get(player.getLocation()).getPuzzleHashMap().containsKey(player.getLocation())) {
                tempAttempt = roomsHashMap.get(player.getLocation()).getPuzzleHashMap().get(player.getLocation()).getPuzzleAttempts();
                while (tempAttempt > 0 && !pStorage.contains(roomsHashMap.get(player.getLocation()).getRoomID())) {
                    System.out.println(roomsHashMap.get(player.getLocation()).getPuzzleHashMap().get(player.getLocation()).getPuzzleDescription());
                    String Solution = roomsHashMap.get(player.getLocation()).getPuzzleHashMap().get(player.getLocation()).getPuzzleAnswer().get(0);
                    String s = scanner.nextLine();
                    String temp = roomsHashMap.get(player.getLocation()).getPuzzleHashMap().get(player.getLocation()).getPuzzleReward();
                    if (!s.equals(Solution)) {
                        tempAttempt++;
                        System.out.println("Wrong Answer, this is your Attempt(s): " + tempAttempt + '\n');

                    }
                    if(!roomsHashMap.get(player.getLocation()).getPuzzleHashMap().get(player.getLocation()).getPuzzleReward().equals("-")){
                            System.out.println("Puzzle Completed" + '\n');
                            player.addItem(roomsHashMap.get(player.getLocation()).getPuzzleHashMap().get(player.getLocation()).getPuzzleReward(), itemsHashMap.get(roomsHashMap.get(player.getLocation()).getPuzzleHashMap().get(player.getLocation()).getPuzzleReward()));
                            //player.getPlayerInventory().put(roomsHashMap.get(player.getLocation()).getPuzzleHashMap().get(player.getLocation()).getPuzzleReward(), new Items("1", str, "Item won from puzzle", "key", new ArrayList<String>(), 0, 0, 0, 0, 1, 1));
                            roomsHashMap.get(player.getLocation()).getPuzzleHashMap().get(player.getLocation()).getPuzzleReward();
                            System.out.println(temp);
                            pStorage.add(roomsHashMap.get(player.getLocation()).getRoomID());


                    } else if (roomsHashMap.get(player.getLocation()).getPuzzleHashMap().get(player.getLocation()).getPuzzleReward().equals("-")) {
                        System.out.println("Puzzle Completed" + '\n');
                        player.setLocation("SW_5");

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

            if (roomsHashMap.get(player.getLocation()).getNeedsRightDirection() == false) {
                prRoom = cuRoom;
                cuRoom = player.getLocation();
            }
            if (roomsHashMap.get(player.getLocation()).getNeedsRightDirection() == true && !(command[0].equals("ladder-down") || command[0].equals("d"))) {
                while (roomsHashMap.get(player.getLocation()).getNeedsRightDirection() == true) {
                    System.out.println(roomsHashMap.get(player.getLocation()).getLockedDescription());
                    view.leave(str);
                    System.out.println();
                    String lockInput = scanner.nextLine();
                    lockInput = lockInput.toLowerCase();
                    if (lockInput.equals("leave")) {
                        player.setLocation(cuRoom);
                    }
                }
            }else {
                roomsHashMap.get(player.getLocation()).setNeedsRightDirection(false);
            }
            if (roomsHashMap.get(player.getLocation()).getMonsterRequirement() == false) {
                pRoom = cRoom;
                cRoom = player.getLocation();
            }
            if (roomsHashMap.get(player.getLocation()).getMonsterRequirement() == true && !MonsterFlags.contains("Cat_Assassin")) {
                while (roomsHashMap.get(player.getLocation()).getMonsterRequirement() == true) {
                    System.out.println(roomsHashMap.get(player.getLocation()).getLockedDescription());
                    view.leave(str);
                    System.out.println();
                    String lockInput = scanner.nextLine();
                    lockInput = lockInput.toLowerCase();
                    if (lockInput.equals("leave")) {
                        player.setLocation(cRoom);
                    }
                }
            }else {
                roomsHashMap.get(player.getLocation()).setMonsterRequirement(false);
            }
            System.out.print(roomsHashMap.get(player.getLocation()).getRoomName());
            System.out.println(" ("+ roomsHashMap.get(player.getLocation()).getRoomID() +")");
            if (!flag.contains(roomsHashMap.get(player.getLocation()).getRoomID())) {
                view.notVisited(str);
                flag.add((roomsHashMap.get(player.getLocation()).getRoomID()));
            } else {
                view.visited(str);
            }
            
            
            
            monsterHashMap.entrySet().forEach(entry -> {
        
                for (String num :  monsterHashMap.get(entry.getKey()).getSpawnLocation()) { 
                	
                    Scanner in = new Scanner(System.in);

                	if(roomsHashMap.get(player.getLocation()).getRoomID().equals(num)) {
                		

                    	
                    	if(!MonsterFlags.contains(num)) {
                			System.out.println("Theres is a Monster in this Room, are you ready for this fight? YES or NO" );
                			  String answer = in.nextLine();

                			  
                			  if(answer.equals("YES")) {
                				  
                				  
                          		System.out.println("THE name of the Monster is: " + monsterHashMap.get(entry.getKey()).getName());
                          		
                          		while(monsterHashMap.get(entry.getKey()).getHP() > 0) {
                          			
                              		System.out.println("What command would you like to Use? attack, info or Run");
                              		System.out.println("You will get hit after every Command");

                      			     String battle = in.nextLine().toUpperCase();

                              		if(battle.equals("ATTACK")) {
                              		monsterHashMap.get(entry.getKey()).setHP((Player.attack(monsterHashMap.get(entry.getKey()).getHP()))); 

                              			System.out.println("Current monster Hp = " + monsterHashMap.get(entry.getKey()).getHP() );
                              		}
                              		else if (battle.equals("INFO")) {
                              			System.out.println(monsterHashMap.get(entry.getKey()).toString());

                              		}
                              		else if (battle.equals("RUN")) {
                              			System.out.println(monsterHashMap.get(entry.getKey()).getLossMessage());
                              			
                              			
                              			break;
                              		}
                              		
                              		else if(player.getPlyhealth() < 20){
                              			
                              			System.out.println("You almost Dead go back !!!");
                              			System.out.println(monsterHashMap.get(entry.getKey()).getLossMessage());


                              			break;
                              		
                              		}
                              		
                              		else {
                              			System.out.println("Wrong Input");
                              		}
                              		
                              		player.setPlyhealth(player.getPlyhealth()-monsterHashMap.get(entry.getKey()).getAttackDamage());
                              		
                              		System.out.println("The monster just Hit you your current HP is " + player.getPlyhealth());
                              		
                              		
                              		if(monsterHashMap.get(entry.getKey()).getHP() < 0) {
                                  		MonsterFlags.add(num);
                                  		player.setplyMoney(player.getPlyMoney() + monsterHashMap.get(entry.getKey()).getGoldReward());
                                  		
                                  		System.out.println(monsterHashMap.get(entry.getKey()).getWinMessage());
                              			
                              			


                              		}
                              		

                          		}
                          		
                          		
                				  
                			  }
                			  else {
                				  System.out.println("i Hope your ready Next Time");
                			  }
                			  
                			 
                			

                		}else {
                    		System.out.println("There used to be a Monster Here, You already kill " + monsterHashMap.get(entry.getKey()).getName());

                		}

                	}
                	//System.out.println(num);

                	
                	}
                 		
                });
            
//            monsterHashMap.entrySet().forEach(entry -> {
//            	//MonsterHashMap.get(entry.getKey()).getSpawnLocation();
//            	
//            	
//            
//                System.out.print(entry.getKey());
//                System.out.println(" " +monsterHashMap.get(entry.getKey()).getSpawnLocation());
//                
//                System.out.print(" ");
//            });
            

            System.out.println(roomsHashMap.get(player.getLocation()).getRoomDescription());

        }
    }
}

