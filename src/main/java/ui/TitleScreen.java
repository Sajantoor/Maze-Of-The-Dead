package ui;

import javax.swing.*;
import java.awt.*;

public class TitleScreen {
    private JPanel titleScreenPanel;
    private JLabel titleLabel;
    private JButton startButton;
    private JButton quitButton;
    private JButton instructionButton;

    public JFrame getTitleScreen(JFrame frame) {
        titleScreenPanel = new JPanel();
        titleScreenPanel.setLayout(new BoxLayout(titleScreenPanel, BoxLayout.PAGE_AXIS));
        UIUtils.addSpace(titleScreenPanel, 500, 400);

        //Name of the game displayed
        titleLabel = new JLabel("Maze of The Dead");
        titleLabel.setFont(UIConstants.heading);
        titleScreenPanel.add(titleLabel);

        UIUtils.addSpace(titleScreenPanel, 0, 30);

        //Start Button
        startButton = new JButton("Start");
        UIUtils.buttonLayout(startButton);
        titleScreenPanel.add(startButton);

        UIUtils.addSpace(titleScreenPanel, 0, 30);

        //Quit button
        quitButton = new JButton("Quit");
        UIUtils.buttonLayout(quitButton);
        titleScreenPanel.add(quitButton);
        UIUtils.addSpace(titleScreenPanel, 0, 30);

        //Instruction button
        instructionButton = new JButton("Instruction");
        UIUtils.buttonLayout(instructionButton);
        titleScreenPanel.add(instructionButton);

        frame.add(titleScreenPanel);
        return frame;
    }

    public JPanel getTitleScreen() {
        return titleScreenPanel;
    }

}
