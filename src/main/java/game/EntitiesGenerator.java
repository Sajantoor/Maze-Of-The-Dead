package game;

import character.Enemy;
import maze.Cell;
import maze.Maze;
import reward.BonusReward;
import reward.Reward;
import reward.RewardType;
import reward.Trap;
import reward.TrapType;
import utilities.Constants;
import utilities.Functions;
import utilities.Position;

/**
 * This class is used to generate the entities of the game (enemies, traps,
 * rewards) in maze randomly.
 * 
 * @author Sajan Toor
 */
public class EntitiesGenerator {
    private static EntitiesGenerator instance = null;

    private EntitiesGenerator() {
        instance = this;
    }

    /**
     * This method returns the instance of EntityGenerator (Singleton).
     * 
     * @return instance of EntityGenerator
     */
    public static EntitiesGenerator getInstance() {
        if (instance == null)
            new EntitiesGenerator();

        return instance;
    }

    /**
     * Generates all entities in the game, such as traps, rewards, and enemies and
     * adds them to the maze
     * 
     */
    public void generateEntities() {
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
            Maze maze = Maze.getInstance();
            cell = maze.getCell(x, y);
        } while (!cell.isPath());

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

        Maze maze = Maze.getInstance();
        Cell cell = maze.getCell(position);
        cell.setTrap();
        Trap trap = new Trap(position, trapType);
        Entities entities = Entities.getInstance();
        entities.addTrap(trap);
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
        Maze maze = Maze.getInstance();
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
     * Generates a bonus reward
     */
    protected void generateBonusReward() {
        generateReward(RewardType.BONUS);
        int oldNumBonusRewards = Entities.getInstance().getNumBonusRewards();
        Entities.getInstance().setNumBonusRewards(oldNumBonusRewards + 1);
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

        Maze maze = Maze.getInstance();
        Cell cell = maze.getCell(position);
        cell.setReward();

        Reward reward = null;
        switch (rewardType) {
            case REGULAR:
                reward = new Reward(position);
                break;
            case BONUS:
                long timeElapsed = Timer.getInstance().getTimeElapsed();
                // pick a random time between lower and upper bounds
                long lower = timeElapsed + Constants.bonusRewardTimeLower;
                long upper = timeElapsed + Constants.bonusRewardTimeUpper;
                long endTime = Functions.getRandomNumber(lower, upper);
                reward = new BonusReward(position, Constants.bonusRewardPoints, endTime);
            default:
                break;
        }

        if (reward != null) {
            Entities.getInstance().addReward(reward);
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
        return Maze.getInstance().isRoute(start, target);
    }

    /***************************************************************************
     * 
     * Generate Enemies
     * 
     **************************************************************************/

    /**
     * Generates enemies at random locations
     * 
     * @param num the number of enemies we want to generate
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
        } while (Entities.getInstance().getEnemy(position) != null);

        Enemy enemy = new Enemy(position);
        Entities.getInstance().addEnemy(enemy);
    }
}
