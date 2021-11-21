package ui.components;

import game.GameController;
import leaderboard.PlayerScore;
import leaderboard.Leaderboard;
import ui.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.GameUI.*;
import static ui.GameUI.revalidateMainScreen;
import static ui.UIUtils.buttonLayout;

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
                revalidateMainScreen();
            }
        });
        buttonLayout(playButton);
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
        buttonLayout(exitGameButton);
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
                revalidateMainScreen();
            }
        });
        buttonLayout(instructionButton);
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
                getSubFrame().remove(panel);
                getSubFrame().setVisible(false);
                getFrame().setEnabled(true);
                GameController.getInstance().setRunning(false);
                removeGamePlayScreen();
                addTitleScreen();
                revalidateMainScreen();
            }
        });
        buttonLayout(quitButton);
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
                revalidateMainScreen();
            }
        });
        buttonLayout(submitNameButton);
        panel.add(submitNameButton);
    }

    /**
     * Add Continue Button (JButton: Takes to Leaderboard screen if score not high enough, or to the New High Score screen)
     *
     * @param panel         JPanel to be added to
     * @param buttonName    Button Label
     * @param score         Score of the player
     * @see ui.LeaderboardScreen
     * @see ui.NewHighScoreScreen
     */
    public static void addContinueButton(JPanel panel, String buttonName, int score){
        JButton continueButton = new JButton(buttonName);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(score < Leaderboard.getInstance().getMinimumScore()) {
                    PlayerScore playerScore = new PlayerScore("",score);
                    getFrame().remove(panel);
                    addLeaderboardScreen(playerScore);
                }
                else{
                    getFrame().remove(panel);
                    addNewHighScoreScreen(score);
                }
                revalidateMainScreen();
            }
        });
        buttonLayout(continueButton);
        panel.add(continueButton);
    }

    /**
     * Add Resume Button (JButton: Takes player back to game screen to resume playing)
     *
     * @param panel         JPanel to be added to
     * @param buttonName    Button Label
     * @see ui.GamePlayScreen
     */
    public static void addResumeButton(JPanel panel, String buttonName){
        JButton resumeButton = new JButton(buttonName);
        buttonLayout(resumeButton);
        resumeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSubFrame().remove(panel);
                getSubFrame().setVisible(false);
                getFrame().setEnabled(true);
                addGamePlayScreen();
                revalidateMainScreen();
            }
        });
        panel.add(resumeButton);
    }
}


