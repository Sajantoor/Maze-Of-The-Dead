package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimerTest {
    private Timer timer = null;

    @BeforeEach
    void setup() {
        timer = Timer.getInstance();
    }

    @Test
    void getInstance() {
        timer = Timer.getInstance();
        assertNotNull(timer);
    }

    @Test
    void getTimeElapsed() {
        assertEquals(0, timer.getTimeElapsed());
    }

    @Test
    void updateTime() {
        timer.updateTime();
        assertEquals(1, timer.getTimeElapsed());
    }

    @Test
    void reset() {
        timer.reset();
        assertEquals(0, timer.getTimeElapsed());
    }
}