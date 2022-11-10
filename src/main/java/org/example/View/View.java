package org.example.View;
import org.example.Model.Player;
import java.io.Serializable;
import java.util.Scanner;

import static org.example.Main.quitGame;
import static org.example.Main.saveGame;

public class View implements Serializable {
    public void chooseDirection (String str) {
        System.out.println("Which way would you like to go? (N,E,S,W) or you can quit");
    }

    public static void intro(String str) {
        System.out.println("########################################################################################");
        System.out.printf(" %50s %-15s %n", "WELCOME TO RATAISSANCE", Player.playerName);
        System.out.println("########################################################################################");
        System.out.println("\n");

        System.out.println("Please enter a command to begin game or enter 'help' to see a list of commands available");

        System.out.println("----------------------------------------");
        System.out.println();
        System.out.println("The room is dark and it is hard to see your way around. However, through the blackness,\nyou manage to make out the figure of what seems to be a doorway to the EAST.");
        System.out.println("Which way do you want to go? (N,E,S,W)" + " or you can quit (q)");
    }

    public void invalid(String str) {
        System.out.println("Invalid Command, Please Try Again");
    }

    public void visited(String str) {
        System.out.println("[You have been here before]" + '\n');
    }

    public void notVisited(String str) {
        System.out.println("[Never been here]" + '\n');
    }
    public void leave(String str) {
        System.out.println("\"leave\" to go back");
    }
    public void useItem(String str) {
        System.out.print("Or use: ");
    }

    public void doNotHave(String str) {
        System.out.print("You do not have ");
    }

    public void noEquip(String str) {
        System.out.print("You can not equip ");
    }

    public void noWear(String str) {
        System.out.print("You can not wear ");
    }
    public void noEat(String str) {
        System.out.print("You can not eat ");
    }
    public void noRead(String str) {
        System.out.print("You can not read ");
    }
    public void notHere(String str) {
        System.out.println(" is not here");
    }

    public void slQ(String str) {
        System.out.println("\"shop\" to shop with Quintella or \"leave\" to leave or \"talk\" to talk to Quintella");
    }
    public void slQ1(String str) {
        System.out.println("So what'll it be...");
    }
    public void slQ2(String str) {
        System.out.println("Anything else?");
    }
    public void slQ3(String str) {
        System.out.println("Thank you. Come again soon.");
    }
    public void slR(String str) {
        System.out.println("\"shop\" to shop with Quintella or \"leave\" to leave or \"talk\" to talk to Quintella");
    }

    public void quitGameView(String str){
        System.out.println("Do you wish to save the game? (yes/no)");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (input.equalsIgnoreCase("yes")){
            saveGame();
        }
        else if (input.equalsIgnoreCase("no")) {
            quitGame();
        }
    }

    public void stat1(String str) {
        System.out.print("Player Attack: ");
    }
    public void stat2(String str) {
        System.out.print("Player Health: ");
    }
    public void stat3(String str) {
        System.out.print("Player Max Health: ");
    }

    public void help(){
        System.out.printf("------------------------------------------------------------------------------------%n");
        System.out.printf("                               COMMANDS AVAILABLE %n");
        System.out.printf("------------------------------------------------------------------------------------%n");
        System.out.printf("| %-22s | %-18s                                  %n", "COMMAND", "ACTION");
        System.out.printf("------------------------------------------------------------------------------------%n");
        System.out.printf("| %-22s | %-18s %n", "NORTH", "TO MOVE FORWARD");
        System.out.printf("| %-22s | %-18s %n", "EAST", "TO MOVE TO THE RIGHT");
        System.out.printf("| %-22s | %-18s %n", "SOUTH", "TO MOVE BACKWARD");
        System.out.printf("| %-22s | %-18s %n", "WEST", "TO MOVE TO THE LEFT");
        System.out.printf("| %-22s | %-18s %n", "TAKE + [ITEM NAME]", "TO ADD ITEM INTO INVENTORY");
        System.out.printf("| %-22s | %-18s %n", "REMOVE + [ITEM NAME]", "TO REMOVE ITEM FROM INVENTORY");
        System.out.printf("| %-22s | %-18s %n", "EQUP + [ITEM NAME]", "TO TAKE ITEM IN YOUR HAND");
        System.out.printf("| %-22s | %-18s %n", "WEAR + [ITEM NAME]", "TO WEAR THE ITEM PRESENT IN THE ROOM");
        System.out.printf("| %-22s | %-18s %n", "USE + [ITEM NAME]", "TO USE KEYS, UPGRADE KITS OR RICIN POWDER");
        System.out.printf("| %-22s | %-18s %n", "CONSUME + [ITEM NAME]", "TO EAT THE CONSUMABLE FOOD ITEM");
        System.out.printf("| %-22s | %-18s %n", "OBSERVE", "TO INSPECT THE ITEM IN THE ROOM");
        System.out.printf("| %-22s | %-18s %n", "EXPLORE", "TO LOOK AROUND AND GET DESCRIPTION OF THE CURRENT ROOM");
        System.out.printf("| %-22s | %-18s %n", "SOLVE", "TO SOLVE THE PUZZLE IN THE ROOM");
        System.out.printf("| %-22s | %-18s %n", "READ + [DOCUMENT NAME]", "TO READ THE DOCUMENT");
        System.out.printf("| %-22s | %-18s %n", "GOTO + [ROOM NAME]", "TO GO DIRECTLY TO AN UNLOCKED ROOM");
        System.out.printf("| %-22s | %-18s %n", "ENTER + [SHOP NAME]", "TO ENTER THE NPC SHOP");
        System.out.printf("| %-22s | %-18s %n", "TALK + [NPC NAME]", "TO TALK TO THE NPC IN THE ROOM");
        System.out.printf("| %-22s | %-18s %n", "SHOP + [NPC NAME]", "TO SHOP ITEMS IN THE NPC'S STORE");
        System.out.printf("| %-22s | %-18s %n", "ATTACK", "TO FIGHT THE MONSTER WITH THE MOST RECENT WEAPON");
        System.out.printf("| %-22s | %-18s %n", "RUN", "TO RUN AWAY FROM THE MONSTER **results in losing money*");
        System.out.printf("| %-22s | %-18s %n", "MONSTER INFO", "TO GET DETAILS OF THE MONSTER IN THE ROOM");
        System.out.printf("| %-22s | %-18s %n", "BEGIN COMBAT", "TO LOCK INTO COMBAT WITH MONSTER. **you can no longer run");
        System.out.printf("| %-22s | %-18s %n", "YES OR NO", "TO ANSWER ANY YES OR NO QUESTIONS");
        System.out.printf("| %-22s | %-18s %n", "INVENTORY", "TO CHECK YOUR INVENTORY");
        System.out.printf("| %-22s | %-18s %n", "STATS", "TO CHECK YOUR INVENTORY");
        System.out.printf("| %-22s | %-18s %n", "SAVE", "TO SAVE THE CURRENT GAME PROGRESS");
        System.out.printf("| %-22s | %-18s %n", "LOAD", "TO LOAD THE LAST SAVED GAME PROGRESS");
        System.out.printf("| %-22s | %-18s %n", "NEW", "TO START A NEW GAME");
        System.out.printf("| %-22s | %-18s %n", "QUIT OR Q", "TO QUIT THE CURRENT GAME SESSION");;
        System.out.printf("------------------------------------------------------------------------------------%n");

    }

}