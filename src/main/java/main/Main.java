package main;

import game.GameController;
import ui.GameUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        //System.out.println("Hello, world!");
        // gameController = GameController.getInstance();
        //System.out.println(gameController.toString());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameUI();
            }
        });
        // TODO: Implement me!
    }
}
