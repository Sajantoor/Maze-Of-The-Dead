package game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Timer;

import character.Player;
import utilities.Movement;
import character.CharacterModel;
import reward.Reward;
import reward.Trap;
import reward.TrapType;
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

    // #region Constructor and Singleton
    // =========================================================================

    private GameController() {
        instance = this;
        enemies = new ArrayList<CharacterModel>();
        rewards = new ArrayList<Reward>();
        traps = new ArrayList<Trap>();
        maze = new Maze(Constants.mazeHeight, Constants.mazeWidth, Constants.mazeRooms);
        isRunning = false;
        isPaused = false;
        generateEntities();
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
            generateEnemyMovement();
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

    // =========================================================================
    // #endregion

    // #region Enemy Movements
    // =========================================================================

    private void generateEnemyMovement() {
        for (CharacterModel enemy : enemies) {
            findNextMove(enemy);
        }
    }

    /***
     * Moves the enemy to the next given position closes to the player
     * 
     * @param enemy The enemy we want to move
     */
    private void findNextMove(CharacterModel enemy) {
        Position current = enemy.getPosition();
        // Open queue to keep track of all possible moves the enemy could move to
        PriorityQueue<Position> openQueue = new PriorityQueue<Position>(100,
                Comparator.comparing(position -> getDistanceFromPlayer(position)));

        // add current position to open queue as well if not moving is the optimial move
        openQueue.add(current);

        // generate successors from the current based off the directions the enemy can
        // move
        // find the position which minimizes the distance to the player
        // then move the enemy to that position
        for (Movement movement : Movement.values()) {
            Position successor = Functions.updatePosition(current, movement);
            if (validateEnemyMove(successor)) {
                // add it to the open queue
                openQueue.add(successor);
            }
        }

        // There will always be at least one position in the open queue
        Position winner = openQueue.poll();
        enemy.setPosition(winner);
    }

    /**
     * Checks if the enemy can move in the given direction Checks if there is wall,
     * trap or enemy in the way, therefore cannot move
     * 
     * @param movement The position the enemy wants to move to
     * @return True if it can, false if it can't
     */
    private boolean validateEnemyMove(Position position) {
        // check if there is a wall, trap or enemy in the new position
        // if there is return false

        if (maze.isWall(position))
            return false;

        if (maze.isTrap(position))
            return false;

        if (getEnemy(position) != null)
            return false;

        return true;
    }

    /**
     * Gets the distance between the given position and the player. Calculated using
     * Pythagoras' theorem
     * 
     * @param position The current position we want to calculate the distance from
     * @return The distance between the given position and the player
     */
    private double getDistanceFromPlayer(Position position) {
        Position playerPos = Player.getInstance().getPosition();
        return Math.sqrt(
                Math.pow(playerPos.getX() - position.getX(), 2) + Math.pow(playerPos.getY() - position.getY(), 2));
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

    // #region Generating entities
    // =========================================================================

    /**
     * Generates all entities in the game, such as traps, rewards, and enemies and
     * adds them to the maze
     * 
     */
    private void generateEntities() {
        generateTraps(Constants.boobyTrapCount, TrapType.BOOBYTRAP);
        generateTraps(Constants.trapFallCount, TrapType.TRAPFALL);
        generateRewards(Constants.rewardCount);
        generateEnemies(Constants.enemyCount);
    }

    /**
     * Finds a random 'empty' position in the a subset of the maze, ie doesn't have
     * a trap, reward, etc in it.
     * 
     * @param startX the starting x coordinate of the maze
     * @param width  total width of the maze we want to look at
     * @param startY the starting y coordinate of the maze
     * @param height total height of the maze we want to look at
     * @return The random empty position in the maze
     */
    private Position findEmptyPosition(int startX, int width, int startY, int height) {
        int x = (int) ((Math.random() * (width - startX)) + startX);
        int y = (int) ((Math.random() * (height - startY)) + startY);

        Cell cell = maze.getCell(x, y);
        // regenerate random combinations until we find a cell in a valid location
        while (!cell.isEmpty()) {
            x = (int) ((Math.random() * (width - startX)) + startX);
            y = (int) ((Math.random() * (height - startY)) + startY);

            cell = maze.getCell(x, y);
        }

        Position position = new Position(cell.getPosition());
        return position;
    }

    /**
     * Finds an empty position in in the whole maze
     * 
     * @return The random empty position in the maze
     */
    private Position findEmptyPosition() {
        return findEmptyPosition(1, Constants.mazeWidth, 1, Constants.mazeHeight);
    }

    /***************************************************************************
     * 
     * Generating Traps
     * 
     **************************************************************************/
    /**
     * Adds traps to the board at random locations
     * 
     * @param num number of traps to add
     */
    private void generateTraps(int num, TrapType trapType) {
        // TODO: This could be more random, but this works for now.
        for (int i = 0; i < num; i++) {
            generateTrap(trapType);
        }
    }

    /**
     * Generates a trap at a random location, validates that the location doesn't
     * create an unsolvable maze and adds it to the maze and trap list
     * 
     * @param trapType the type of trap we want to generate
     */
    private void generateTrap(TrapType trapType) {
        Position position = findEmptyPosition();

        while (!isValidPosition(position)) {
            position = findEmptyPosition();
        }

        Cell cell = maze.getCell(position);
        cell.setTrap();
        Trap trap = new Trap(position, trapType);
        addTrap(trap);
    }

    /**
     * Checks if a given position is blocked, ie, if a trap is created at it, it
     * doesn't create an unsolvable maze
     * 
     * @param position The position we want to block (or add a trap)
     * @return True if we can block the position and the maze is solvable, false
     *         otherwise
     */
    private boolean isValidPosition(Position position) {
        // temporarily set the position to be a wall, it is empty
        // and check if the maze can be solved
        Cell cell = maze.getCell(position);
        cell.setWall();
        boolean isValid = maze.isSolvable();
        cell.setEmpty();
        return isValid;
    }

    /***************************************************************************
     * 
     * Generate Rewards
     * 
     **************************************************************************/

    /**
     * Generates rewards at random locations
     * 
     * @param num number of rewards to add
     */
    private void generateRewards(int num) {
        for (int i = 0; i < num; i++) {
            generateReward();
        }
    }

    /**
     * Generates a reward at a random location, checks if the player can reach the
     * reward to validate that the game is winnable, and adds it to the maze and
     * reward list
     */
    private void generateReward() {
        Position position = findEmptyPosition();

        while (!isReachable(position)) {
            position = findEmptyPosition();
        }

        Cell cell = maze.getCell(position);
        cell.setReward();
        Reward reward = new Reward(position);
        addReward(reward);
    }

    /**
     * Checks if a target location is reachable by the player
     * 
     * @param target The target location
     * @return True if the player can reach the target location, false otherwise
     */
    private boolean isReachable(Position target) {
        Position start = new Position(Constants.playerStartX, Constants.playerStartY);
        return maze.isPath(start, target);
    }

    /***************************************************************************
     * 
     * Generate Enemies
     * 
     **************************************************************************/

    /**
     * Generates enemies at random locations
     * 
     * @param num
     */
    private void generateEnemies(int num) {
        for (int i = 0; i < num; i++) {
            generateEnemy();
        }
    }

    /**
     * Generates an enemy at a random location in a subset of the maze and adds it
     * to the maze and enemy list
     */
    private void generateEnemy() {
        // want to generate zombies in a random position in the other half of the screen
        // from the player
        int width = Constants.mazeWidth;
        int height = Constants.mazeHeight;

        Position position = findEmptyPosition(width / 2, width, height / 2, height);

        // Checks if there is an enemy
        while (getEnemy(position) != null) {
            position = findEmptyPosition(width / 2, width, height / 2, height);
        }

        CharacterModel enemy = new CharacterModel(position);
        addEnemy(enemy);
    }

    // =========================================================================
    // #endregion

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
    /**
     * Adds a enemy to the list of enemies
     * 
     * @param enemy The enemy we want to add
     */
    private void addEnemy(CharacterModel enemy) {
        if (enemy == null)
            return;

        enemies.add(enemy);
    }

    /**
     * Removes an enemy from the list of enemies
     * 
     * @param enemy the enemy that we want to remove from the list
     */
    private void removeEnemy(CharacterModel enemy) {
        if (enemy == null)
            return;

        enemies.remove(enemy);
    }

    /**
     * Adds a reward to the list of rewards
     * 
     * @param reward The reward we want to add
     */
    private void addReward(Reward reward) {
        if (reward == null)
            return;

        rewards.add(reward);
    }

    /**
     * Removes a reward from the list of rewards
     * 
     * @param reward the reward we want to remove
     */
    private void removeReward(Reward reward) {
        if (reward == null)
            return;

        rewards.remove(reward);
    }

    /**
     * Adds a trap to the list of traps
     * 
     * @param trap The trap we want to add
     */
    private void addTrap(Trap trap) {
        if (trap == null)
            return;

        traps.add(trap);
    }

    /**
     * Removes a trap from the list of traps
     * 
     * @param trap the trap we want to remove
     */
    public void removeTrap(Trap trap) {
        if (trap == null)
            return;

        traps.remove(trap);
    }

    /**
     * Removes all enemies from the list of enemies
     */
    private void clearEnemies() {
        enemies.clear();
    }

    /**
     * Removes all traps from the list of traps
     */
    private void clearTraps() {
        traps.clear();
    }

    /**
     * Removes all rewards from the list of rewards
     */
    private void clearRewards() {
        rewards.clear();
    }

    /**
     * Removes all entities from the game. Entities are traps, enemies and rewards
     */
    private void clearAllEntities() {
        clearEnemies();
        clearTraps();
        clearRewards();
    }

    // =========================================================================
    // #endregion

}
