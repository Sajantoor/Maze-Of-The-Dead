package game;

import java.util.ArrayList;
import java.util.Timer;

import character.Player;
import utilities.Movement;
import character.CharacterModel;
import reward.Reward;
import reward.Trap;
import cell.Cell;
import utilities.Constants;
import utilities.Functions;
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
        this.maze = new Maze(Constants.mazeHeight, Constants.mazeWidth, Constants.mazeRooms);
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

    public void movePlayer(Movement movement) {
        // check if there is a wall in the way that would stop movement
        Position position = this.player.getPosition();

        // update the player's position based on the movement
        Position nextPosition = Functions.updatePosition(position, movement);

        // check if the next position is a valid position, ie is there a wall
        Cell cell = this.maze.getCell(nextPosition);

        // if there is a wall, then the player cannot move
        if (cell.isWall()) {
            return;
        }

        this.player.move(movement);
    }

    public void updateGame(String playerInput) {
        if (isRunning && !isPaused) {
            switch (playerInput) {
            case Constants.playerMoveUp:
                // TODO: Refactor forward and up to be the same name
                movePlayer(Movement.FORWARD);
                break;

            case Constants.playerMoveDown:
                // TODO: Same with backwards and down
                movePlayer(Movement.BACKWARD);
                break;

            case Constants.playerMoveLeft:
                movePlayer(Movement.LEFT);
                break;

            case Constants.playerMoveRight:
                movePlayer(Movement.RIGHT);
                break;
            default:
                break;
            }
        }
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
