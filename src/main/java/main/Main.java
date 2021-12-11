package main;

import ui.GameUI;

import javax.swing.*;

/**
 * Starts the program
 */
public class Main {
    /**
     * Starts the program
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameUI();
            }
        });
    }
}
