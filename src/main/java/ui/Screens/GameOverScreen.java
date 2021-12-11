package ui.Screens;

import ui.components.PanelWithBackgroundImage;

import javax.swing.*;
import java.awt.*;

import static ui.components.UIUtils.*;
import static ui.components.Buttons.*;
import static ui.components.Elements.*;

/**
 * Represent the "Game Over" screen
 *
 * @author Kaung Si Thu
 */
public class GameOverScreen extends PanelWithBackgroundImage {
    /**
     * Represents the "Game Over" screen
     *
     * @param score             The score the player accumulates
     * @param timeInSeconds     The time the player spent in the game
     * @param numOfBonusRewards The number of bonus rewards collected in the game.
     * @param image             The background image for the screen
     */
    public GameOverScreen(int score, long timeInSeconds, int numOfBonusRewards, Image image) {
        super(image);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addSpace(this, 0, 60);

        // Game Over Text
        addTitle(this, "Game Over");
        addSpace(this, 0, 60);

        // Score Text
        addScoreLabel(this, score);
        addSpace(this, 0, 60);

        // Time Text
        addTimeLabel(this, timeInSeconds);
        addSpace(this, 0, 60);

        // Reward Panel
        addRewardPanel(this);
        addSpace(this, 0, 60);

        // Bonus Reward Panel
        addBonusRewardPanel(this, numOfBonusRewards);
        addSpace(this, 0, 60);

        // Play Again Button
        addPlayAgainButton(this, "Play Again");
        addSpace(this, 0, 60);

        // Quit Button
        addQuitButton(this, "Quit");
    }
}
