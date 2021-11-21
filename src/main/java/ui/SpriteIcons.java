package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Creates the graphics for the game
 *
 * @author Dylan Young
 */
public class SpriteIcons {
    private ImageIcon path = null;
    private ImageIcon wall = null;
    private ImageIcon[] persons = new ImageIcon[4];
    private ImageIcon[] enemies = new ImageIcon[4];
    private ImageIcon reward = null;
    private ImageIcon bonusReward = null;
    private ImageIcon trapFall = null;
    private ImageIcon boobyTrap = null;
    private ImageIcon background = null;
    private String[] str = new String[]{"d", "l", "r", "u"};

    /**
     * Represents the Sprites used for the graphics of the game.
     */
    public SpriteIcons() {
        {
            path = new ImageIcon(new ImageIcon("src/main/java/ui/images/tile.png").getImage().getScaledInstance(UIConstants.cellWidth, UIConstants.cellHeight, Image.SCALE_SMOOTH));
            wall = new ImageIcon(new ImageIcon("src/main/java/ui/images/wall.png").getImage().getScaledInstance(UIConstants.cellWidth, UIConstants.cellHeight, Image.SCALE_SMOOTH));
            reward = new ImageIcon(new ImageIcon("src/main/java/ui/images/reward.png").getImage().getScaledInstance(UIConstants.cellWidth, UIConstants.cellHeight, Image.SCALE_SMOOTH));
            bonusReward = new ImageIcon(new ImageIcon("src/main/java/ui/images/bonus_reward.png").getImage().getScaledInstance(UIConstants.cellWidth, UIConstants.cellHeight, Image.SCALE_SMOOTH));
            trapFall = new ImageIcon(new ImageIcon("src/main/java/ui/images/trapFall.png").getImage().getScaledInstance(UIConstants.cellWidth, UIConstants.cellHeight, Image.SCALE_SMOOTH));
            boobyTrap = new ImageIcon(new ImageIcon("src/main/java/ui/images/boobyTrap.png").getImage().getScaledInstance(UIConstants.cellWidth, UIConstants.cellHeight, Image.SCALE_SMOOTH));
            for (int i = 0; i < str.length; i++) {
                ImageIcon person = new ImageIcon(new ImageIcon("src/main/java/ui/images/person_" + str[i] + ".png").getImage().getScaledInstance(UIConstants.cellWidth, UIConstants.cellHeight, Image.SCALE_SMOOTH));
                persons[i] = person;
                ImageIcon enemy = new ImageIcon(new ImageIcon("src/main/java/ui/images/zombie_" + str[i] + ".png").getImage().getScaledInstance(UIConstants.cellWidth, UIConstants.cellHeight, Image.SCALE_SMOOTH));
                enemies[i] = enemy;
            }
            background = new ImageIcon(new ImageIcon("src/main/java/ui/images/background.jpg").getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH));
        }
    }

    /**
     * returns the Path sprite
     *
     * @return the Path sprite
     */
    public ImageIcon getPath() {
        return path;
    }

    /**
     * returns the Wall sprite
     *
     * @return the Wall sprite
     */
    public ImageIcon getWall() {
        return wall;
    }

    /**
     * returns the Player sprites
     *
     * @param i the position in the person sprite array to get one of the sprites for the player
     * @return the Player sprites
     */
    public ImageIcon getPerson(int i) {
        return persons[i];
    }

    /**
     * returns the Zombie sprites
     *
     * @param i the position in the person sprite array to get one of the sprites for the zombie
     * @return the Zombie sprites
     */
    public ImageIcon getEnemy(int i) {
        return enemies[i];
    }

    /**
     * returns the Reward sprite
     *
     * @return the Reward sprite
     */
    public ImageIcon getReward() {
        return reward;
    }

    /**
     * returns the BonusReward sprite
     *
     * @return the BonusReward sprite
     */
    public ImageIcon getBonusReward() {
        return bonusReward;
    }

    /**
     * returns the TrapFall sprite
     *
     * @return the TrapFall sprite
     */
    public ImageIcon getTrapFall() {
        return trapFall;
    }

    /**
     * returns the BoobyTrap sprite
     *
     * @return the BoobyTrap sprite
     */
    public ImageIcon getBoobyTrap() {
        return boobyTrap;
    }
    public ImageIcon getBackground(){
        return background;
    }
}
