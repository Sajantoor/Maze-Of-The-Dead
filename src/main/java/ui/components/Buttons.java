package ui.components;

import game.GameController;
import leaderboard.PlayerScore;
import leaderboard.Leaderboard;
import ui.Screens.GamePlayScreen;
import ui.Screens.InstructionScreen;
import ui.Screens.LeaderboardScreen;
import ui.Screens.NewHighScoreScreen;
import ui.Screens.TitleScreen;

import javax.swing.*;
import java.awt.*;

import static ui.GameUI.*;
import static ui.components.UIUtils.buttonLayout;
import static utilities.Constants.leaderboardCapacity;

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
     * @see GamePlayScreen
     */
    public static void addPlayButton(JPanel panel, String buttonName) {
        JButton playButton = new JButton(buttonName);
        playButton.addActionListener(e -> {
            getFrame().remove(panel);
            mainFrameRefocus();
            addGamePlayScreen();
            revalidateMainScreen();
        });
        buttonLayout(playButton);
        panel.add(playButton);
    }

    /**
     * Add Play Button (JButton: Open the GameOverScreen and LeaderboardScreen if
     * clicked)
     *
     * @param panel      JPanel to be added to
     * @param buttonName Button Label
     * @see GamePlayScreen
     */
    public static void addPlayAgainButton(JPanel panel, String buttonName) {
        JButton playButton = new JButton(buttonName);
        playButton.addActionListener(e -> {
            getSubFrame().remove(panel);
            getSubFrame().setVisible(false);
            getFrame().setEnabled(true);
            GameController.getInstance().setRunning(false);
            mainFrameRefocus();
            removeGamePlayScreen();
            addGamePlayScreen();
            revalidateMainScreen();
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
        exitGameButton.addActionListener(e -> {
            Leaderboard.getInstance().writeToFile();
            getFrame().dispose();
            System.exit(0);
        });
        buttonLayout(exitGameButton);
        panel.add(exitGameButton);
    }

    /**
     * Add Instruction Button (JButton: Open the InstructionScreen if clicked)
     *
     * @param panel      JPanel to be added to
     * @param buttonName Button Label
     * @see InstructionScreen
     */
    public static void addInstructionButton(JPanel panel, String buttonName) {
        JButton instructionButton = new JButton(buttonName);
        instructionButton.addActionListener(e -> {
            getFrame().remove(panel);
            addInstructionScreen();
            revalidateMainScreen();
        });
        buttonLayout(instructionButton);
        panel.add(instructionButton);
    }

    /**
     * Add Quit Button (JButton: Open the TitleScreen if clicked)
     *
     * @param panel      JPanel to be added to
     * @param buttonName Button Label
     * @see TitleScreen
     */
    public static void addQuitButton(JPanel panel, String buttonName) {
        JButton quitButton = new JButton(buttonName);
        quitButton.addActionListener(e -> {
            getSubFrame().remove(panel);
            getSubFrame().setVisible(false);
            getFrame().setEnabled(true);
            GameController.getInstance().setRunning(false);
            GameController.getInstance().unpauseGame();
            GameController.getInstance().setQuit();
            removeGamePlayScreen();
            addTitleScreen();
            revalidateMainScreen();
            mainFrameRefocus();
        });
        buttonLayout(quitButton);
        panel.add(quitButton);
    }

    /**
     * Add Back Button (JButton: Open the TitleScreen if clicked)
     *
     * @param panel      JPanel to be added to
     * @param buttonName Button Label
     * @see TitleScreen
     */
    public static void addBackButton(JPanel panel, String buttonName) {
        JButton quitButton = new JButton(buttonName);
        quitButton.addActionListener(e -> {
            getFrame().remove(panel);
            addTitleScreen();
            revalidateMainScreen();
        });
        buttonLayout(quitButton);
        panel.add(quitButton);
    }

    /**
     * Add Submit Name Button (JButton: Submit the name from the nameTextField and
     * the score to LeaderBoardScreen if clicked)
     *
     * @param panel         JPanel to be added to
     * @param buttonName    Button Label
     * @param nameTextField Name of the player with the new high score
     * @param score         Score of the player
     * @see LeaderboardScreen
     */
    public static void addSubmitNameButton(JPanel panel, String buttonName, JTextField nameTextField, int score) {
        JButton submitNameButton = new JButton(buttonName);
        submitNameButton.addActionListener(e -> {
            String name = nameTextField.getText();
            if (!name.contains("#")) {
                PlayerScore playerScore = new PlayerScore(name, score);
                getSubFrame().remove(panel);
                addLeaderboardScreen(playerScore);
                revalidateSubScreen();
                revalidateMainScreen();
            }
        });
        buttonLayout(submitNameButton);
        panel.add(submitNameButton);
    }

    /**
     * Add Continue Button (JButton: Takes to Leaderboard screen if score not high
     * enough, or to the New High Score screen)
     *
     * @param panel      JPanel to be added to
     * @param buttonName Button Label
     * @param score      Score of the player
     * @see LeaderboardScreen
     * @see NewHighScoreScreen
     */
    public static void addContinueButton(JPanel panel, String buttonName, int score) {
        JButton continueButton = new JButton(buttonName);
        continueButton.addActionListener(e -> {
            if (score < Leaderboard.getInstance().getMinimumScore()
                    && Leaderboard.getInstance().getLeaderboardSize() >= leaderboardCapacity) {
                getSubFrame().remove(panel);
                addLeaderboardScreen(null);
            } else {
                getSubFrame().remove(panel);
                addNewHighScoreScreen(score);
            }
            revalidateSubScreen();
            revalidateMainScreen();
        });
        buttonLayout(continueButton);
        panel.add(continueButton);
    }

    /**
     * Add Resume Button (JButton: Takes player back to game screen to resume
     * playing)
     *
     * @param panel      JPanel to be added to
     * @param buttonName Button Label
     * @see GamePlayScreen
     */
    public static void addResumeButton(JPanel panel, String buttonName) {
        JButton resumeButton = new JButton(buttonName);
        buttonLayout(resumeButton);
        resumeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resumeButton.addActionListener(e -> {
            getSubFrame().remove(panel);
            getSubFrame().setVisible(false);
            getFrame().setEnabled(true);
            GameController.getInstance().unpauseGame();
            revalidateMainScreen();
            mainFrameRefocus();
        });
        panel.add(resumeButton);
    }
}
