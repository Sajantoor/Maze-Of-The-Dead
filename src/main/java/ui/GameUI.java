package ui;

import leaderboard.PlayerScore;
import ui.Frames.GameFrame;
import ui.Frames.SubFrame;
import ui.Screens.*;

import javax.swing.*;
import java.awt.*;

import static ui.components.SpriteIcons.getBackgroundImage;
import static ui.components.UIConstants.defaultScreenSizeX;
import static ui.components.UIConstants.defaultScreenSizeY;

/**
 * Controls the calling and displaying all the screens
 *
 * @author Dylan Young
 */
public class GameUI {

    private static GameFrame frame;
    private static SubFrame subFrame;
    private static JPanel panel;
    private static JPanel subPanel;
    private static Image image;
    private static JPanel gamePanel = new JPanel();


    /**
     * Represents the controls for calling and displaying all the screens
     */
    public GameUI() {
        // Full Screen
        frame = new GameFrame();
        subFrame = new SubFrame();
        image = getBackgroundImage(defaultScreenSizeX, defaultScreenSizeY).getImage();
        addTitleScreen();
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
        panel = new InstructionScreen();
        frame.add(panel);
    }

    /**
     * Displays the Title screen
     */
    public static void addTitleScreen() {
        panel = new TitleScreen(image);
        frame.add(panel);
    }

    /**
     * Displays the GameOver screen
     */
    public static void addGameWonScreen(int score, long timeInSeconds) {
        subPanel = new GameWonScreen(score, timeInSeconds, image);
        subFrame.setSize(600, 600);
        subFrame.setLocationRelativeTo(null);
        subFrame.add(subPanel);
    }

    public static void addGameOverScreen(int score, long timeInSeconds, int numOfRewards, int numOfBonusRewards) {
        subPanel = new GameOverScreen(score, timeInSeconds, numOfRewards, numOfBonusRewards, image);
        subFrame.setSize(new Dimension(700, 900));
        subFrame.setLocationRelativeTo(null);
        subFrame.add(subPanel);
    }

    public static void addPauseScreen() {
        subPanel = new PauseScreen(image);
        subFrame.setSize(new Dimension(500, 500));
        subFrame.setLocationRelativeTo(null);
        subFrame.add(subPanel);
    }

    /**
     * Displays the NewHighScore screen
     *
     * @param score the score they the player accumulates through the game.
     */
    public static void addNewHighScoreScreen(int score) {
        subPanel = new NewHighScoreScreen(score, image);
        subFrame.setSize(500,500);
        subFrame.add(subPanel);
        subFrame.setLocationRelativeTo(null);
    }

    /**
     * Displays the Leaderboard screen
     *
     * @param highPlayerScore the name and score of the player
     */
    public static void addLeaderboardScreen(PlayerScore highPlayerScore) {
        subPanel = new LeaderboardScreen(highPlayerScore, image);
        subFrame.add(subPanel);
        subFrame.setSize(750, 1000);
        subFrame.setLocationRelativeTo(null);
    }

    /**
     * Refreshes the main screen
     */
    public static void revalidateMainScreen() {
        frame.revalidate();
        frame.repaint();
        frame.setFocusable(true);
    }
    /**
     * Refreshes the sub-screen
     */
    public static void revalidateSubScreen() {
        subFrame.revalidate();
        subFrame.repaint();
        frame.setFocusable(true);
    }

    public static void mainFrameRefocus(){
        frame.requestFocusInWindow();
    }
    public static void subFrameRefocus(){
        subFrame.requestFocusInWindow();
    }
}
