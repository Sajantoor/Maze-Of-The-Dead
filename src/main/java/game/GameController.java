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
    private ArrayList<CharacterModel> enemies;
    private ArrayList<Reward> rewards;
    private ArrayList<Trap> traps;
    private Timer timer;
    private boolean isRunning;
    private boolean isPaused;
    private boolean hasCollectedAllRewards = false;

    // #region Constructor and Singleton
    // =========================================================================

    private GameController() {
        instance = this;
        this.maze = new Maze(Constants.mazeHeight, Constants.mazeWidth, Constants.mazeRooms);
        this.timer = new Timer();
    }

    /**
     * GameController follows the singleton design pattern. This method returns the
     * instance of the GameController.
     * 
     * @return Instance of GameController
     */
    public static GameController getInstance() {
        if (GameController.instance == null)
            new GameController();

        return GameController.instance;
    }

    // =========================================================================
    // #endregion

    // #region Game state methods
    // =========================================================================

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

    private void winGame() {
        // TODO: Implement me!
    }

    private void loseGame() {
        // TODO: Implement me!
    }

    // =========================================================================
    // #endregion

    /***************************************************************************
     * 
     * Game Logic Methods
     * 
     **************************************************************************/

    // #region Game loop and miscellaneous game logic
    // ==========================================================================

    /**
     * Game loop that runs every 'tick' Involves player input, collidables and
     * checking if the game is over
     * 
     * @param playerInput String of player input
     */
    public void updateGame(String playerInput) {
        if (isRunning && !isPaused) {
            updatePlayerInput(playerInput);
            checkCollidables();
            hasWon();
        }
    }

    /**
     * Checks if the score is below zero and if so, the game is lost Invokes the
     * lose game method.
     */
    private void checkScore() {
        if (Player.getInstance().getScore() < 0) {
            loseGame();
        }
    }

    /**
     * Checks if the player has won and if so, invokes the win game method.
     */
    private void hasWon() {
        // check if player has collected all the rewards
        if (!hasCollectedAllRewards)
            return;

        // check if the player has reached the exit
        Position playerPosition = Player.getInstance().getPosition();
        boolean reachedEnd = maze.isEnd(playerPosition);
        if (reachedEnd)
            winGame();
    }

    /**
     * Checks if the player has collected all regular rewards and if so returns
     * true. Used to set the hasCollectedAllRewards boolean.
     * 
     * @return True if the player has collected all regular rewards, false
     *         otherwise.
     */
    private boolean collectedAllRewards() {
        if (rewards.size() == 0) {
            return true;
        }

        for (Reward reward : rewards) {
            // if the points are equal to reward points, then they aren't bonus
            // rewards, so they must be collected.
            if (reward.getPoints() == Constants.rewardPoints) {
                return false;
            }
        }

        return true;
    }

    private int generateEnemyMovement() {
        // TODO: Implement me!
        return 0;
    }

    // =========================================================================
    // #endregion

    // #region Player input
    // =========================================================================
    /**
     * Updates the game based on player input and updates the player position
     * 
     * @param playerInput String of player input
     */
    private void updatePlayerInput(String playerInput) {
        // TODO: Not done yet, need input for pausing, etc.

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
        Player player = Player.getInstance();
        Position position = player.getPosition();

        // update the player's position based on the movement
        Position nextPosition = Functions.updatePosition(position, movement);

        // check if the next position is a valid position, ie is there a wall
        Cell cell = maze.getCell(nextPosition);

        // if there is not a wall, the player can move
        if (!cell.isWall()) {
            player.move(movement);
        }
    }
    // =========================================================================
    // #endregion

    // #region Collisions
    // =========================================================================
    /**
     * Checks if the player has colided with a reward or a trap. If so, the
     * reward/trap is removed from the game and the player's score changes
     * accordingly.
     * 
     * @param object this is either a reward or a trap, casted according to cell
     *               type
     * @param cell   this is the cell the player has collided with
     */
    private void collided(Object object, Cell cell) {
        Player player = Player.getInstance();
        int scoreUpdate;

        switch (cell.getCellType()) {
            case REWARD:
                Reward reward = (Reward) object;
                scoreUpdate = reward.getPoints();
                removeReward(reward);
                // check if player has collected all rewards
                hasCollectedAllRewards = collectedAllRewards();
                break;
            case TRAP:
                Trap trap = (Trap) object;
                scoreUpdate = ((Trap) object).getPoints();
                removeTrap(trap);
                break;
            default:
                // just return here if there is no reward or trap
                return;
        }

        player.updateScore(scoreUpdate);
        cell.setEmpty();

        // If the score is decreasing, then check if the player has lost
        if (scoreUpdate < 0) {
            checkScore();
        }
    }

    /**
     * Checks if the player has collided with any collidables and acts accordingly.
     * For example, coliding with a reward increases the player's score and removes
     * the reward. Coliding with a zombie, makes the player lose the game.
     */
    private void checkCollidables() {
        Player player = Player.getInstance();
        Position position = player.getPosition();
        Cell cell = maze.getCell(position);

        // if the next position has an enemy, then the player loses the game
        CharacterModel enemy = getEnemy(position);

        if (enemy != null) {
            loseGame();
        }

        // check for rewards or traps
        switch (cell.getCellType()) {
            // if the position has a reward, the player picks it up and adds to score
            case REWARD:
                Reward reward = getReward(position);

                if (reward != null) {
                    collided(reward, cell);
                }
                break;
            case TRAP:
                Trap trap = getTrap(position);

                if (trap != null) {
                    collided(trap, cell);
                }
                break;
            default:
                break;
        }
    }

    // =========================================================================
    // #endregion

    /***************************************************************************
     * 
     * Adding, removing and getting entities
     * 
     **************************************************************************/
    // #region Getting entities

    /**
     * Checks if there is a reward at a position and returns it
     * 
     * @return the reward at the position, else null
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
     */
    private CharacterModel getEnemy(Position position) {
        for (CharacterModel enemy : enemies) {
            if (enemy.getPosition().equals(position)) {
                return enemy;
            }
        }

        return null;
    }

    // =========================================================================
    // #endregion

    // #region Adding and removing entities
    // =========================================================================
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

    // =========================================================================
    // #endregion

}
