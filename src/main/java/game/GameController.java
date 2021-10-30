package main.java.game;

import java.util.ArrayList;
import java.util.Timer;

import main.java.character.Player;
import main.java.character.CharacterModel;
import main.java.reward.Reward;
import main.java.reward.Trap;

public class GameController {
    private Maze maze;
    private Player player;
    private ArrayList<CharacterModel> enemies;
    private ArrayList<Reward> reward;
    private ArrayList<Trap> trap;
    private Timer timer;
    private boolean isRunning;
    private boolean isPaused;

    private GameController() {
        // TODO: Implement me!
    }

    public static GameController getInstance() {
        // TODO: Implement me!
        return null;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void startGame() {
        // TODO: Implement me!
    }

    public void endGame() {
        // TODO: Implement me!
    }

    public void pauseGame() {
        // TODO: Implement me!
    }

    public void unpauseGame() {
        // TODO: Implement me!
    }

    public void updateGame(String playerInput) {
        // TODO: Implement me!
    }

    public void addEnemy(CharacterModel enemy) {
        // TODO: Implement me!
    }

    public void removeEnemy(CharacterModel enemy) {
        // TODO: Implement me!
    }

    public void addReward(Reward reward) {
        // TODO: Implement me!
    }

    public void removeReward(Reward reward) {
        // TODO: Implement me!
    }

    public void addTrap(Trap trap) {
        // TODO: Implement me!
    }

    public void removeTrap(Trap trap) {
        // TODO: Implement me!
    }

    private void clearEnemies() {
        // TODO: Implement me!
    }

    private void clearTraps() {
        // TODO: Implement me!
    }

    private void clearRewards() {
        // TODO: Implement me!
    }

    private void createPlayer() {
        // TODO: Implement me!
    }

    private void winGame() {
        // TODO: Implement me!
    }

    private void loseGame() {
        // TODO: Implement me!
    }

    private int generateEnemyMovement() {
        // TODO: Implement me!
        return 0;
    }
}
