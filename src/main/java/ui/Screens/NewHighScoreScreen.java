package ui.Screens;

import ui.components.PanelWithBackgroundImage;

import javax.swing.*;

import java.awt.*;

import static ui.components.UIUtils.*;
import static ui.components.Buttons.*;
import static ui.components.Elements.*;

/**
 * Represents the New High Score Screen
 * (Will be shown if the player's score is high enough to be one of the highest
 * five scores)
 *
 * @author Kaung Si Thu
 */
public class NewHighScoreScreen extends PanelWithBackgroundImage {
    /**
     * Return the New High Score Screen
     * (Will be called if the player's score is high enough to be one of the highest
     * five scores)
     *
     * @param newHighScore New High Score of the player
     * @param image        The background image for the screen
     */
    public NewHighScoreScreen(int newHighScore, Image image) {
        super(image);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addSpace(this, 0, 100);

        // Title Label
        addTitle(this, "New HighScore!");
        addSpace(this, 0, 60);

        // Score Label
        addNewHighScoreLabel(this, newHighScore);
        addSpace(this, 0, 60);

        // Name input
        JTextField nameInput = getNameInput(this);
        addSpace(this, 0, 60);

        // Submit Button
        addSubmitNameButton(this, "Submit", nameInput, newHighScore);
    }

}
