package ui;

import javax.swing.*;

import java.awt.*;

import static ui.GameUI.*;
import static ui.UIUtils.addSpace;
import static ui.components.Buttons.*;
import static ui.components.Elements.*;

/**
 * Represents the TitleScreen
 *
 * @author Dylan Young
 */
public class TitleScreen {
    private JPanel titleScreenPanel;

    /**
     * returns the title screen panel
     *
     * @return the title screen panel
     */
    public JPanel getTitleScreen() {
        titleScreenPanel = new JPanel();
        titleScreenPanel.setLayout(new BoxLayout(titleScreenPanel, BoxLayout.PAGE_AXIS));
        addSpace(titleScreenPanel, 500, 400);

        // Name of the game displayed
        addGameTitle(titleScreenPanel);
        addSpace(titleScreenPanel, 0, 30);

        // Start Button
        addPlayButton(titleScreenPanel, "Start");
        addSpace(titleScreenPanel, 0, 30);

        // Instruction button
        addInstructionButton(titleScreenPanel, "Instruction");
        addSpace(titleScreenPanel, 0, 30);

        // Exit Game button
        addExitGameButton(titleScreenPanel, "Exit Game");

        // adds the title panel to the static frame
        getFrame().add(titleScreenPanel);
        return titleScreenPanel;
    }

}
