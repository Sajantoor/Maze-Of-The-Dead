package reward;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RewardTest {
    private Reward reward;

    @BeforeEach
    void setUp() {
        reward = new Reward(new Position(1, 1), 10);
    }

    @Test
    void getPosition() {
        Position result = reward.getPosition();
        Position expect = new Position(1, 1);
        assertEquals(expect, result);
    }

    @Test
    void setPosition() {
        Position newPos = new Position(2, 2);
        reward.setPosition(newPos);
        Position result = reward.getPosition();
        assertEquals(new Position(2, 2), result);
    }

    @Test
    void getPoints() {
        int result = reward.getPoints();
        assertEquals(10, result);
    }

    @Test
    void setPoints() {
        reward.setPoints(20);
        int result = reward.getPoints();
        assertEquals(20, result);
    }
}