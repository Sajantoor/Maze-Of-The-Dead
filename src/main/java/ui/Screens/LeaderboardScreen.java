package ui.Screens;

import leaderboard.Leaderboard;
import leaderboard.PlayerScore;
import ui.components.PanelWithBackgroundImage;

import javax.swing.*;

import java.awt.*;

import static ui.components.UIUtils.*;
import static ui.components.Buttons.*;
import static ui.components.Elements.*;

/**
 * Represent the Leaderboard screen
 *
 * @author Kaung Si Thu
 */
public class LeaderboardScreen extends PanelWithBackgroundImage {
    /**
     * Return the Leaderboard screen
     *
     * @param playerScore New High Score of the player
     * @param image       The background image for the screen
     */
    public LeaderboardScreen(PlayerScore playerScore, Image image) {
        super(image);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addSpace(this, 0, 100);

        // update Leaderboard
        Leaderboard leaderboard = Leaderboard.getInstance();
        leaderboard.addPlayerScore(playerScore);

        // Title
        addTitle(this, "You Escaped!");
        addSpace(this, 0, 60);

        // LeaderBoard Title
        addSmallTitle(this, "Leaderboard");
        addSpace(this, 0, 60);

        // LeaderBoard Table
        addLeaderBoard(this, leaderboard);
        addSpace(this, 0, 60);

        // Play Again Button
        addPlayAgainButton(this, "Play Again");
        addSpace(this, 0, 60);

        // Quit Button
        addQuitButton(this, "Quit");
    }
}
