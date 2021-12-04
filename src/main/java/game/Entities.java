package game;

import java.util.ArrayList;

import character.Enemy;
import reward.Reward;
import reward.Trap;
import utilities.Position;

/**
 * This class contains all the entities for the game generated in
 * EntitiesGenerator. It provides public read methods to get the entities, all
 * writing (besides clearing all entities) is protected and can only be done
 * within game package.
 *
 * @author Sajan Toor
 * @see EntitiesGenerator
 */
public class Entities {
    private static Entities instance = null;
    private final ArrayList<Reward> rewards;
    private final ArrayList<Enemy> enemies;
    private final ArrayList<Trap> traps;
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

    /**
     * This method returns the instance of GameInput (Singleton).
     *
     * @return instance of GameInput
     */
    public static Entities getInstance() {
        if (instance == null)
            new Entities();

        return instance;
    }

    // #region Getting entities
    // =========================================================================

    /**
     * Returns all rewards
     *
     * @return an arrayList of all rewards
     */
    public ArrayList<Reward> getRewards() {
        return rewards;
    }

    /**
     * Checks if there is a reward at a position and returns it
     *
     * @param position position to check
     * @return the reward at the position, else null
     */
    public Reward getReward(Position position) {
        if (position == null)
            return null;

        for (int i = 0; i < rewards.size(); i++) {
            Reward reward = rewards.get(i);

            if (reward == null)
                continue;

            if (reward.getPosition().equals(position)) {
                return reward;
            }
        }

        return null;
    }

    /**
     * Returns all traps
     *
     * @return an arrayList of all traps
     */
    public ArrayList<Trap> getTraps() {
        return traps;
    }

    /**
     * Checks if there is a trap at a position and returns it
     *
     * @param position position to check
     * @return the trap at the position, else null
     */
    public Trap getTrap(Position position) {
        if (position == null)
            return null;

        for (int i = 0; i < traps.size(); i++) {
            Trap trap = traps.get(i);

            if (trap == null)
                continue;

            if (trap.getPosition().equals(position)) {
                return trap;
            }
        }

        return null;
    }

    /**
     * Returns all enemies
     *
     * @return an arrayList of all enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Checks if there is an enemy at a position and returns it
     *
     * @param position position to check
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

    // =========================================================================
    // #endregion

    // #region Contains entities
    // =========================================================================

    /**
     * Checks if an position contains an enemy in the maze
     *
     * @param position The position we want to check for an enemy
     * @return True if the coordinate contains an enemy, false otherwise
     */
    public boolean containsEnemy(Position position) {
        Enemy enemy = getEnemy(position);
        return enemy != null;
    }

    /***
     * Checks if a position contains a trap in the maze
     *
     * @param position The position we want to check for a trap
     * @return True if the coordinate contains a trap, false otherwise
     */
    public boolean containsTrap(Position position) {
        Trap trap = getTrap(position);
        return trap != null;
    }

    /**
     * Checks if a position contains a reward in the maze
     *
     * @param position The position we want to check for a reward
     * @return True if the coordinate contains a reward, false otherwise
     */
    public boolean containsReward(Position position) {
        Reward reward = getReward(position);
        return reward != null;
    }

    // =========================================================================
    // #endregion

    // #region Counts of entities
    // =========================================================================

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

    /**
     * Sets the number of bonus rewards in the maze
     *
     * @param num the number of bonus rewards in the maze
     */
    protected void setNumBonusRewards(int num) {
        numBonusRewards = num;
    }

    /**
     * Updates the number of bonus rewards collected by num
     *
     * @param num The update to number of bonus rewards collected
     */
    protected void updateBonusRewardsCollected(int num) {
        bonusRewardsCollected += num;
    }

    /**
     * Returns the number of bonus rewards collected
     *
     * @return the number of bonus rewards collected
     */
    public int getBonusRewardsCollected() {
        return bonusRewardsCollected;
    }

    // =========================================================================
    // #endregion

    // #region Adding and removing entities
    // =========================================================================

    /**
     * Adds an enemy to the list of enemies
     *
     * @param enemy The enemy we want to add
     */
    protected void addEnemy(Enemy enemy) {
        if (enemy == null)
            return;

        enemies.add(enemy);
    }

    /**
     * Adds a reward to the list of rewards
     *
     * @param reward The reward we want to add
     */
    protected void addReward(Reward reward) {
        if (reward == null)
            return;

        rewards.add(reward);
    }

    /**
     * Removes a reward from the list of rewards
     *
     * @param reward the reward we want to remove
     */
    protected void removeReward(Reward reward) {
        if (reward == null)
            return;

        rewards.remove(reward);
    }

    /**
     * Adds a trap to the list of traps
     *
     * @param trap The trap we want to add
     */
    protected void addTrap(Trap trap) {
        if (trap == null)
            return;

        traps.add(trap);
    }

    /**
     * Removes a trap from the list of traps
     *
     * @param trap the trap we want to remove
     */
    protected void removeTrap(Trap trap) {
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
    public void clear() {
        clearEnemies();
        clearTraps();
        clearRewards();
        numBonusRewards = 0;
        bonusRewardsCollected = 0;
    }

    // =========================================================================
    // #endregion
}
