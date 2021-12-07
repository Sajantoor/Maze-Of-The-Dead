package game;

import maze.Maze;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        // testing if the instance is not null
        assertNotNull(EG);
        EntitiesGenerator EG2 = EntitiesGenerator.getInstance();
        // testing if the two instances point to the same singleton object
        assertSame(EG, EG2);
    }

    @Test
    void generateEntities() {
        // testing if there is no entities before generating entities
        assertTrue(entities.getRewards().isEmpty());
        assertTrue(entities.getTraps().isEmpty());
        assertTrue(entities.getEnemies().isEmpty());
        EG.generateEntities();
        // testing if there are entities after generating entities
        assertFalse(entities.getRewards().isEmpty());
        assertFalse(entities.getTraps().isEmpty());
        assertFalse(entities.getEnemies().isEmpty());
    }

    @Test
    void generateBonusReward() {
        // Before generating bonus rewards
        assertEquals(0, entities.getNumBonusRewards());
        EG.generateBonusReward();
        // After generating bonus rewards
        assertNotEquals(0, entities.getNumBonusRewards());
    }
}