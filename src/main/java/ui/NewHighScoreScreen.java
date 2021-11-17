package ui;

import leaderboard.PlayerScore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.*;
import static ui.GameUI.*;
import static ui.UIConstants.*;

/**
 * Represents the New High Score Screen
 * (Will be shown if the player's score is high enough to be one of the highest five scores)
 * @author Kaung Si Thu
 */
public class NewHighScoreScreen {
    private JPanel newHighScoreScreenPanel;
    private JLabel titleLabel;
    private JLabel scoreLabel;
    private JPanel namePanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JButton submitButton;

    /**
     * Return the New High Score Screen
     * (Will be called if the player's score is high enough to be one of the highest five scores)
     * @param score New High Score of the player
     * @return New High Score Screen
     */
    public JPanel getNewHighScoreScreen(int score) {
        newHighScoreScreenPanel = new JPanel();
        newHighScoreScreenPanel.setLayout(new BoxLayout(newHighScoreScreenPanel, BoxLayout.PAGE_AXIS));

        UIUtils.addSpace(newHighScoreScreenPanel, 300, 60);

        //Title Label
        titleLabel = new JLabel("New Highscore!");
        titleLabel.setFont(heading);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newHighScoreScreenPanel.add(titleLabel);

        UIUtils.addSpace(newHighScoreScreenPanel, 300, 60);

        //Score Label
        scoreLabel = new JLabel(Integer.toString(score));
        scoreLabel.setFont(boldArial35);
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(cyan);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newHighScoreScreenPanel.add(scoreLabel);

        UIUtils.addSpace(newHighScoreScreenPanel, 500, 60);

        //Name Panel
        namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        newHighScoreScreenPanel.add(namePanel);
        //Name Label
        nameLabel = new JLabel("Name: ");
        namePanel.add(nameLabel);
        //Name TextField
        nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(200, 30));
        nameTextField.setMaximumSize(nameTextField.getPreferredSize());
        nameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePanel.add(nameTextField);

        UIUtils.addSpace(newHighScoreScreenPanel, 500, 60);

        //Submit Button
        submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newHighScoreScreenPanel.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                PlayerScore playerScore = new PlayerScore(name, score);
                getFrame().remove(newHighScoreScreenPanel);
                addLeaderboardScreen(playerScore);
                revalidate();
            }
        });

        return newHighScoreScreenPanel;
    }

}
