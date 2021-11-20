package ui;

import leaderboard.PlayerScore;

import javax.swing.*;

public class GameUI {
    private static JFrame frame;

    public GameUI() {
        // Full Screen
        frame = new JFrame("Maze of The Dead");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        addTitleScreen();


        frame.setVisible(true);
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void addGamePlayScreen() {
        GamePlayScreen gamePlayScreen = new GamePlayScreen();
        frame.add(gamePlayScreen.getGamePlayScreen());
    }

    public static void addInstructionScreen() {
        InstructionScreen instructionScreen = new InstructionScreen();
        frame.add(instructionScreen.getInstructionScreen());
    }

    public static void addTitleScreen() {
        TitleScreen titleScreen = new TitleScreen();
        frame.add(titleScreen.getTitleScreen());
    }

    public static void addGameWonScreen(int score, long timeInSeconds) {
        GameWonScreen gameWonScreen = new GameWonScreen();
        frame.add(gameWonScreen.getGameWonScreen(score, timeInSeconds));
    }

    public static void addGameOverScreen(int score, long timeInSeconds, int numOfRewards, int numOfBonusRewards) {
        GameOverScreen gameOverScreen = new GameOverScreen();
        frame.add(gameOverScreen.getGameOverScreen(score, timeInSeconds, numOfRewards, numOfBonusRewards));
    }

    public static void addNewHighScoreScreen(int score) {
        NewHighScoreScreen newHighScoreScreen = new NewHighScoreScreen();
        frame.add(newHighScoreScreen.getNewHighScoreScreen(score));
    }

    public static void addLeaderboardScreen(PlayerScore HighPlayerScore) {
        LeaderboardScreen leaderboardScreen = new LeaderboardScreen();
        frame.add(leaderboardScreen.getLeaderboardScreen(HighPlayerScore));
    }

    public static void revalidate() {
        frame.revalidate();
        frame.repaint();
    }
    // create static methods to call for your panel below. Follow format above

}
