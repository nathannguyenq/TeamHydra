package org.example;

import org.example.Controller.Controller;
import org.example.Model.Player;
import org.example.View.View;
import java.io.Serializable;
import java.io.*;


public class Main implements Serializable{
    public static Controller gamew;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        View view = new View();
        Controller game = new Controller();
        String str = "";
        Player.EnterPlayerName();
        view.intro(str);
        boolean running = true;
        while (running) {
            game.runGame();
        }
    }

    public static void newGame() {
        View view = new View();
        Controller game = new Controller();
        System.out.println("---------------NEW GAME------------------");
        Player.EnterPlayerName();
        game.runGame();
    }

    public static void saveGame() {
        try {
            FileOutputStream fos = new FileOutputStream("Adv.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Main.gamew);
            oos.flush();
            oos.close();
            System.out.println("Game saved - quitting\n");
            quitGame();

        } catch (Exception e) {
            System.out.println("Can't Save Data\n");
        }
    }

    public static void loadGame() {
        try {
            FileInputStream fis = new FileInputStream("Adv.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            gamew = (Controller) ois.readObject();
            ois.close();
            System.out.println("Game Loaded\n");
        } catch (Exception e) {
            System.out.println("Can't load Data\n");
        }
    }

    public static void quitGame(){
        System.exit(0);
    }
}