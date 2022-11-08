package org.example.View;

public class View {
    public void chooseDirection (String str) {
        System.out.println("Which way would you like to go? (N,E,S,W)");
    }

    public void intro(String str) {
        System.out.println("\t" + "\t" + "#############################");
        System.out.println("\t" + "\t" + "#### Welcome to the game ####");
        System.out.println("\t" + "\t" + "#############################");
        System.out.println("\n");
        System.out.println("----------------------------------------");
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
}
