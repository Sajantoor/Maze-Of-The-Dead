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

    private SpriteIcons(){
    }


    /**
     * returns the Path sprite
     * @param width the width of the image
     * @param height the height of the image
     * @return the Path sprite
     */
    public static ImageIcon getPath(int width, int height) {
        if(path == null || width != path.getIconWidth() || height != path.getIconHeight())
            path = new ImageIcon(new ImageIcon(PATH_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return path;
    }

    /**
     * returns the Wall sprite
     * @param width the width of the image
     * @param height the height of the image
     * @return the Wall sprite
     */
    public static ImageIcon getWall(int width, int height) {
        if(wall == null || width != wall.getIconWidth() || height != wall.getIconHeight())
            wall = new ImageIcon(new ImageIcon(WALL_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return wall;
    }

    /**
     * returns the Player sprites
     *
     * @param width the width of the image
     * @param height the height of the image
     * @return the Player sprites
     */
    public static ImageIcon[] getPerson(int width, int height) {
        if(persons[0] == null || width != persons[0].getIconWidth() || height != persons[0].getIconHeight()) {
            ImageIcon person1 = new ImageIcon(new ImageIcon(PLAYER_UP_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
            persons[0] = person1;
            ImageIcon person2 = new ImageIcon(new ImageIcon(PLAYER_DOWN_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
            persons[1] = person2;
            ImageIcon person3 = new ImageIcon(new ImageIcon(PLAYER_LEFT_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
            persons[2] = person3;
            ImageIcon person4 = new ImageIcon(new ImageIcon(PLAYER_RIGHT_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
            persons[3] = person4;
        }
        return persons;
    }

    /**
     * returns the Zombie sprites
     *
     * @param width the width of the image
     * @param height the height of the image
     * @return the Zombie sprites
     */
    public static ImageIcon[] getEnemy(int width, int height) {
        if(enemies[0] == null || width != enemies[0].getIconWidth() || height != enemies[0].getIconHeight()) {
            ImageIcon enemy1 = new ImageIcon(new ImageIcon(ENEMY_UP_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
            enemies[0] = enemy1;
            ImageIcon enemy2 = new ImageIcon(new ImageIcon(ENEMY_DOWN_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
            enemies[1] = enemy2;
            ImageIcon enemy3 = new ImageIcon(new ImageIcon(ENEMY_LEFT_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
            enemies[2] = enemy3;
            ImageIcon enemy4 = new ImageIcon(new ImageIcon(ENEMY_RIGHT_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
            enemies[3] = enemy4;
        }
        return enemies;
    }

    /**
     * returns the Reward sprite
     *
     * @param width the width of the image
     * @param height the height of the image
     * @return the Reward sprite
     */
    public static ImageIcon getReward(int width, int height) {
        if(reward == null || width != reward.getIconWidth() || height != reward.getIconHeight())
            reward = new ImageIcon(new ImageIcon(REWARD_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return reward;
    }

    /**
     * returns the BonusReward sprite
     * @param width the width of the image
     * @param height the height of the image
     * @return the BonusReward sprite
     */
    public static ImageIcon getBonusReward(int width, int height) {
        if(bonusReward == null || width != bonusReward.getIconWidth() || height != bonusReward.getIconHeight())
            bonusReward = new ImageIcon(new ImageIcon(BONUS_REWARD_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return bonusReward;
    }

    /**
     * returns the TrapFall sprite
     * @param width the width of the image
     * @param height the height of the image
     * @return the TrapFall sprite
     */
    public static ImageIcon getTrapFall(int width, int height) {
        if(trapFall == null || width != trapFall.getIconWidth() || height != trapFall.getIconHeight())
            trapFall = new ImageIcon(new ImageIcon(TRAP_FALL_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return trapFall;
    }

    /**
     * returns the BoobyTrap sprite
     * @param width the width of the image
     * @param height the height of the image
     * @return the BoobyTrap sprite
     */
    public static ImageIcon getBoobyTrap(int width, int height) {
        if(boobyTrap == null || width != boobyTrap.getIconWidth() || height != boobyTrap.getIconHeight())
            boobyTrap = new ImageIcon(new ImageIcon(BOOBY_TRAP_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return boobyTrap;
    }
    /**
     * returns the Background image
     * @param width the width of the image
     * @param height the height of the image
     * @return the Background image
     */
    public static ImageIcon getBackgroundImage(int width, int height){
        if(background == null || width != background.getIconWidth() || height != background.getIconHeight()) {
            background = new ImageIcon(new ImageIcon(BACKGROUND_LOCATION).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        }
        return background;
    }
}
