package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.GameUI.*;
import static ui.UIConstants.*;
import static utilities.Constants.rewardCount;

/**
 * Represent the "Game Over" screen
 *
 * @author Kaung Si Thu
 */
public class GameOverScreen {
    private JPanel gameOverScreenPanel;

    /**
     * Return the "Game Over" screen
     *
     * @return the "Game Over" screen
     */
    public JPanel getGameOverScreen(int score, long timeInSeconds, int numOfRewards, int numOfBonusRewards) {
        gameOverScreenPanel = new JPanel();
        gameOverScreenPanel.setLayout(new BoxLayout(gameOverScreenPanel, BoxLayout.PAGE_AXIS));

        UIUtils.addSpace(gameOverScreenPanel, 300, 60);

        //Game Over Text
        JLabel gameOverText = new JLabel("Game Over");
        gameOverText.setFont(heading);
        gameOverText.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameOverScreenPanel.add(gameOverText);
        UIUtils.addSpace(gameOverScreenPanel, 300, 60);

        //Score Text
        JLabel scoreText = new JLabel("Score: " + score); //to change later
        scoreText.setFont(plainArial35);
        scoreText.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameOverScreenPanel.add(scoreText);
        UIUtils.addSpace(gameOverScreenPanel, 300, 60);

        //Time Text
        JLabel timeText = new JLabel("Time: " + timeInSeconds / 60 + ":" + timeInSeconds % 60); //to change later
        timeText.setFont(plainArial35);
        timeText.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameOverScreenPanel.add(timeText);
        UIUtils.addSpace(gameOverScreenPanel, 300, 60);

        //Reward Panel
        JPanel rewardPanel = new JPanel();
        rewardPanel.setLayout(new BoxLayout(rewardPanel, BoxLayout.X_AXIS));
        //Reward Image
        JLabel rewardImageLabel = new JLabel("Reward Image to be created"); //to change later
        //Reward Text
        JLabel rewardText = new JLabel(": " + numOfRewards + "/" + rewardCount); //to change later
        rewardText.setFont(plainArial35);
        rewardText.setAlignmentX(Component.CENTER_ALIGNMENT);
        rewardPanel.add(rewardImageLabel);
        rewardPanel.add(rewardText);
        gameOverScreenPanel.add(rewardPanel);
        UIUtils.addSpace(gameOverScreenPanel, 300, 60);

        //Bonus Reward Panel
        JPanel bonusRewardPanel = new JPanel();
        bonusRewardPanel.setLayout(new BoxLayout(bonusRewardPanel, BoxLayout.X_AXIS));
        //Bonus Reward Image
        JLabel bonusRewardImageLabel = new JLabel("Bonus Reward Image to be created");//to change later
        //BonusReward Text
        JLabel bonusRewardText = new JLabel(": " + numOfBonusRewards);
        bonusRewardText.setFont(plainArial35);
        bonusRewardText.setAlignmentX(Component.CENTER_ALIGNMENT);
        bonusRewardPanel.add(bonusRewardImageLabel);
        bonusRewardPanel.add(bonusRewardText);
        gameOverScreenPanel.add(bonusRewardPanel);
        UIUtils.addSpace(gameOverScreenPanel, 300, 60);

        //Play Again Button
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().remove(gameOverScreenPanel);
                addGamePlayScreen();
                revalidate();
            }
        });
        gameOverScreenPanel.add(playAgainButton);
        UIUtils.addSpace(gameOverScreenPanel, 300, 60);

        //Quit Button
        JButton quitButton = new JButton("Quit");
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().remove(gameOverScreenPanel);
                addTitleScreen();
                revalidate();
            }
        });
        gameOverScreenPanel.add(quitButton);
        return gameOverScreenPanel;
    }
}

