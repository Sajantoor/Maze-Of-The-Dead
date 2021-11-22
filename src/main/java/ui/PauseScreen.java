package ui;

import javax.swing.*;
import java.awt.*;

import static ui.components.Buttons.*;
import static ui.components.Elements.*;

/**
 * Creates a pause screen when a player pauses the game
 *
 * @author Maisha Supritee Chowdhury
 * @see GameUI
 */

public class PauseScreen {
    private JPanel pauseScreenPanel;

    /**
     * returns the PauseScreen
     *
     * @return the PauseScreen
     * @see JPanel
     */
    public JPanel getPauseScreen() {
        pauseScreenPanel = new PanelWithBackgroundImage(new ImageIcon("src/main/java/ui/images/background.jpg").getImage());
        pauseScreenPanel.setLayout(new BoxLayout(pauseScreenPanel, BoxLayout.PAGE_AXIS));
        UIUtils.addSpace(pauseScreenPanel, 500, 100);

        // Game Paused text
        addPausedLabel(pauseScreenPanel, "Game Paused!");
        UIUtils.addSpace(pauseScreenPanel, 300, 60);

        // Resume button
        addResumeButton(pauseScreenPanel, "Resume");
        UIUtils.addSpace(pauseScreenPanel, 0, 50);

        // Quit button
        addQuitButton(pauseScreenPanel, "Quit");
        UIUtils.addSpace(pauseScreenPanel, 0, 50);

        pauseScreenPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pauseScreenPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        return pauseScreenPanel;
    }


}
