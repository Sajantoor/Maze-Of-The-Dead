package ui.components;

import javax.swing.*;
import java.awt.*;

import static ui.components.SpritePathString.*;

/**
 * Creates the graphics for the game
 * Represents the Sprites used for the graphics of the game.
 *
 * @author Dylan Young
 */
public class SpriteIcons {
    private static ImageIcon path = null;
    private static ImageIcon wall = null;
    private static ImageIcon[] persons = new ImageIcon[4];
    private static ImageIcon[] enemies = new ImageIcon[4];
    private static ImageIcon reward = null;
    private static ImageIcon bonusReward = null;
    private static ImageIcon trapFall = null;
    private static ImageIcon boobyTrap = null;
    private static ImageIcon background = null;

    private SpriteIcons() {
    }

    /**
     * returns the Path sprite
     *
     * @param width  the width of the image
     * @param height the height of the image
     * @return the Path sprite
     */
    public static ImageIcon getPath(int width, int height) {
        if (isValidImage(path, width, height))
            path = new ImageIcon(
                    new ImageIcon(PATH_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return path;
    }

    /**
     * returns the Wall sprite
     *
     * @param width  the width of the image
     * @param height the height of the image
     * @return the Wall sprite
     */
    public static ImageIcon getWall(int width, int height) {
        if (isValidImage(wall, width, height))
            wall = new ImageIcon(
                    new ImageIcon(WALL_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return wall;
    }

    /**
     * returns the Player sprites
     *
     * @param width  the width of the image
     * @param height the height of the image
     * @return the Player sprites
     */
    public static ImageIcon[] getPerson(int width, int height) {
        if (isValidImage(persons[0], width, height)) {
            String[] arr = { PLAYER_UP_LOCATION,
                    PLAYER_DOWN_LOCATION,
                    PLAYER_LEFT_LOCATION,
                    PLAYER_RIGHT_LOCATION };
            addImageToArray(persons, arr, width, height);
        }
        return persons;
    }

    /**
     * returns the Zombie sprites
     *
     * @param width  the width of the image
     * @param height the height of the image
     * @return the Zombie sprites
     */
    public static ImageIcon[] getEnemy(int width, int height) {
        if (isValidImage(enemies[0], width, height)) {
            String[] arr = { ENEMY_UP_LOCATION,
                    ENEMY_DOWN_LOCATION,
                    ENEMY_LEFT_LOCATION,
                    ENEMY_RIGHT_LOCATION };
            addImageToArray(enemies, arr, width, height);
        }
        return enemies;
    }

    /**
     * returns the Reward sprite
     *
     * @param width  the width of the image
     * @param height the height of the image
     * @return the Reward sprite
     */
    public static ImageIcon getReward(int width, int height) {
        if (isValidImage(reward, width, height))
            reward = new ImageIcon(
                    new ImageIcon(REWARD_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return reward;
    }

    /**
     * returns the BonusReward sprite
     *
     * @param width  the width of the image
     * @param height the height of the image
     * @return the BonusReward sprite
     */
    public static ImageIcon getBonusReward(int width, int height) {
        if (isValidImage(bonusReward, width, height))
            bonusReward = new ImageIcon(new ImageIcon(BONUS_REWARD_LOCATION).getImage().getScaledInstance(width, height,
                    Image.SCALE_SMOOTH));
        return bonusReward;
    }

    /**
     * returns the TrapFall sprite
     *
     * @param width  the width of the image
     * @param height the height of the image
     * @return the TrapFall sprite
     */
    public static ImageIcon getTrapFall(int width, int height) {
        if (isValidImage(trapFall, width, height))
            trapFall = new ImageIcon(
                    new ImageIcon(TRAP_FALL_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return trapFall;
    }

    /**
     * returns the BoobyTrap sprite
     *
     * @param width  the width of the image
     * @param height the height of the image
     * @return the BoobyTrap sprite
     */
    public static ImageIcon getBoobyTrap(int width, int height) {
        if (isValidImage(boobyTrap, width, height))
            boobyTrap = new ImageIcon(
                    new ImageIcon(BOOBY_TRAP_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return boobyTrap;
    }

    /**
     * returns the Background image
     *
     * @param width  the width of the image
     * @param height the height of the image
     * @return the Background image
     */
    public static ImageIcon getBackgroundImage(int width, int height) {
        if (isValidImage(background, width, height)) {
            background = new ImageIcon(
                    new ImageIcon(BACKGROUND_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        }
        return background;
    }

    private static boolean isValidImage(ImageIcon icon, int width, int height) {
        return icon == null || width != icon.getIconWidth() || height != icon.getIconHeight();
    }

    private static void addImageToArray(ImageIcon[] icons, String[] arr, int width, int height) {
        for (int i = 0; i < arr.length; i++) {
            icons[i] = new ImageIcon(
                    new ImageIcon(arr[i]).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        }
    }
}
