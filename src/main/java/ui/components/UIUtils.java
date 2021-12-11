package ui.components;

import javax.swing.*;
import java.awt.*;

/**
 * Helper methods for the UI
 *
 * @author Dylan Young
 */
public class UIUtils {
    /**
     * Changes the layout for the button
     *
     * @param button a clickable component of the UI
     * @see JButton
     */
    public static void buttonLayout(JButton button) {
        button.setFont(UIConstants.boldArial35);
        button.setFocusPainted(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.RED);
    }

    /**
     * Add a spacer on the panel to align the components
     *
     * @param panel  holds the components of a UI
     * @param width  width of the space to be created
     * @param height height of the space to be created
     * @see JPanel
     */
    public static void addSpace(JPanel panel, int width, int height) {
        panel.add(Box.createRigidArea(new Dimension(width, height)));
    }

    /**
     * returns the time in a m:ss format
     * The minute side doesn't have a digit limit
     *
     * @param time the time that passes in the game
     * @return the time in a m:ss format
     */
    public static String formatTime(long time) {
        String timeStr = "" + (time % 60);
        if (timeStr.length() == 1) {
            timeStr = "0" + timeStr;
        }
        timeStr = "" + (time / 60) + ":" + timeStr;
        return timeStr;
    }

    /**
     * Returns a string format the collected reward out of the total number of
     * rewards
     *
     * @param maxReward            the max rewards generated for the game
     * @param rewardCollected      the number of rewards collected
     * @param numBonusReward       the total number of bonus rewards
     * @param bonusRewardCollected the number of bonus rewards collected
     * @return a string format the collected reward out of the total number of
     *         rewards
     */
    public static String formatRewardCount(int maxReward, int rewardCollected, int numBonusReward,
            int bonusRewardCollected) {
        // calculating number of rewards collected
        return "" + (maxReward - rewardCollected + numBonusReward - bonusRewardCollected) + "/" + maxReward;
    }

}
