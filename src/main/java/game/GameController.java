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

    /**
     * Game loop that runs every 'tick' Involves player input, collidables and
     * checking if the game is over
     * 
     * @param playerInput
     */
    public void updateGame(String playerInput) {
        if (isRunning && !isPaused) {
            updatePlayerInput(playerInput);
            checkColliables();
            checkScore();
        }
    }

    /**
     * Updates the game based on player input and updates the player position
     * 
     * @param playerInput String of player input
     */
    private void updatePlayerInput(String playerInput) {

        // TODO: Not done yet
        switch (playerInput) {
        case Constants.playerMoveUp:
            movePlayer(Movement.UP);
            break;

        case Constants.playerMoveDown:
            movePlayer(Movement.DOWN);
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

    /**
     * Move the player in the direction specified by the movement. Checks if there
     * is a collision with a wall and acts accordingly.
     * 
     * @param movement the movement to be performed
     */
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

    /**
     * Checks if the player has collided with any collidables and acts accordingly.
     * For example, coliding with a reward increases the player's score and removes
     * the reward. Coliding with a zombie, makes the player lose the game.
     */
    private void checkColliables() {
        Position position = this.player.getPosition();
        Cell cell = this.maze.getCell(position);
        // if the position has a reward, the player picks it up and adds to score
        switch (cell.getCellType()) {
        case REWARD:
            Reward reward = getReward(position);

            if (reward != null) {
                // TODO: Refactor maybe this is repeated twice
                this.player.updateScore(reward.getPoints());
                cell.setEmpty();
                removeReward(reward);
            }
            break;
        case TRAP:
            Trap trap = getTrap(position);

            if (trap != null) {
                this.player.updateScore(-trap.getPoints());
                cell.setEmpty();
                removeTrap(trap);
            }
            break;
        default:
            break;
        }

        // if the next position has an enemy, then the player loses the game
        CharacterModel enemy = getEnemy(position);

        if (enemy != null) {
            loseGame();
        }
    }

    /**
     * Checks if there is a reward at a position and returns it
     * 
     * @return the reward at the position, else null
     * @see Reward
     * @see Position
     */
    private Reward getReward(Position position) {
        for (Reward reward : rewards) {
            if (reward.getPosition().equals(position)) {
                rewards.remove(reward);
                return reward;
            }
        }

        return null;
    }

    /**
     * Checks if there is a trap at a position and returns it
     * 
     * @return the trap at the position, else null
     * @see Trap
     * @see Position
     */
    private Trap getTrap(Position position) {
        for (Trap trap : traps) {
            if (trap.getPosition().equals(position)) {
                traps.remove(trap);
                return trap;
            }
        }

        return null;
    }

    /**
     * Checks if there is a enemy at a position and returns it
     * 
     * @return the enemy at the position, else null
     * @see CharacterModel
     * @see Position
     */
    private CharacterModel getEnemy(Position position) {
        for (CharacterModel enemy : enemies) {
            if (enemy.getPosition().equals(position)) {
                return enemy;
            }
        }

        return null;
    }

    /**
     * Checks if the score is below zero and if so, the game is lost
     */
    private void checkScore() {
        if (this.player.getScore() < 0) {
            loseGame();
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
