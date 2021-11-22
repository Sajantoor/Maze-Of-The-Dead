package ui;

import leaderboard.Leaderboard;
import leaderboard.PlayerScore;

import javax.swing.*;

import static ui.UIUtils.*;
import static ui.components.Buttons.*;
import static ui.components.Elements.*;

/**
 * Represent the Leaderboard screen
 *
 * @author Kaung Si Thu
 */
public class LeaderboardScreen {
    private JPanel leaderBoardPanel;
    private JLabel titleLabel;
    private JLabel leaderBoardLabel;
    private JButton playAgainButton;
    private JButton quitButton;

    /**
     * Return the Leaderboard screen
     *
     * @param playerScore New High Score of the player
     * @return Leaderboard Screen
     */
    public JPanel getLeaderboardScreen(PlayerScore playerScore) {
        leaderBoardPanel = new JPanel();
        leaderBoardPanel.setLayout(new BoxLayout(leaderBoardPanel, BoxLayout.PAGE_AXIS));
        addSpace(leaderBoardPanel, 0, 60);

        //update Leaderboard
        Leaderboard leaderboard = Leaderboard.getInstance();
        leaderboard.addPlayerScore(playerScore);

        //Title
        addTitle(leaderBoardPanel, "You Escaped!");
        addSpace(leaderBoardPanel, 0, 60);

        //LeaderBoard Title
        addSmallTitle(leaderBoardPanel, "Leaderboard");
        addSpace(leaderBoardPanel, 0, 60);

        //LeaderBoard Table
        addLeaderBoard(leaderBoardPanel, leaderboard);
        addSpace(leaderBoardPanel, 0, 60);

        //Play Again Button
        addPlayAgainButton(leaderBoardPanel, "Play Again");
        addSpace(leaderBoardPanel, 0, 60);

        //Quit Button
        addQuitButton(leaderBoardPanel, "Quit");

        return leaderBoardPanel;
    }
}
