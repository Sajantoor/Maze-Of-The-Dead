package ui.components;

import leaderboard.PlayerScore;
import ui.UIUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.GameUI.*;
import static ui.GameUI.revalidate;

/**
 * Represents UI Button Components
 *
 * @author Kaung Si Thu
 */
public class Buttons {
    /**
     * Add Play Button (JButton: Open the GamePlayScreen if clicked)
     *
     * @param panel      JPanel to be added to
     * @param buttonName Button Label
     * @see ui.GamePlayScreen
     */
    public static void addPlayButton(JPanel panel, String buttonName) {
        JButton playButton = new JButton(buttonName);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().remove(panel);
                addGamePlayScreen();
                revalidate();
            }
        });
        UIUtils.buttonLayout(playButton);
        panel.add(playButton);
    }

    /**
     * Add Exit Game Button (JButton: Close the application if clicked)
     *
     * @param panel      JPanel to be added to
     * @param buttonName Button Label
     */
    public static void addExitGameButton(JPanel panel, String buttonName) {
        JButton exitGameButton = new JButton(buttonName);
        exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().dispose();
            }
        });
        UIUtils.buttonLayout(exitGameButton);
        panel.add(exitGameButton);
    }

    /**
     * Add Instruction Button (JButton: Open the InstructionScreen if clicked)
     *
     * @param panel      JPanel to be added to
     * @param buttonName Button Label
     * @see ui.InstructionScreen
     */
    public static void addInstructionButton(JPanel panel, String buttonName) {
        JButton instructionButton = new JButton(buttonName);
        instructionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().remove(panel);
                addInstructionScreen();
                revalidate();
            }
        });
        UIUtils.buttonLayout(instructionButton);
        panel.add(instructionButton);
    }

    /**
     * Add Quit Button (JButton: Open the TitleScreen if clicked)
     *
     * @param panel      JPanel to be added to
     * @param buttonName Button Label
     * @see ui.TitleScreen
     */
    public static void addQuitButton(JPanel panel, String buttonName) {
        JButton quitButton = new JButton(buttonName);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().remove(panel);
                addTitleScreen();
                revalidate();
            }
        });
        UIUtils.buttonLayout(quitButton);
        panel.add(quitButton);
    }

    /**
     * Add Submit Name Button (JButton: Submit the name from the nameTextField and the score to LeaderBoardScreen if clicked)
     *
     * @param panel         JPanel to be added to
     * @param buttonName    Button Label
     * @param nameTextField Name of the player with the new high score
     * @param score         Score of the player
     * @see ui.LeaderboardScreen
     */
    public static void addSubmitNameButton(JPanel panel, String buttonName, JTextField nameTextField, int score) {
        JButton submitNameButton = new JButton("Submit");
        submitNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                PlayerScore playerScore = new PlayerScore(name, score);
                getFrame().remove(panel);
                addLeaderboardScreen(playerScore);
                revalidate();
            }
        });
        UIUtils.buttonLayout(submitNameButton);
        panel.add(submitNameButton);
    }
}
