package ui;

import leaderboard.PlayerScore;

import javax.swing.*;
import java.awt.*;

/**
 * Controls the calling and displaying all the screens
 *
 * @author Dylan Young
 */
public class GameUI {
    private static JFrame frame;
    private static JFrame subFrame;
    private static GamePlayScreen gamePlayScreen = null;
    private static InstructionScreen instructionScreen = new InstructionScreen();
    private static TitleScreen titleScreen = new TitleScreen();
    private static GameWonScreen gameWonScreen = new GameWonScreen();
    private static GameOverScreen gameOverScreen = new GameOverScreen();
    private static PauseScreen pauseScreen = new PauseScreen();
    private static NewHighScoreScreen newHighScoreScreen = new NewHighScoreScreen();
    private static LeaderboardScreen leaderboardScreen = new LeaderboardScreen();
    private static JPanel gamePanel = new JPanel();


    /**
     * Represents the controls for calling and displaying all the screens
     */
    public GameUI() {
        // Full Screen
        frame = new JFrame("Maze of The Dead");
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setUndecorated(true);

        addTitleScreen();

        subFrame = new JFrame();
        subFrame.setLocationRelativeTo(null);
        subFrame.setAutoRequestFocus(true);

        frame.setVisible(true);
    }

    /**
     * returns the frame for the game
     *
     * @return the frame for the game
     */
    public static JFrame getFrame() {
        return frame;
    }
    /**
     * returns the sub-frame for the game
     *
     * @return the sub-frame for the game
     */
    public static JFrame getSubFrame() {
        return subFrame;
    }

    /**
     * Displays the GamePlay screen
     */
    public static void addGamePlayScreen() {
        gamePanel = GamePlayScreen.getInstance();
        frame.add(gamePanel);
        revalidateMainScreen();
    }
    public static void removeGamePlayScreen() {
        frame.remove(gamePanel);
    }

    /**
     * Displays the Instruction screen
     */
    public static void addInstructionScreen() {
        frame.add(instructionScreen.getInstructionScreen());
    }

    /**
     * Displays the Title screen
     */
    public static void addTitleScreen() {
        frame.add(titleScreen.getTitleScreen());
    }

    /**
     * Displays the GameOver screen
     */
    public static void addGameWonScreen(int score, long timeInSeconds) {
        subFrame.setSize(500, 600);
        subFrame.setLocationRelativeTo(null);
        subFrame.add(gameWonScreen.getGameWonScreen(score, timeInSeconds));
    }

    public static void addGameOverScreen(int score, long timeInSeconds, int numOfRewards, int numOfBonusRewards) {
        subFrame.setSize(new Dimension(700, 900));
        subFrame.setLocationRelativeTo(null);
        subFrame.add(gameOverScreen.getGameOverScreen(score, timeInSeconds, numOfRewards, numOfBonusRewards));
    }

    public static void addPauseScreen() {
        subFrame.setSize(new Dimension(500, 500));
        subFrame.setLocationRelativeTo(null);
        subFrame.add(pauseScreen.getPauseScreen());
    }

    /**
     * Displays the NewHighScore screen
     *
     * @param score the score they the player accumulates through the game.
     */
    public static void addNewHighScoreScreen(int score) {
        subFrame.setSize(500,500);
        subFrame.add(newHighScoreScreen.getNewHighScoreScreen(score));
        subFrame.setLocationRelativeTo(null);
    }

    /**
     * Displays the Leaderboard screen
     *
     * @param HighPlayerScore the name and score of the player
     */
    public static void addLeaderboardScreen(PlayerScore HighPlayerScore) {
        subFrame.add(leaderboardScreen.getLeaderboardScreen(HighPlayerScore));
        subFrame.setSize(500, 500);
        subFrame.setLocationRelativeTo(null);
    }

    /**
     * Refresh the screen
     */
    public static void revalidateMainScreen() {
        frame.revalidate();
        frame.repaint();
        frame.setFocusable(true);
    }

    public static void revalidateSubScreen() {
        subFrame.revalidate();
        subFrame.repaint();
        frame.setFocusable(true);
    }

}
