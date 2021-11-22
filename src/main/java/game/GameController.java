package game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import character.Player;
import utilities.Movement;
import character.CharacterModel;
import reward.BonusReward;
import reward.Reward;
import reward.RewardType;
import reward.Trap;
import reward.TrapType;
import cell.Cell;
import utilities.Constants;
import utilities.Functions;
import utilities.Position;

/**
 * This class is the controller of the game. It is in charge of the game logic,
 * handling player input and generation of entities.
 *
 * @author Sajan Toor
 */
public class GameController {
    private static GameController instance = null;
    private Maze maze;
    private ArrayList<CharacterModel> enemies;
    private ArrayList<Reward> rewards;
    private ArrayList<Trap> traps;
    private ArrayList<Movement> moves;
    private Player player;
    private int bonusRewardsCollected = 0;
    private boolean isRunning;
    private boolean hasWon;
    private boolean isPaused;
    private boolean hasCollectedAllRewards = false;
    private long timeElapsed;
    private boolean quit;

    // #region Constructor and Singleton
    // =========================================================================

    private GameController() {
        instance = this;
        moves = new ArrayList<>();
        moves.add(Movement.STATIONARY);
        player = Player.getInstance();
        enemies = new ArrayList<CharacterModel>();
        rewards = new ArrayList<Reward>();
        traps = new ArrayList<Trap>();
        // initialize flags
        isRunning = false;
        isPaused = false;
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

    /**
     * Sets whether or not the game is running
     *
     * @param isRunning True if the game is running, false otherwise
     */
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * Method to start the game. This will geenrate the maze and entities as well as
     * start the threads.
     */
    public void startGame() {
        setRunning(true);
        // Clears all entities and regenerates them
        clearAllEntities();
        moves.add(Movement.STATIONARY);
        player.setPosition(0, 1);
        player.setScore(0);
        maze = Maze.getInstance();
        maze.newMaze(Constants.mazeWidth, Constants.mazeHeight, Constants.mazeRooms);
        generateEntities();
        startThreads();
    }

    /**
     * Starts the threads for the game, including game loop, enemy loop and time
     * loop.
     */
    private void startThreads() {
        Thread gameLoop = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    updateGame();
                    try {
                        Thread.sleep(Constants.gameLoopSleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread enemyLoop = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    generateEnemyMovement();
                    try {
                        Thread.sleep(Constants.enemyLoopSleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread timeLoop = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    updateTime();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        timeLoop.start();
        gameLoop.start();
        enemyLoop.start();
    }

    /**
     * Updates the winning state of the game.
     */
    private void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    /**
     * Checks whether the player has won the game.
     * 
     * @return True if the player has won the game, false otherwise.
     */
    public boolean getHasWon() {
        return hasWon;
    }

    /**
     * Ends the game, setting the running flag to false.
     */
    public void endGame() {
        setRunning(false);
    }

    /**
     * Pauses the game
     */
    public void pauseGame() {
        this.isPaused = true;
    }

    /**
     * Resumes the game
     */
    public void unpauseGame() {
        this.isPaused = false;
    }

    private void winGame() {
        setHasWon(true);
        endGame();
    }

    private void loseGame() {
        setHasWon(false);
        endGame();
    }

    /**
     * Returns the number of bonus rewards collected
     */
    public int getBonusRewardsCollected() {
        return bonusRewardsCollected;
    }

    /**
     * Sets quit to true to end the game
     */
    public void setQuit() {
        quit = true;
    }

    /**
     * Gets the value of the quit state
     * 
     * @return the value of quit state
     */
    public boolean getQuit() {
        return quit;
    }

    /**
     * Returns the value of the isPaused state
     * 
     * @return the value of isPaused state
     */
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * Returns the value of the isRunning state
     * 
     * @return the value of isRunning state
     */
    public boolean getIsRunning() {
        return isRunning;
    }

    /**
     * Returns the player
     * 
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    // =========================================================================
    // #endregion

    // #region Time
    // =========================================================================
    /**
     * This method sets the time (in seconds) elapsed since the start of the game.
     */
    private void updateTime() {
        if (isPaused)
            return;

        timeElapsed++;
        // check if any bonus rewards have expired
        checkBonusRewardExpired();
        // have a random chance of generating a new bonus reward
        if (Functions.getRandomNumber(0, 100) < Constants.bonusRewardChance) {
            generateReward(RewardType.BONUS);
        }
    }

    /**
     * Checks whether any bonus rewards have expired, and removes them if they are
     * expired.
     *
     */
    private void checkBonusRewardExpired() {
        int size = rewards.size();
        for (int i = 0; i < size; i++) {
            Reward reward = rewards.get(i);
            // check if it is a bonus reward and cast it to BonusReward
            if (reward instanceof BonusReward) {
                BonusReward bonusReward = (BonusReward) reward;
                // if the current time is greater than the bonus reward's end time,
                // the bonus reward is expired
                if (bonusReward.getEndTime() <= timeElapsed) {
                    rewards.remove(i);
                    Position position = bonusReward.getPosition();
                    Cell cell = maze.getCell(position);
                    cell.setEmpty();
                }
            }
        }
    }

    /**
     * This method returns the time (in seconds) elapsed since the start of the
     * game.
     * 
     * @return Time (in seconds) elapsed since the start of the game
     */
    public long getTimeElapsed() {
        return timeElapsed;
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
     */
    public void updateGame() {
        hasWon();
        if (isRunning && !isPaused) {
            movePlayer(moves.get(0));
            checkCollidables();
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

    /**
     * Generates the enemy movement by looping through all enemies and finding it's
     * next move
     */
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
        // Open queue to keep track of all possible moves the enemy could move to
        PriorityQueue<Position> openQueue = new PriorityQueue<Position>(100,
                Comparator.comparing(move -> getDistanceFromPlayer(move)));

        Position current = enemy.getPosition();

        // generate successors from the current based off the directions the enemy can
        // move find the position which minimizes the distance to the player
        // then move the enemy to that position
        for (Movement movement : Movement.values()) {
            Position successor = Functions.updatePosition(current, movement);
            if (validateEnemyMove(successor)) {
                // add it to the open queue
                openQueue.add(successor);
            }
        }

        Position winner = openQueue.poll();
        enemy.setPosition(winner);
    }

    /**
     * Checks if the enemy can move in the given direction Checks if there is wall,
     * trap or enemy in the way, therefore cannot move
     * 
     * @param position The position the enemy wants to move to
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

    /**
     * Gets the distance from the player in number of steps to the given position
     * 
     * @param position The current position we want to calculate the distance from
     * @return The distance between the given position and the player
     */
    private int getDistanceFromPlayer(Position position) {
        Position playerPos = Player.getInstance().getPosition();
        return maze.getDistance(position, playerPos);
    }

    // =========================================================================
    // #endregion

    // #region Player input
    // =========================================================================
    /**
     * Move the player in the direction specified by the movement. Checks if there
     * is a collision with a wall and acts accordingly.
     * 
     * @param movement the movement to be performed
     */
    public void movePlayer(Movement movement) {
        // check if there is a wall in the way that would stop movement
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

    /**
     * Adds the given move to the list of moves.
     * 
     * @param move The move to be added
     */
    public void addMovement(Movement move) {
        moves.add(0, move);
    }

    /**
     * Removes the given move from the list of moves.
     * 
     * @param move The move we want to remove
     */
    public void removeMovement(Movement move) {
        moves.remove(move);
    }

    /**
     * Checks if a the list contains a move
     * 
     * @param move The move wae want to check if it is in the list
     * @return True if the move is in the list, false otherwise
     */
    public boolean checkMovement(Movement move) {
        return moves.contains(move);
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
                if (reward instanceof BonusReward) {
                    bonusRewardsCollected += 1;
                }
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
        // regenerate random combinations until we find a cell in an empty location
        Cell cell;

        do {
            int x = Functions.getRandomNumber(startX, width - startX);
            int y = Functions.getRandomNumber(startY, height - startY);
            cell = maze.getCell(x, y);
        } while (!cell.isEmpty());

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
        Position position = null;

        do {
            position = findEmptyPosition();
        } while (!isValidPosition(position));

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
        if (position == null)
            return false;

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
            generateReward(RewardType.REGULAR);
        }
    }

    /**
     * Generates a reward at a random location, checks if the player can reach the
     * reward to validate that the game is winnable, and adds it to the maze and
     * reward list
     */
    private void generateReward(RewardType rewardType) {
        Position position;

        do {
            position = findEmptyPosition();
        } while (!isReachable(position));

        Cell cell = maze.getCell(position);
        cell.setReward();

        Reward reward = null;
        switch (rewardType) {
            case REGULAR:
                reward = new Reward(position);
                break;
            case BONUS:
                long endTime = Functions.getRandomNumber(timeElapsed + Constants.bonusRewardTimeLower,
                        timeElapsed + Constants.bonusRewardTimeUpper);
                reward = new BonusReward(position, endTime);
            default:
                break;
        }

        if (reward != null) {
            addReward(reward);
        }
    }

    /**
     * Checks if a target location is reachable by the player
     * 
     * @param target The target location
     * @return True if the player can reach the target location, false otherwise
     */
    private boolean isReachable(Position target) {
        if (target == null)
            return false;

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

        Position position;

        // Checks if there is an enemy
        do {
            position = findEmptyPosition(width / 4, width, height / 4, height);
        } while (getEnemy(position) != null);

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
        if (position == null)
            return null;

        for (Reward reward : rewards) {
            if (reward.getPosition().equals(position)) {
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
        if (position == null)
            return null;

        for (Trap trap : traps) {
            if (trap.getPosition().equals(position)) {
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
        if (position == null)
            return null;

        for (CharacterModel enemy : enemies) {
            if (enemy.getPosition().equals(position)) {
                return enemy;
            }
        }

        return null;
    }

    /**
     * Gets the enemy at index i
     *
     * @param i index of enemies array
     * @return the enemy at index i
     */
    public CharacterModel getEnemy(int i) {
        return enemies.get(i);
    }

    /**
     * Gets the reward at index i
     *
     * @param i index of rewards array
     * @return the reward at index i
     */
    public Reward getReward(int i) {
        return rewards.get(i);
    }

    /**
     * Gets the trap at index i
     *
     * @param i index of traps array
     * @return the trap at index i
     */
    public Trap getTrap(int i) {
        return traps.get(i);
    }

    /**
     * Gets a reward at an x y coordinate in the maze
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @return The reward if it exists or null
     */
    public Reward getReward(int x, int y) {
        Position position = new Position(x, y);
        return getReward(position);
    }

    /**
     * Gets a trap at an x y coordinate in the maze
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @return The trap if it exists or null
     */
    public Trap getTrap(int x, int y) {
        Position position = new Position(x, y);
        return getTrap(position);
    }

    /**
     * Gets the number of rewards in the maze
     */
    public int getRewardCount() {
        return rewards.size();
    }

    /**
     * Checks if an x y coordinate contains an enemy in the maze
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @return True if the coordinate contains an enemy, false otherwise
     */
    public boolean containsEnemy(int x, int y) {
        Position position = new Position(x, y);
        CharacterModel enemy = getEnemy(position);
        if (enemy != null) {
            return true;
        }

        return false;
    }

    /***
     * Checks if an x y coordinate contains a trap in the maze
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @return True if the coordinate contains a trap, false otherwise
     */
    public boolean containsTrap(int x, int y) {
        Trap trap = getTrap(x, y);
        if (trap != null) {
            return true;
        }

        return false;
    }

    /**
     * Checks if an x y coordinate contains a reward in the maze
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @return True if the coordinate contains a reward, false otherwise
     */
    public boolean containsReward(int x, int y) {
        Reward reward = getReward(x, y);
        if (reward != null) {
            return true;
        }

        return false;
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
        moves.clear();
    }

    // =========================================================================
    // #endregion
}
