package ui;

import javax.swing.*;
import static ui.UIUtils.*;
import static ui.components.Buttons.*;
import static ui.components.Elements.*;

/**
 * Represent the "Game Over" screen
 *
 * @author Kaung Si Thu
 */
public class GameOverScreen {
    private JPanel gameOverScreenPanel;

    /**
     * Return the "Game Over" screen
     *
     * @return the "Game Over" screen
     */
    public JPanel getGameOverScreen(int score, long timeInSeconds, int numOfRewards, int numOfBonusRewards) {
        gameOverScreenPanel = new JPanel();
        gameOverScreenPanel.setLayout(new BoxLayout(gameOverScreenPanel, BoxLayout.PAGE_AXIS));
        addSpace(gameOverScreenPanel, 0, 60);

        //Game Over Text
        addTitle(gameOverScreenPanel, "Game Over");
        addSpace(gameOverScreenPanel, 0, 60);

        //Score Text
        addScoreLabel(gameOverScreenPanel, score);
        addSpace(gameOverScreenPanel, 0, 60);

        //Time Text
        addTimeLabel(gameOverScreenPanel, timeInSeconds);
        addSpace(gameOverScreenPanel, 0, 60);

        //Reward Panel
        addRewardPanel(gameOverScreenPanel, numOfRewards);
        addSpace(gameOverScreenPanel, 0, 60);

        //Bonus Reward Panel
        addBonusRewardPanel(gameOverScreenPanel, numOfBonusRewards);
        addSpace(gameOverScreenPanel, 0, 60);

        //Play Again Button
        addPlayButton(gameOverScreenPanel,"Play Again");
        addSpace(gameOverScreenPanel, 0, 60);

        //Quit Button
        addQuitButton(gameOverScreenPanel, "Quit");
        return gameOverScreenPanel;
    }
}

