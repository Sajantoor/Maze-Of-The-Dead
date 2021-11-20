package ui;

import leaderboard.PlayerScore;

import javax.swing.*;

/**
 * Controls the calling and displaying all the screens
 *
 * @author Dylan Young
 */
public class GameUI {
    private static JFrame frame;

    /**
     * Represents the controls for calling and displaying all the screens
     */
    public GameUI() {
        // Full Screen
        frame = new JFrame("Maze of The Dead");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

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
     * Displays the GamePlay screen
     */
    public static void addGamePlayScreen() {
        GamePlayScreen gamePlayScreen = new GamePlayScreen();
        frame.add(gamePlayScreen.getGamePlayScreen());
    }

    /**
     * Displays the Instruction screen
     */
    public static void addInstructionScreen() {
        InstructionScreen instructionScreen = new InstructionScreen();
        frame.add(instructionScreen.getInstructionScreen());
    }

    /**
     * Displays the Title screen
     */
    public static void addTitleScreen() {
        TitleScreen titleScreen = new TitleScreen();
        frame.add(titleScreen.getTitleScreen());
    }

    /**
     * Displays the GameOver screen
     */
    public static void addGameWonScreen(int score, long timeInSeconds) {
        GameWonScreen gameWonScreen = new GameWonScreen();
        frame.add(gameWonScreen.getGameWonScreen(score, timeInSeconds));
    }

    public static void addGameOverScreen(int score, long timeInSeconds, int numOfRewards, int numOfBonusRewards) {
        GameOverScreen gameOverScreen = new GameOverScreen();
        frame.add(gameOverScreen.getGameOverScreen(score, timeInSeconds, numOfRewards, numOfBonusRewards));
    }

    public static void addPauseScreen() {
        PauseScreen pauseScreen = new PauseScreen();
        frame.add(pauseScreen.getPauseScreen());
    }

    /**
     * Displays the NewHighScore screen
     *
     * @param score the score they the player accumulates through the game.
     */
    public static void addNewHighScoreScreen(int score) {
        NewHighScoreScreen newHighScoreScreen = new NewHighScoreScreen();
        frame.add(newHighScoreScreen.getNewHighScoreScreen(score));
    }

    /**
     * Displays the Leaderboard screen
     *
     * @param HighPlayerScore the name and score of the player
     */
    public static void addLeaderboardScreen(PlayerScore HighPlayerScore) {
        LeaderboardScreen leaderboardScreen = new LeaderboardScreen();
        frame.add(leaderboardScreen.getLeaderboardScreen(HighPlayerScore));
    }

    /**
     * Refresh the screen
     */
    public static void revalidate() {
        frame.revalidate();
        frame.repaint();
    }

}
