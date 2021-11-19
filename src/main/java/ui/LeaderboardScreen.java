package ui;

import leaderboard.Leaderboard;
import leaderboard.PlayerScore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.GameUI.*;
import static ui.UIConstants.*;

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
        //update Leaderboard
        Leaderboard leaderboard = Leaderboard.getInstance();
        leaderboard.addPlayerScore(playerScore);

        leaderBoardPanel = new JPanel();
        leaderBoardPanel.setLayout(new BoxLayout(leaderBoardPanel, BoxLayout.PAGE_AXIS));

        UIUtils.addSpace(leaderBoardPanel, 300, 60);

        //Title
        titleLabel = new JLabel("You Escaped!");
        titleLabel.setFont(heading);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderBoardPanel.add(titleLabel);

        UIUtils.addSpace(leaderBoardPanel, 300, 60);

        //LeaderBoard Title
        leaderBoardLabel = new JLabel("Leaderboard");
        leaderBoardLabel.setFont(boldArial35);
        leaderBoardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderBoardPanel.add(leaderBoardLabel);

        UIUtils.addSpace(leaderBoardPanel, 300, 60);

        //LeaderBoard Table
        JTable leaderBoardTable = new JTable();
        Object[] columns = {"Rank", "Name", "Score"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        leaderBoardTable.setModel(model);
        leaderBoardTable.setBackground(Color.white);
        leaderBoardTable.setForeground(Color.black);
        leaderBoardTable.setSelectionBackground(Color.red);
        leaderBoardTable.setSelectionForeground(Color.white);
        leaderBoardTable.setRowHeight(50);
        leaderBoardTable.setAutoCreateRowSorter(true);
        JScrollPane pane = new JScrollPane(leaderBoardTable);
        pane.setForeground(Color.RED);
        pane.setBackground(Color.WHITE);
        pane.setPreferredSize(new Dimension(300, 300));
        pane.setMaximumSize(pane.getPreferredSize());
        for (int i = 0; i < leaderboard.getLeaderboardSize(); i++) {
            Object[] row = new Object[3];
            row[0] = "1";
            row[1] = leaderboard.getPlayerScore(i).getName();
            row[2] = leaderboard.getPlayerScore(i).getScore();
            model.addRow(row);
        }
        leaderBoardPanel.add(pane);

        UIUtils.addSpace(leaderBoardPanel, 300, 60);

        //Play Again Button
        playAgainButton = new JButton("Play Again");
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().remove(leaderBoardPanel);
                addGamePlayScreen();
                revalidate();
            }
        });
        leaderBoardPanel.add(playAgainButton);

        UIUtils.addSpace(leaderBoardPanel, 300, 60);

        //Quit Button
        quitButton = new JButton("Quit");
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().remove(leaderBoardPanel);
                addTitleScreen();
                revalidate();
            }
        });
        leaderBoardPanel.add(quitButton);


        return leaderBoardPanel;
    }
}
