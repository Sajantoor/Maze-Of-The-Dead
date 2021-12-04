package ui.Screens;

import ui.components.PanelWithBackgroundImage;
import ui.components.UIUtils;

import javax.swing.*;
import java.awt.*;

import static ui.components.Buttons.*;
import static ui.components.Elements.*;

/**
 * Creates a pause screen when a player pauses the game
 *
 * @author Maisha Supritee Chowdhury
 */

public class PauseScreen extends PanelWithBackgroundImage {
    /**
     * returns the PauseScreen
     *
     * @param image The background image for the screen
     * @see JPanel
     */
    public PauseScreen(Image image) {
        super(image);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        UIUtils.addSpace(this, 500, 100);

        // Game Paused text
        addPausedLabel(this, "Game Paused!");
        UIUtils.addSpace(this, 300, 60);

        // Resume button
        addResumeButton(this, "Resume");
        UIUtils.addSpace(this, 0, 50);

        // Quit button
        addQuitButton(this, "Quit");
        UIUtils.addSpace(this, 0, 50);

        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setAlignmentY(Component.CENTER_ALIGNMENT);
    }
}
