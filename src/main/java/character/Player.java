package main.java.character;

import main.java.utilities.Position;

public class Player extends CharacterModel {
    private int score;

    private Player(Position position, int score) {
        // TODO: Implement me!
    }

    public Player getInstance() {
        // TODO: Implement me!
        return null;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
