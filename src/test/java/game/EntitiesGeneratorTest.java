package game;

import maze.Maze;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reward.Reward;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EntitiesGeneratorTest {
    private maze.Maze m;
    private EntitiesGenerator EG;
    private Entities entities;

    @BeforeEach
    void setUp() {
        m = Maze.getInstance();
        m.regenerateMaze();
        EG = EntitiesGenerator.getInstance();
        entities = Entities.getInstance();
        entities.clear();
    }

    @AfterEach
    void tearDown() {
        entities.clear();
    }

    @Test
    void getInstance() {
        //create two instances and check if they point to the same object
        EntitiesGenerator EG2 = EntitiesGenerator.getInstance();
        assertSame(EG, EG2);
    }

    @Test
    void generateEntities() {
        //Before generating entities
        assertTrue(entities.getRewards().isEmpty());
        assertTrue(entities.getTraps().isEmpty());
        assertTrue(entities.getEnemies().isEmpty());
        EG.generateEntities();
        //After generating entities
        assertFalse(entities.getRewards().isEmpty());
        assertFalse(entities.getTraps().isEmpty());
        assertFalse(entities.getEnemies().isEmpty());
    }

    @Test
    void generateBonusReward() {
        //Before generating bonus rewards
        assertTrue(entities.getNumBonusRewards()==0);
        EG.generateBonusReward();
        //After generating bonus rewards
        assertFalse(entities.getNumBonusRewards()==0);
    }
}