package org.example;

import org.example.Controller.Controller;
import org.example.View.View;

import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.html.StyleSheet;
import java.io.*;
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
        }

}