package reward;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.Position;

import static org.junit.jupiter.api.Assertions.*;

class BonusRewardTest {
    private BonusReward bonusReward;

    @BeforeEach
    void setUp() {
        bonusReward = new BonusReward(new Position(1, 1), 2, 10);
    }

    @Test
    void getEndTime() {
        long result = bonusReward.getEndTime();
        assertEquals(10, result);
    }

    @Test
    void setEndTime() {
        bonusReward.setEndTime(20);
        long result = bonusReward.getEndTime();
        assertEquals(20, result);
    }
}