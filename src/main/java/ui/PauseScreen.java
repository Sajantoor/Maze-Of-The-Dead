package ui;

import javax.swing.*;

import static ui.components.Buttons.*;
import static ui.components.Elements.*;

/**
 * Creates a pause screen when a player pauses the game
 *
 * @author Maisha Supritee Chowdhury
 * @see GameUI
 */

public class PauseScreen {
    private JPanel PauseScreenPanel;

    public JPanel getPauseScreen() {
        PauseScreenPanel = new PanelWithBackgroundImage(new ImageIcon("src/main/java/ui/images/background.jpg").getImage());
        PauseScreenPanel.setLayout(new BoxLayout(PauseScreenPanel, BoxLayout.PAGE_AXIS));
        UIUtils.addSpace(PauseScreenPanel, 500, 250);

        // Game Paused text
        addPausedLabel(PauseScreenPanel, "Game Paused!");
        UIUtils.addSpace(PauseScreenPanel, 300, 60);

        // Resume button
        addResumeButton(PauseScreenPanel, "Resume");
        UIUtils.addSpace(PauseScreenPanel, 0, 60);

        // Quit button
        addQuitButton(PauseScreenPanel, "Quit");
        UIUtils.addSpace(PauseScreenPanel, 0, 60);

        return PauseScreenPanel;
    }


}
