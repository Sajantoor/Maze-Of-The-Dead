package leaderboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerScoreTest {

    private PlayerScore ps;

    @BeforeEach
    void setUp() {
        ps = new PlayerScore("Randam Naim", 200);
    }

    @org.junit.jupiter.api.Test
    void getName() {
        assertEquals("Randam Naim", ps.getName());
    }

    @org.junit.jupiter.api.Test
    void setName() {
        ps.setName("Anada Randam Naim");
        assertEquals("Anada Randam Naim", ps.getName());
    }

    @org.junit.jupiter.api.Test
    void getScore() {
        assertEquals(200, ps.getScore());
    }

    @org.junit.jupiter.api.Test
    void setScore() {
        ps.setScore(250);
        assertEquals(250, ps.getScore());
    }

    @Test
    void testToString() {
        assertEquals("Randam Naim#200", ps.toString());
    }
}