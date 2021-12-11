package leaderboard;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeaderboardTest {
    @Test
    public void getInstance() {
        Leaderboard instance1 = Leaderboard.getInstance();
        // testing if the instance is not null
        assertNotNull(instance1);
        Leaderboard instance2 = Leaderboard.getInstance();
        // testing if the two instances point to a singleton object
        assertSame(instance1, instance2);
    }

    @Test
    public void addPlayerScore() {
        Leaderboard l = Leaderboard.Clone();
        // testing adding playerScores to the empty leaderboard
        l.addPlayerScore(new PlayerScore("Jeff", 200));
        assertEquals("Jeff#200\n", l.toString());
        // testing adding existing playerScore to the leaderboard
        l.addPlayerScore(new PlayerScore("Jeff", 200));
        assertEquals("Jeff#200\n", l.toString());
        // testing adding playerScores until the leaderboard capacity(5 playerScores) is
        // full
        l.addPlayerScore(new PlayerScore("Deff", 201));
        l.addPlayerScore(new PlayerScore("Zeff", 202));
        l.addPlayerScore(new PlayerScore("Meff", 203));
        l.addPlayerScore(new PlayerScore("Leff", 204));
        assertEquals("Leff#204\nMeff#203\nZeff#202\nDeff#201\nJeff#200\n", l.toString());
        // adding new High Score to full leaderboard(high enough)
        l.addPlayerScore(new PlayerScore("Teff", 205));
        assertEquals("Teff#205\nLeff#204\nMeff#203\nZeff#202\nDeff#201\n", l.toString());
        // adding new High Score to full leaderboard(not high enough)
        l.addPlayerScore(new PlayerScore("Feff", 200));
        assertEquals("Teff#205\nLeff#204\nMeff#203\nZeff#202\nDeff#201\n", l.toString());
    }

    @Test
    public void getPlayerScore() {
        Leaderboard l = Leaderboard.Clone();
        l.addPlayerScore(new PlayerScore("Jeff", 200));
        assertEquals("Jeff#200", l.getPlayerScore(0).toString());
    }

    @Test
    public void testToString() {
        Leaderboard l = Leaderboard.Clone();
        l.addPlayerScore(new PlayerScore("Jeff", 200));
        l.addPlayerScore(new PlayerScore("Deff", 300));
        assertEquals("Deff#300\nJeff#200\n", l.toString());
    }

    @Test
    public void getLeaderboardSize() {
        Leaderboard l = Leaderboard.Clone();
        // testing after adding playerScores to the empty leaderboard
        l.addPlayerScore(new PlayerScore("Jeff", 200));
        assertEquals(1, l.getLeaderboardSize());
        // testing after adding existing playerScore to the leaderboard
        l.addPlayerScore(new PlayerScore("Jeff", 200));
        assertEquals(1, l.getLeaderboardSize());
        // testing after adding playerScores until the leaderboard capacity(5
        // playerScores) is full
        l.addPlayerScore(new PlayerScore("Deff", 201));
        l.addPlayerScore(new PlayerScore("Zeff", 202));
        l.addPlayerScore(new PlayerScore("Meff", 203));
        l.addPlayerScore(new PlayerScore("Leff", 204));
        assertEquals(5, l.getLeaderboardSize());
        // testing after adding new High Score to full leaderboard(high enough)
        l.addPlayerScore(new PlayerScore("Teff", 205));
        assertEquals(5, l.getLeaderboardSize());
        // testing after adding new High Score to full leaderboard(not high enough)
        l.addPlayerScore(new PlayerScore("Feff", 200));
        assertEquals(5, l.getLeaderboardSize());
    }

    @Test
    public void getMinimumScore() {
        Leaderboard l = Leaderboard.Clone();
        // testing after adding playerScores to the empty leaderboard
        l.addPlayerScore(new PlayerScore("Jeff", 200));
        assertEquals(200, l.getMinimumScore());
        // testing after adding playerScores until the leaderboard capacity(5
        // playerScores) is full
        l.addPlayerScore(new PlayerScore("Deff", 201));
        l.addPlayerScore(new PlayerScore("Zeff", 202));
        l.addPlayerScore(new PlayerScore("Meff", 203));
        l.addPlayerScore(new PlayerScore("Leff", 204));
        assertEquals(200, l.getMinimumScore());
        // adding after new High Score to full leaderboard(high enough)
        l.addPlayerScore(new PlayerScore("Teff", 205));
        assertEquals(201, l.getMinimumScore());
        // adding after new High Score to full leaderboard(not high enough)
        l.addPlayerScore(new PlayerScore("Feff", 200));
        assertEquals(201, l.getMinimumScore());
    }
}
