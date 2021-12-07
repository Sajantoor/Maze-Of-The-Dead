package character;

import org.junit.Test;
import utilities.Constants;
import utilities.Position;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player player = Player.getInstance();
    private final int testScore = 10;

    @Test
    public void getInstance() {
        Player player2 = Player.getInstance();
        player.setScore(10);
        assertEquals(player2, player);
    }

    @Test
    public void setScore() {
        player.reset();
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
        // with score = 0 initially
        player.reset();
        player.updateScore(testScore);
        int score = player.getScore();
        assertEquals(testScore, score);

        // with score>0 initially
        int result = 20;
        player.updateScore(testScore);
        int score2 = player.getScore();
        assertEquals(result, score2);
    }

    @Test
    public void reset() {
        player.reset();
        Position newPos = new Position(Constants.playerStartX, Constants.playerStartY);
        int score = 0;
        assertEquals(newPos, player.getPosition());
        assertEquals(score, player.getScore());
    }

}
