package ui;

import javax.swing.*;
import java.awt.*;

public class InstructionScreen {
    private JPanel instructionScreenPanel;

    private JPanel topPanel;
    private JLabel hTPLabel; //How to Play
    private JButton xButton;

    private JPanel movementPanel;
    private JPanel movementLeftPanel;
    private JPanel movementRightPanel;
    private JPanel movementRightPanelUpper;
    private JPanel movementRightPanelLower;
    private JLabel movementLabel;
    private JLabel wLabel;
    private JLabel aLabel;
    private JLabel sLabel;
    private JLabel dLabel;

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
    private JPanel bootyTrapPanel;
    private JLabel bootyTrapLabel;
    private JLabel bootyTrapImageLabel;
    private JPanel trapfallPanel;
    private JLabel trapfallLabel;
    private JLabel trapfallImageLabel;

    public JPanel getInstructionScreen() {
        instructionScreenPanel = new JPanel();

        //Top bar
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        topPanel.setPreferredSize(new Dimension(1920, 100));
        topPanel.setBackground(new Color(173, 216, 230));

        UIUtils.addSpace(topPanel,20, 0);

        //Close button
        xButton = new JButton("X");
        UIUtils.buttonLayout(xButton);
        topPanel.add(xButton);

        UIUtils.addSpace(topPanel,800, 0);

        //How to play heading
        hTPLabel = new JLabel("How to Play");
        hTPLabel.setFont(UIConstants.heading);
        topPanel.add(hTPLabel);
        instructionScreenPanel.add(topPanel);
        GameUI.getFrame().add(instructionScreenPanel);

        UIUtils.addSpace(instructionScreenPanel,0, 400);

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
        wLabel = new JLabel(" W ");
        wLabel.setFont(UIConstants.plainArial35);
        wLabel.setBorder(UIConstants.keyBorder);
        movementRightPanelUpper.add(wLabel);

        //A key
        aLabel = new JLabel(" A ");
        aLabel.setFont(UIConstants.plainArial35);
        aLabel.setBorder(UIConstants.keyBorder);
        movementRightPanelLower.add(aLabel);

        //S key
        sLabel = new JLabel(" S ");
        sLabel.setFont(UIConstants.plainArial35);
        sLabel.setBorder(UIConstants.keyBorder);
        movementRightPanelLower.add(sLabel);

        //D key
        dLabel = new JLabel(" D ");
        dLabel.setFont(UIConstants.plainArial35);
        dLabel.setBorder(UIConstants.keyBorder);
        movementRightPanelLower.add(dLabel);

        instructionScreenPanel.add(movementPanel);

        //Lower Panel
        lowerPanel = new JPanel();
        lowerPanel.setPreferredSize(new Dimension(1920,200));

        //Collect Panel
        collectPanel = new JPanel();
        collectPanel.setLayout(new BoxLayout(collectPanel, BoxLayout.Y_AXIS));
        lowerPanel.add(collectPanel);

        UIUtils.addSpace(lowerPanel,400, 0);

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
        rewardImageLabel = new JLabel("Reward Image to be created");
        rewardPanel.add(rewardImageLabel);

        UIUtils.addSpace(rewardPanel,10, 0);

        //Reward Label
        rewardLabel = new JLabel("Collect All to escape (10)");
        rewardLabel.setFont(UIConstants.plainArial20);
        rewardPanel.add(rewardLabel);

        //Bonus Panel
        bonusPanel = new JPanel();
        collectPanel.add(bonusPanel);

        //Bonus Image
        bonusImageLabel = new JLabel("Bonus Image to be created");
        bonusPanel.add(bonusImageLabel);

        UIUtils.addSpace(bonusPanel,10, 0);

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
        zombieImageLabel = new JLabel("Zombie Image");
        zombiePanel.add(zombieImageLabel);

        UIUtils.addSpace(zombiePanel,10, 0);

        //Zombie Label
        zombieLabel = new JLabel("Zombies");
        zombieLabel.setFont(UIConstants.plainArial20);
        zombiePanel.add(zombieLabel);

        //Booty Trap Panel
        bootyTrapPanel = new JPanel();
        avoidPanel.add(bootyTrapPanel);

        //Booty Trap Image
        bootyTrapImageLabel = new JLabel("Booty Trap Image");
        bootyTrapPanel.add(bootyTrapImageLabel);

        UIUtils.addSpace(bootyTrapPanel,10, 0);

        //Booty Trap Label
        bootyTrapLabel = new JLabel("Booty Trap (-10)");
        bootyTrapLabel.setFont(UIConstants.plainArial20);
        bootyTrapPanel.add(bootyTrapLabel);

        //Trapfall Panel
        trapfallPanel = new JPanel();
        avoidPanel.add(trapfallPanel);

        //Trapfall Image
        trapfallImageLabel = new JLabel("Trapfall Image");
        trapfallPanel.add(trapfallImageLabel);

        UIUtils.addSpace(trapfallPanel,10, 0);

        //Trapfall Label
        trapfallLabel = new JLabel("Trapfall (-20)");
        trapfallLabel.setFont(UIConstants.plainArial20);
        trapfallPanel.add(trapfallLabel);

        instructionScreenPanel.add(lowerPanel);

        return instructionScreenPanel;
    }
}
