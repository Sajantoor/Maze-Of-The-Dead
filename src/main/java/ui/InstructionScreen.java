package ui;

import javax.swing.*;
import java.awt.*;

import static ui.GameUI.getFrame;
import static ui.UIUtils.*;
import static ui.components.Buttons.*;
import static ui.components.Elements.*;

/**
 * Represents the Instruction screen
 *
 * @author Dylan Young
 */
public class InstructionScreen {
    private JPanel instructionScreenPanel;

    private JPanel topPanel;

    private JPanel movementPanel;
    private JPanel movementLeftPanel;
    private JPanel movementRightPanel;
    private JPanel movementRightPanelUpper;
    private JPanel movementRightPanelLower;
    private JLabel movementLabel;

    private JPanel lowerPanel;
    private JPanel collectPanel;
    private JPanel avoidPanel;
    private JLabel collectLabel;
    private JLabel avoidLabel;
    private JPanel rewardPanel;
    private JLabel rewardLabel;
    private JLabel rewardImageLabel;
    private JPanel bonusPanel;
    private JLabel bonusLabel;
    private JLabel bonusImageLabel;
    private JPanel zombiePanel;
    private JLabel zombieLabel;
    private JLabel zombieImageLabel;
    private JPanel boobyTrapPanel;
    private JLabel boobyTrapLabel;
    private JLabel boobyTrapImageLabel;
    private JPanel trapfallPanel;
    private JLabel trapfallLabel;
    private JLabel trapfallImageLabel;

    /**
     * returns the Instruction screen
     *
     * @return the Instruction screen
     * @see JPanel
     */
    public JPanel getInstructionScreen() {
        SpriteIcons s = new SpriteIcons();
        instructionScreenPanel = new JPanel();

        //Top Panel
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        topPanel.setPreferredSize(new Dimension(1920, 100));
        topPanel.setBackground(new Color(173, 216, 230));
        addSpace(topPanel, 820, 0);

        //How to play Title
        addTitle(topPanel, "How to Play");
        instructionScreenPanel.add(topPanel);
        getFrame().add(instructionScreenPanel);

        addSpace(instructionScreenPanel, 0, 400);

        //Movement area
        movementPanel = new JPanel();
        movementPanel.setLayout(new BoxLayout(movementPanel, BoxLayout.LINE_AXIS));
        movementPanel.setPreferredSize(new Dimension(1000, 120));

        //Left movement area
        movementLeftPanel = new JPanel();
        movementPanel.add(movementLeftPanel);

        //Right movement area
        movementRightPanel = new JPanel();
        movementRightPanel.setLayout(new BoxLayout(movementRightPanel, BoxLayout.Y_AXIS));
        movementPanel.add(movementRightPanel);

        //Right movement upper area
        movementRightPanelUpper = new JPanel();
        movementRightPanel.add(movementRightPanelUpper);

        //Right movement lower area
        movementRightPanelLower = new JPanel();
        movementRightPanel.add(movementRightPanelLower);

        //Movement Label
        movementLabel = new JLabel("Movement:");
        movementLabel.setFont(UIConstants.boldArial35);
        movementLeftPanel.add(movementLabel);

        //W key
        keyFormat(movementRightPanelUpper, " W ");

        //A key
        keyFormat(movementRightPanelLower, " A ");

        //S key
        keyFormat(movementRightPanelLower, " S ");

        //D key
        keyFormat(movementRightPanelLower, " D ");

        instructionScreenPanel.add(movementPanel);

        //Lower Panel
        lowerPanel = new JPanel();
        lowerPanel.setPreferredSize(new Dimension(1920, 200));

        //Collect Panel
        collectPanel = new JPanel();
        collectPanel.setLayout(new BoxLayout(collectPanel, BoxLayout.Y_AXIS));
        lowerPanel.add(collectPanel);

        addSpace(lowerPanel, 400, 0);

        //Avoid Panel
        avoidPanel = new JPanel();
        avoidPanel.setLayout(new BoxLayout(avoidPanel, BoxLayout.Y_AXIS));
        lowerPanel.add(avoidPanel);

        //Collect Label
        collectLabel = new JLabel("Collect:");
        collectLabel.setFont(UIConstants.boldArial35);
        collectPanel.add(collectLabel);

        //Reward Panel
        rewardPanel = new JPanel();
        collectPanel.add(rewardPanel);

        //Reward Image
        rewardImageLabel = new JLabel(s.getReward());
        rewardPanel.add(rewardImageLabel);

        addSpace(rewardPanel, 10, 0);

        //Reward Label
        rewardLabel = new JLabel("Collect All to escape (10)");
        rewardLabel.setFont(UIConstants.plainArial20);
        rewardPanel.add(rewardLabel);

        //Bonus Panel
        bonusPanel = new JPanel();
        collectPanel.add(bonusPanel);

        //Bonus Image
        bonusImageLabel = new JLabel(s.getBonusReward());
        bonusPanel.add(bonusImageLabel);

        addSpace(bonusPanel, 10, 0);

        //Bonus Label
        bonusLabel = new JLabel("Bonus (50)");
        bonusLabel.setFont(UIConstants.plainArial20);
        bonusPanel.add(bonusLabel);

        //Avoid Label
        avoidLabel = new JLabel("Avoid:");
        avoidLabel.setFont(UIConstants.boldArial35);
        avoidPanel.add(avoidLabel);

        //Zombie Panel
        zombiePanel = new JPanel();
        avoidPanel.add(zombiePanel);

        //Zombie Image
        zombieImageLabel = new JLabel(s.getEnemy(0));
        zombiePanel.add(zombieImageLabel);

        addSpace(zombiePanel, 10, 0);

        //Zombie Label
        zombieLabel = new JLabel("Zombies");
        zombieLabel.setFont(UIConstants.plainArial20);
        zombiePanel.add(zombieLabel);

        //Booby Trap Panel
        boobyTrapPanel = new JPanel();
        avoidPanel.add(boobyTrapPanel);

        //Booby Trap Image
        boobyTrapImageLabel = new JLabel(s.getBoobyTrap());
        boobyTrapPanel.add(boobyTrapImageLabel);

        addSpace(boobyTrapPanel, 10, 0);

        //Booby Trap Label
        boobyTrapLabel = new JLabel("Booty Trap (-10)");
        boobyTrapLabel.setFont(UIConstants.plainArial20);
        boobyTrapPanel.add(boobyTrapLabel);

        //Trapfall Panel
        trapfallPanel = new JPanel();
        avoidPanel.add(trapfallPanel);

        //Trapfall Image
        trapfallImageLabel = new JLabel(s.getTrapFall());
        trapfallPanel.add(trapfallImageLabel);

        addSpace(trapfallPanel, 10, 0);

        //Trapfall Label
        trapfallLabel = new JLabel("Trapfall (-20)");
        trapfallLabel.setFont(UIConstants.plainArial20);
        trapfallPanel.add(trapfallLabel);

        instructionScreenPanel.add(lowerPanel);
        addBackButton(instructionScreenPanel, "Back");

        return instructionScreenPanel;
    }

    private void keyFormat(JPanel panel, String letter) {
        JLabel keyLabel = new JLabel(letter);
        keyLabel.setFont(UIConstants.plainArial35);
        keyLabel.setBorder(UIConstants.keyBorder);
        panel.add(keyLabel);
    }
}
