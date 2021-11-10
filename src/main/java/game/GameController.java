package game;

import java.util.ArrayList;
import java.util.Timer;

import character.Player;
import utilities.Movement;
import character.CharacterModel;
import reward.Reward;
import reward.Trap;
import utilities.Constants;
import utilities.Position;

public class GameController {
    private static GameController instance = null;
    private Maze maze;
    private Player player;
    private ArrayList<CharacterModel> enemies;
    private ArrayList<Reward> rewards;
    private ArrayList<Trap> traps;
    private Timer timer;
    private boolean isRunning;
    private boolean isPaused;

    private GameController() {
        instance = this;
        this.maze = new Maze(Constants.mazeHeight, Constants.mazeWidth);
        this.player = null;
        this.timer = new Timer();
    }

    public static GameController getInstance() {
        if (GameController.instance == null)
            new GameController();

        return GameController.instance;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void startGame() {
        setRunning(true);
    }

    public void endGame() {
        setRunning(false);
    }

    public void pauseGame() {
        this.isPaused = true;
    }

    public void unpauseGame() {
        this.isPaused = false;
    }

    public void updateGame(String playerInput) {
        // TODO: Implement me!
    }

    public void addEnemy(CharacterModel enemy) {
        if (enemy == null)
            return;

        enemies.add(enemy);
    }

    public void removeEnemy(CharacterModel enemy) {
        if (enemy == null)
            return;

        enemies.remove(enemy);
    }

    public void addReward(Reward reward) {
        if (reward == null)
            return;

        rewards.add(reward);
    }

    public void removeReward(Reward reward) {
        if (reward == null)
            return;

        rewards.remove(reward);
    }

    public void addTrap(Trap trap) {
        if (trap == null)
            return;

        traps.add(trap);
    }

    public void removeTrap(Trap trap) {
        if (trap == null)
            return;

        traps.remove(trap);
    }

    private void clearEnemies() {
        enemies.clear();
    }

    private void clearTraps() {
        traps.clear();
    }

    private void clearRewards() {
        rewards.clear();
    }

    private void clearAll() {
        clearEnemies();
        clearTraps();
        clearRewards();
    }

    private void createPlayer() {
        this.player = Player.getInstance();
        Position pos = new Position(Constants.playerStartX, Constants.playerStartY);
        player.setPosition(pos);
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
