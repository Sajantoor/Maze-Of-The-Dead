package main;

import game.GameController;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
        GameController gameController = GameController.getInstance();
        System.out.println(gameController.toString());
        // TODO: Implement me!
    }
}
