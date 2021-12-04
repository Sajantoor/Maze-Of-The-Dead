package ui.Screens;

import ui.components.PanelWithBackgroundImage;

import javax.swing.*;
import java.awt.*;

import static ui.components.Buttons.*;
import static ui.components.Elements.*;

import static ui.components.UIUtils.*;

/**
 * Creates a "Game Won" screen after they've won the game
 * Takes the player to the Leaderboard or the NewHighScore screen depending upon
 * score
 *
 * @author Maisha Supritee Chowdhury
 */

public class GameWonScreen extends PanelWithBackgroundImage {
    /**
     * Represents the GameWonScreen
     *
     * @param score         The score the player accumulates
     * @param timeInSeconds The time the player spent in the game
     * @param image         The background image for the screen
     */
    public GameWonScreen(int score, long timeInSeconds, Image image) {
        super(image);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addSpace(this, 200, 50);

        // You Escaped Text
        addEscapedLabel(this, "You Escaped!");
        addSpace(this, 0, 60);

        // Score Text
        addScoreLabel(this, score);
        addSpace(this, 0, 60);

        // Time Text
        addTimeLabel(this, timeInSeconds);
        addSpace(this, 0, 60);

        // Quit Button
        addContinueButton(this, "Continue", score);
    }
}