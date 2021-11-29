package character;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player player = null;
    private int testScore = 10;

    @Test
    public void getInstance() {
        player = Player.getInstance();
        assertNotNull(player);
    }

    @Test
    public void setScore() {
        player = Player.getInstance();
        player.setScore(testScore);
        int score = player.getScore();
        assertEquals(testScore, score);
    }

    @Test
    public void getScore() {
        player = Player.getInstance();
        int score = player.getScore();
        assertEquals(testScore, score);
    }

    @Test
    public void updateScore() {
        //with score = 0 initially
        player = Player.getInstance();
        player.updateScore(testScore);
        int score = player.getScore();
        assertEquals(testScore, score);

        //with score>0 initially
        int result = 20;
        player.updateScore(testScore);
        int score2 = player.getScore();
        assertEquals(result, score2);
    }


}
