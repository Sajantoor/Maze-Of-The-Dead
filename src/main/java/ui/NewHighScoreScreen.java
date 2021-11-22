package ui;

import javax.swing.*;

import static ui.UIUtils.*;
import static ui.components.Buttons.*;
import static ui.components.Elements.*;


/**
 * Represents the New High Score Screen
 * (Will be shown if the player's score is high enough to be one of the highest five scores)
 *
 * @author Kaung Si Thu
 */
public class NewHighScoreScreen {
    private JPanel newHighScoreScreenPanel;

    /**
     * Return the New High Score Screen
     * (Will be called if the player's score is high enough to be one of the highest five scores)
     *
     * @param newHighScore New High Score of the player
     * @return New High Score Screen
     */
    public JPanel getNewHighScoreScreen(int newHighScore) {
        newHighScoreScreenPanel = new PanelWithBackgroundImage(new ImageIcon("src/main/java/ui/images/background.jpg").getImage());
        newHighScoreScreenPanel.setLayout(new BoxLayout(newHighScoreScreenPanel, BoxLayout.PAGE_AXIS));
        addSpace(newHighScoreScreenPanel, 0, 100);

        //Title Label
        addTitle(newHighScoreScreenPanel, "New HighScore!");
        addSpace(newHighScoreScreenPanel, 0, 60);

        //Score Label
        addNewHighScoreLabel(newHighScoreScreenPanel, newHighScore);
        addSpace(newHighScoreScreenPanel, 0, 60);

        //Name input
        JTextField nameInput = getNameInput(newHighScoreScreenPanel);
        addSpace(newHighScoreScreenPanel, 0, 60);

        //Submit Button
        addSubmitNameButton(newHighScoreScreenPanel, "Submit", nameInput, newHighScore);

        return newHighScoreScreenPanel;
    }


}
