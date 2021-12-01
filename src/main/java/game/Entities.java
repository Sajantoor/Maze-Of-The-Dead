package game;

import java.util.ArrayList;

import cell.Cell;
import character.Enemy;
import reward.BonusReward;
import reward.Reward;
import reward.Trap;
import utilities.Position;

public class Entities {
    private static Entities instance = null;
    private ArrayList<Reward> rewards;
    // private ArrayList<BonusReward> bonusRewards;
    private ArrayList<Enemy> enemies;
    private ArrayList<Trap> traps;
    private int numBonusRewards;
    private int bonusRewardsCollected;

    private Entities() {
        instance = this;
        rewards = new ArrayList<Reward>();
        enemies = new ArrayList<Enemy>();
        traps = new ArrayList<Trap>();

        numBonusRewards = 0;
        bonusRewardsCollected = 0;
    }

    public static Entities getInstance() {
        if (instance == null)
            new Entities();

        return instance;
    }

    /**
     * Checks if there is a reward at a position and returns it
     * 
     * @return the reward at the position, else null
     */
    public Reward getReward(Position position) {
        if (position == null)
            return null;

        for (Reward reward : rewards) {
            if (reward.getPosition().equals(position)) {
                return reward;
            }
        }

        return null;
    }

    public ArrayList<Reward> getRewards() {
        return rewards;
    }

    /**
     * Checks if there is a trap at a position and returns it
     * 
     * @return the trap at the position, else null
     */
    public Trap getTrap(Position position) {
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
    public Enemy getEnemy(Position position) {
        if (position == null)
            return null;

        for (Enemy enemy : enemies) {
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
    public Enemy getEnemy(int i) {
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
     * 
     * @return the number of rewards in the maze
     */
    public int getRewardCount() {
        return rewards.size();
    }

    /**
     * Gets the number of bonus rewards in the maze
     * 
     * @return the number of bonus rewards in the maze
     */
    public int getNumBonusRewards() {
        return numBonusRewards;
    }

    public void setNumBonusRewards(int num) {
        numBonusRewards = num;
    }

    public void updateBonusRewardsCollected(int num) {
        bonusRewardsCollected += num;
    }

    /**
     * Returns the number of bonus rewards collected
     */
    public int getBonusRewardsCollected() {
        return bonusRewardsCollected;
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
        Enemy enemy = getEnemy(position);
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
    public void addEnemy(Enemy enemy) {
        if (enemy == null)
            return;

        enemies.add(enemy);
    }

    /**
     * Adds a reward to the list of rewards
     * 
     * @param reward The reward we want to add
     */
    public void addReward(Reward reward) {
        if (reward == null)
            return;

        rewards.add(reward);
    }

    /**
     * Removes a reward from the list of rewards
     * 
     * @param reward the reward we want to remove
     */
    public void removeReward(Reward reward) {
        if (reward == null)
            return;

        rewards.remove(reward);
    }

    /**
     * Checks whether any bonus rewards have expired, and removes them if they are
     * expired.
     *
     */
    public void checkBonusRewardExpired() {
        int size = rewards.size();
        for (int i = 0; i < size; i++) {
            Reward reward = rewards.get(i);
            // check if it is a bonus reward and cast it to BonusReward
            if (reward instanceof BonusReward) {
                BonusReward bonusReward = (BonusReward) reward;
                // if the current time is greater than the bonus reward's end time,
                // the bonus reward is expired
                if (bonusReward.getEndTime() <= Timer.getInstance().getTimeElapsed()) {
                    rewards.remove(i);
                    Position position = bonusReward.getPosition();
                    Cell cell = Maze.getInstance().getCell(position);
                    cell.setEmpty();
                }
            }
        }
    }

    /**
     * Generates the enemy movement by looping through all enemies and finding it's
     * next move
     */
    public void generateEnemyMovement() {
        if (!GameController.getInstance().isPaused()) {
            for (Enemy enemy : enemies) {
                enemy.move();
            }
        }
    }

    /**
     * Adds a trap to the list of traps
     * 
     * @param trap The trap we want to add
     */
    public void addTrap(Trap trap) {
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
    public void clearAllEntities() {
        clearEnemies();
        clearTraps();
        clearRewards();
    }
}
