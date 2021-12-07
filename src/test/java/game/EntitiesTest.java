package game;

import maze.Maze;
import org.junit.Test;

import java.util.ArrayList;

import character.Enemy;
import reward.*;
import utilities.Position;

import static org.junit.Assert.*;

public class EntitiesTest {
    private final Entities entities = Entities.getInstance();
    final Position testPos = new Position(10, 10);
    final Position testPos2 = new Position(20, 20);

    @Test
    public void getInstance() {
        Entities entities2 = Entities.getInstance();
        Position testPos = new Position(10, 10);
        Reward reward = new Reward(testPos);
        entities.addReward(reward);
        assertEquals(entities2, entities);
    }

    @Test
    public void getRewards() {
        entities.clear();
        ArrayList<Reward> rewards = entities.getRewards();
        assertNotNull(rewards);
    }

    @Test
    public void getEnemies() {
        entities.clear();
        ArrayList<Enemy> enemies = entities.getEnemies();
        assertNotNull(enemies);
    }

    @Test
    public void getTraps() {
        entities.clear();
        ArrayList<Trap> traps = entities.getTraps();
        assertNotNull(traps);
    }

    @Test
    public void getReward() {
        entities.clear();
        //with null position
        Reward outcome = entities.getReward(null);
        assertNull(outcome);

        //reward exists at given locationS
        Reward reward = new Reward(testPos);
        entities.addReward(reward);
        Reward outcome2 = entities.getReward(testPos);
        assertEquals(reward, outcome2);

        //testing getReward with index parameter
        Reward result = entities.getReward(0);
        assertEquals(reward, result);

        //reward doesn't exist at given location
        Reward outcome3 = entities.getReward(testPos2);
        assertNull(outcome3);
    }

    @Test
    public void getTrap() {
        entities.clear();
        //with null position
        Trap outcome = entities.getTrap(null);
        assertNull(outcome);

        //trap exists at given location
        Trap trap = new Trap(testPos, TrapType.BOOBYTRAP);
        entities.addTrap(trap);
        Trap outcome2 = entities.getTrap(testPos);
        assertEquals(trap, outcome2);

        //testing getTrap with index parameter
        Trap result = entities.getTrap(0);
        assertEquals(trap, result);

        //trap doesn't exist at given location
        Trap outcome3 = entities.getTrap(testPos2);
        assertNull(outcome3);
    }

    @Test
    public void getEnemy() {
        Maze maze = Maze.getInstance();
        maze.regenerateMaze();
        entities.clear();
        //with null position
        Enemy outcome = entities.getEnemy(null);
        assertNull(outcome);

        //enemy exists at given location
        Enemy enemy = new Enemy(testPos);
        entities.addEnemy(enemy);
        Enemy outcome2 = entities.getEnemy(testPos);
        assertEquals(enemy, outcome2);

        //testing getEnemy with index parameter
        Enemy result = entities.getEnemy(0);
        assertEquals(enemy, result);

        //enemy doesn't exist at given location
        Enemy outcome3 = entities.getEnemy(testPos2);
        assertNull(outcome3);

    }

    @Test
    public void containsEnemy() {
        Maze maze = Maze.getInstance();
        maze.regenerateMaze();
        entities.clear();
        //with null position
        boolean outcome = entities.containsEnemy(null);
        assertFalse(outcome);

        //with enemy at position
        Enemy enemy = new Enemy(testPos);
        entities.addEnemy(enemy);
        boolean outcome2 = entities.containsEnemy(testPos);
        assertTrue(outcome2);

        //with no enemy at position
        boolean outcome3 = entities.containsEnemy(testPos2);
        assertFalse(outcome3);
    }

    @Test
    public void containsReward() {
        entities.clear();
        //with null position
        boolean outcome = entities.containsReward(null);
        assertFalse(outcome);

        //with enemy at position
        Reward reward = new Reward(testPos);
        entities.addReward(reward);
        boolean outcome2 = entities.containsReward(testPos);
        assertTrue(outcome2);

        //with no enemy at position
        boolean outcome3 = entities.containsReward(testPos2);
        assertFalse(outcome3);
    }

    @Test
    public void containsTrap() {
        entities.clear();
        //with null position
        boolean outcome = entities.containsTrap(null);
        assertFalse(outcome);

        //with enemy at position
        Trap trap = new Trap(testPos, TrapType.TRAPFALL);
        entities.addTrap(trap);
        boolean outcome2 = entities.containsTrap(testPos);
        assertTrue(outcome2);

        //with no enemy at position
        boolean outcome3 = entities.containsTrap(testPos2);
        assertFalse(outcome3);
    }

    @Test
    public void getRewardCount() {
        entities.clear();
        //with no rewards
        int count = entities.getRewardCount();
        assertEquals(0, count);

        //with rewards
        Reward reward = new Reward(testPos);
        entities.addReward(reward);
        int count2 = entities.getRewardCount();
        assertEquals(1, count2);
    }

    @Test
    public void getNumBonusRewards() {
        entities.clear();
        //with no rewards
        int count = entities.getNumBonusRewards();
        assertEquals(0, count);

        //with rewards
        entities.setNumBonusRewards(1);
        int count2 = entities.getNumBonusRewards();
        assertEquals(1, count2);
    }

    @Test
    public void setNumBonusRewards() {
        entities.clear();

        entities.setNumBonusRewards(1);
        int count = entities.getNumBonusRewards();
        assertEquals(1, count);
    }

    @Test
    public void updateBonusRewardsCollected() {
        entities.clear();
        //with no bonus rewards collected
        entities.updateBonusRewardsCollected(1);
        int count = entities.getBonusRewardsCollected();
        assertEquals(1, count);


        //with bonus rewards collected
        entities.updateBonusRewardsCollected(1);
        int count2 = entities.getBonusRewardsCollected();
        assertEquals(2, count2);

    }

    @Test
    public void getBonusRewardsCollected() {
        entities.clear();
        //with no bonus rewards collected
        int count = entities.getBonusRewardsCollected();
        assertEquals(0, count);

        //with bonus rewards collected
        entities.updateBonusRewardsCollected(1);
        int count2 = entities.getBonusRewardsCollected();
        assertEquals(1, count2);
    }

    @Test
    public void addEnemy() {
        Maze maze = Maze.getInstance();
        maze.regenerateMaze();
        entities.clear();
        //with null enemy
        entities.addEnemy(null);
        assertTrue(entities.getEnemies().isEmpty());

        //with null position
        Enemy enemy = new Enemy(null);
        entities.addEnemy(enemy);
        Enemy outcome = entities.getEnemy(0);
        assertEquals(enemy, outcome);

        //with position
        Enemy enemy1 = new Enemy(testPos);
        entities.addEnemy(enemy1);
        Enemy outcome2 = entities.getEnemy(1);
        assertEquals(enemy1, outcome2);
    }

    @Test
    public void addReward() {
        entities.clear();
        //with null reward
        entities.addReward(null);
        assertTrue(entities.getRewards().isEmpty());

        //with null position
        Reward reward = new Reward(null);
        entities.addReward(reward);
        Reward outcome = entities.getReward(0);
        assertEquals(reward, outcome);

        //with position
        Reward reward1 = new Reward(testPos);
        entities.addReward(reward1);
        Reward outcome2 = entities.getReward(1);
        assertEquals(reward1, outcome2);
    }

    @Test
    public void addTrap() {
        entities.clear();
        //with null trap
        entities.addTrap(null);
        assertTrue(entities.getTraps().isEmpty());

        //with null position
        Trap trap = new Trap(null, TrapType.BOOBYTRAP);
        entities.addTrap(trap);
        Trap outcome = entities.getTrap(0);
        assertEquals(trap, outcome);

        //with position
        Trap trap1 = new Trap(testPos, TrapType.BOOBYTRAP);
        entities.addTrap(trap1);
        Trap outcome2 = entities.getTrap(1);
        assertEquals(trap1, outcome2);
    }

    @Test
    public void removeReward() {
        entities.clear();

        Reward reward = new Reward(testPos);
        Reward reward1 = new Reward(testPos2);
        entities.addReward(reward);
        //with null reward
        entities.removeReward(null);
        assertFalse(entities.getRewards().isEmpty());

        //with single reward
        entities.removeReward(reward);
        assertTrue(entities.getRewards().isEmpty());

        //with reward>1
        entities.addReward(reward);
        entities.addReward(reward1);
        entities.removeReward(reward);
        assertFalse(entities.containsReward(testPos));
    }

    @Test
    public void removeTrap() {
        entities.clear();

        Trap trap = new Trap(testPos, TrapType.TRAPFALL);
        Trap trap1 = new Trap(testPos2, TrapType.BOOBYTRAP);
        entities.addTrap(trap);
        //with null trap
        entities.removeTrap(null);
        assertFalse(entities.getTraps().isEmpty());

        //with single trap
        entities.removeTrap(trap);
        assertTrue(entities.getTraps().isEmpty());

        //with reward>1
        entities.addTrap(trap);
        entities.addTrap(trap1);
        entities.removeTrap(trap);
        assertFalse(entities.containsTrap(testPos));
    }

    @Test
    public void clear() {
        entities.clear();
        assertTrue(entities.getEnemies().isEmpty());
        assertTrue(entities.getRewards().isEmpty());
        assertTrue(entities.getTraps().isEmpty());
        assertEquals(0, entities.getNumBonusRewards());
        assertEquals(0, entities.getBonusRewardsCollected());
    }

}
