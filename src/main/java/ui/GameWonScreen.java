package ui;
import javax.swing.*;
import static ui.components.Buttons.*;
import static ui.components.Elements.*;

import static ui.UIUtils.*;

/**
 * Creates a "Game Won" screen after they've won the game
 * Takes the player to the Leaderboard or the NewHighScore screen depending upon score
 *
 * @author Maisha Supritee Chowdhury
 */

public class GameWonScreen {
    private JPanel gameWonScreenPanel;

    /**
     * returns the GameWonScreen
     *
     * @return the GameWonScreen panel
     */
    public JPanel getGameWonScreen(int score, long timeInSeconds) {
        gameWonScreenPanel = new PanelWithBackgroundImage(new ImageIcon("src/main/java/ui/images/background.jpg").getImage());
        gameWonScreenPanel.setLayout(new BoxLayout(gameWonScreenPanel, BoxLayout.PAGE_AXIS));
        addSpace(gameWonScreenPanel, 200, 50);

        //You Escaped Text
        addEscapedLabel(gameWonScreenPanel, "You Escaped!");
        addSpace(gameWonScreenPanel, 0, 60);

        //Score Text
        addScoreLabel(gameWonScreenPanel, score);
        addSpace(gameWonScreenPanel, 0, 60);

        //Time Text
        addTimeLabel(gameWonScreenPanel, timeInSeconds);
        addSpace(gameWonScreenPanel, 0, 60);

        //Quit Button
        addContinueButton(gameWonScreenPanel,"Continue", score);
        return gameWonScreenPanel;
    }
}