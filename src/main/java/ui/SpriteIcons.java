package ui;

import javax.swing.*;
import java.awt.*;

public class SpriteIcons {
    private ImageIcon path = null;
    private ImageIcon wall = null;
    private ImageIcon[] persons = new ImageIcon[4];
    private ImageIcon[] enemies = new ImageIcon[4];
    private ImageIcon reward = null;
    private ImageIcon bonusReward = null;
    private ImageIcon trapFall = null;
    private ImageIcon boobyTrap = null;
    private String[] str = new String[]{"d", "l", "r", "u"};
    public SpriteIcons() {
        {
            path = new ImageIcon(new ImageIcon("src/main/java/ui/images/tile.png").getImage().getScaledInstance(UIConstants.cellWidth, UIConstants.cellHeight, Image.SCALE_SMOOTH));
            wall = new ImageIcon(new ImageIcon("src/main/java/ui/images/wall.png").getImage().getScaledInstance(UIConstants.cellWidth, UIConstants.cellHeight, Image.SCALE_SMOOTH));
            reward = new ImageIcon(new ImageIcon("src/main/java/ui/images/reward.png").getImage().getScaledInstance(UIConstants.cellWidth, UIConstants.cellHeight, Image.SCALE_SMOOTH));
            bonusReward = new ImageIcon(new ImageIcon("src/main/java/ui/images/bonus_reward.png").getImage().getScaledInstance(UIConstants.cellWidth, UIConstants.cellHeight, Image.SCALE_SMOOTH));
            trapFall = new ImageIcon(new ImageIcon("src/main/java/ui/images/trapFall.png").getImage().getScaledInstance(UIConstants.cellWidth, UIConstants.cellHeight, Image.SCALE_SMOOTH));
            boobyTrap = new ImageIcon(new ImageIcon("src/main/java/ui/images/boobyTrap.png").getImage().getScaledInstance(UIConstants.cellWidth, UIConstants.cellHeight, Image.SCALE_SMOOTH));
            for (int i = 0; i < str.length; i++){
                ImageIcon person = new ImageIcon(new ImageIcon("src/main/java/ui/images/person_" + str[i] + ".png").getImage().getScaledInstance(UIConstants.cellWidth, UIConstants.cellHeight, Image.SCALE_SMOOTH));
                persons[i] = person;
                ImageIcon enemy = new ImageIcon(new ImageIcon("src/main/java/ui/images/zombie_" + str[i] + ".png").getImage().getScaledInstance(UIConstants.cellWidth, UIConstants.cellHeight, Image.SCALE_SMOOTH));
                enemies[i] = enemy;
            }
        }
    }
    public ImageIcon getPath(){
        return path;
    }
    public ImageIcon getWall(){
        return wall;
    }

    public ImageIcon getPerson(int i) {
        return persons[i];
    }

    public ImageIcon getEnemy(int i) {
        return enemies[i];
    }

    public ImageIcon getReward() {
        return reward;
    }

    public ImageIcon getBonusReward() {
        return bonusReward;
    }
    public ImageIcon getTrapFall(){
        return trapFall;
    }
    public ImageIcon getBoobyTrap(){
        return boobyTrap;
    }
}
