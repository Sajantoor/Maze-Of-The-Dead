package ui.components;

import game.Entities;
import leaderboard.Leaderboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static ui.components.SpriteIcons.*;
import static ui.components.UIConstants.*;
import static ui.components.UIUtils.formatRewardCount;
import static ui.components.UIUtils.formatTime;
import static utilities.Constants.*;

/**
 * Other UI Components
 *
 * @author Kaung Si Thu
 */
public class Elements {
    /**
     * Game Title (JLabel)
     *
     * @param panel JPanel to be added to
     */
    public static void addGameTitle(JPanel panel) {
        JLabel gameTitle = new JLabel("Maze of The Dead");
        gameTitle.setFont(UIConstants.heading);
        gameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameTitle.setForeground(Color.WHITE);
        panel.add(gameTitle);
    }

    /**
     * Screen Title (JLabel)
     *
     * @param panel JPanel to be added to
     * @param title Title of the screen
     */
    public static void addTitle(JPanel panel, String title) {
        JLabel gameTitle = new JLabel(title);
        gameTitle.setFont(UIConstants.title);
        gameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameTitle.setForeground(Color.WHITE);
        panel.add(gameTitle);
    }

    /**
     * Title for certain components (JLabel)
     *
     * @param panel JPanel to be added to
     * @param title Title of screen
     */
    public static void addSmallTitle(JPanel panel, String title) {
        JLabel gameTitle = new JLabel(title);
        gameTitle.setFont(UIConstants.boldArial35);
        gameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameTitle.setForeground(Color.WHITE);
        panel.add(gameTitle);
    }

    /**
     * LeaderBoard Table (JTable: A table of Players with top five highest scores)
     *
     * @param panel       JPanel to be added to
     * @param leaderboard LeaderBoard to be shown
     * @see Leaderboard
     */
    public static void addLeaderBoard(JPanel panel, Leaderboard leaderboard) {
        JTable leaderBoardTable = new JTable();
        Object[] columns = { "Rank", "Name", "Score" };
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
        panel.add(pane);
    }

    /**
     * Reward Label and Count (JPanel: Shows how many Rewards the Player gained
     * throughout the game)
     *
     * @param panel JPanel to be added toS
     */
    public static void addRewardPanel(JPanel panel) {
        // Reward Panel
        JPanel rewardPanel = new JPanel();
        rewardPanel.setLayout(new BoxLayout(rewardPanel, BoxLayout.X_AXIS));
        // Reward Image
        JLabel rewardImageLabel = new JLabel(getReward(cellWidth, cellHeight));
        rewardPanel.add(rewardImageLabel);
        // Reward Text
        Entities entities = Entities.getInstance();

        JLabel rewardText = new JLabel(": " +
                formatRewardCount(rewardCount,
                        entities.getRewardCount(),
                        entities.getNumBonusRewards(),
                        entities.getBonusRewardsCollected()));
        rewardText.setFont(plainArial35);
        rewardText.setForeground(Color.WHITE);
        rewardText.setAlignmentX(Component.CENTER_ALIGNMENT);
        rewardPanel.add(rewardText);
        rewardPanel.setOpaque(false);
        panel.add(rewardPanel);
    }

    /**
     * Bonus Reward Label and Count (JPanel: Shows how many Bonus Rewards the Player
     * gained throughout the game)
     *
     * @param panel             JPanel to be added to
     * @param numOfBonusRewards Number of Bonus Rewards the Player gained throughout
     *                          the game
     */
    public static void addBonusRewardPanel(JPanel panel, int numOfBonusRewards) {
        // Bonus Reward Panel
        JPanel bonusRewardPanel = new JPanel();
        bonusRewardPanel.setLayout(new BoxLayout(bonusRewardPanel, BoxLayout.X_AXIS));
        // Bonus Reward Image
        JLabel bonusRewardImageLabel = new JLabel(getBonusReward(cellWidth, cellHeight));
        bonusRewardPanel.add(bonusRewardImageLabel);
        // BonusReward Text
        JLabel bonusRewardText = new JLabel(": " + numOfBonusRewards);
        bonusRewardText.setFont(plainArial35);
        bonusRewardText.setForeground(Color.WHITE);
        bonusRewardText.setAlignmentX(Component.CENTER_ALIGNMENT);
        bonusRewardPanel.add(bonusRewardText);
        bonusRewardPanel.setOpaque(false);
        panel.add(bonusRewardPanel);
    }

    /**
     * Score Label and Count (JLabel: Shows how many score the Player gained
     * throughout the game)
     *
     * @param panel JPanel to be added to
     * @param score The Score the Player gained throughout the game
     */
    public static void addScoreLabel(JPanel panel, int score) {
        JLabel scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(plainArial35);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setForeground(Color.WHITE);
        panel.add(scoreLabel);
    }

    /**
     * Time Label and Count (JLabel: Shows how long the player actively played the
     * game before winning or losing)
     *
     * @param panel         JPanel to be added to
     * @param timeInSeconds Time the player spent in the game less the Pause time in
     *                      seconds
     */
    public static void addTimeLabel(JPanel panel, long timeInSeconds) {
        JLabel timeText = new JLabel(formatTime(timeInSeconds));
        timeText.setFont(plainArial35);
        timeText.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeText.setForeground(Color.WHITE);
        panel.add(timeText);
    }

    /**
     * New High Score (JLabel: Shows the new HighScore the player achieved)
     *
     * @param panel        JPanel to be added to
     * @param newHighScore The new HighScore the player achieved
     */
    public static void addNewHighScoreLabel(JPanel panel, int newHighScore) {
        JLabel newHighScoreLabel = new JLabel(Integer.toString(newHighScore));
        newHighScoreLabel.setFont(boldArial35);
        newHighScoreLabel.setOpaque(false);
        newHighScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newHighScoreLabel.setForeground(Color.WHITE);
        panel.add(newHighScoreLabel);
    }

    /**
     * Input Box for player name (JTextField)
     *
     * @param panel JPanel to be added to
     * @return The Name Input reference to JTextField
     */
    public static JTextField getNameInput(JPanel panel) {
        // Name Panel
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        panel.add(namePanel);
        // Name Label
        JLabel nameLabel = new JLabel("Name: ");
        namePanel.add(nameLabel);
        // Name TextField
        JTextField nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(200, 30));
        nameTextField.setMaximumSize(nameTextField.getPreferredSize());
        nameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePanel.add(nameTextField);
        return nameTextField;
    }

    /**
     * You Escaped Label
     *
     * @param panel JPanel to be added to
     * @param title Title of the screen
     */
    public static void addEscapedLabel(JPanel panel, String title) {
        JLabel escapedLabel = new JLabel(title);
        escapedLabel.setFont(UIConstants.heading);
        escapedLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        escapedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        escapedLabel.setForeground(Color.WHITE);
        panel.add(escapedLabel);
    }

    /**
     * Game Paused Label
     *
     * @param panel JPanel to be added to
     * @param title Title of the screen
     */
    public static void addPausedLabel(JPanel panel, String title) {
        JLabel pauseText = new JLabel(title);
        pauseText.setFont(UIConstants.heading);
        pauseText.setAlignmentX(Component.CENTER_ALIGNMENT);
        pauseText.setForeground(Color.WHITE);
        panel.add(pauseText);
    }

}
