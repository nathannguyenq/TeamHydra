package org.example;

import org.example.Controller.Controller;
import org.example.Model.Monster;
import org.example.View.View;

import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;

public class Main {
    static Controller gamew;

    private static void saveGame() {
    	
        try {
            FileOutputStream fos = new FileOutputStream("Adv.sav");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gamew);
            oos.flush();
            oos.close();
            System.out.println("Game Saved\n");
        } catch (Exception e) {
            System.out.println("Can't Save Data\n");
        }
    }
    private static void loadGame() {
        try {
            FileInputStream fis = new FileInputStream("Adv.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            gamew = (Controller) ois.readObject();
            ois.close();
            System.out.println("Game Loaded\n");
        } catch (Exception e) {
            System.out.println("Can't load Data\n");
        }
    }
    public static void main(String[] args) {
        View view = new View();
        Controller game = new Controller();
        String str = "";
        view.intro(str);

//        BufferedReader in;
//        String input;
//        String output = "";
//        in = new BufferedReader(new InputStreamReader(System.in));
//        do {
//            input = in.readLine();
//            switch (input) {
//                case "save":
//                    saveGame();
//                    break;
//                case "load":
//                    loadGame();
//                    break;
//            }
//        }

        boolean running = true;

        while (running) {
            game.runGame();
        }
    }
}
