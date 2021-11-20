package main;

import ui.GameUI;

import javax.swing.*;

/**
 * Starts the program
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameUI();
            }
        });
    }
}
