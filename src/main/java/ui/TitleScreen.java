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
        addSpace(400);
        titleScreenPanel.setBackground(Color.BLACK);

        //Name of the game displayed
        titleLabel = new JLabel("Maze of The Dead");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleScreenPanel.add(titleLabel);

        addSpace(30);

        //Start Button
        startButton = new JButton("Start");
        buttonLayout(startButton);
        titleScreenPanel.add(startButton);

        addSpace(30);

        //Quit button
        quitButton = new JButton("Quit");
        buttonLayout(quitButton);
        titleScreenPanel.add(quitButton);
        addSpace(30);

        //Instruction button
        instructionButton = new JButton("Instruction");
        buttonLayout(instructionButton);
        titleScreenPanel.add(instructionButton);

        frame.add(titleScreenPanel);
        return frame;
    }

    public JPanel getTitleScreen() {
        return titleScreenPanel;
    }

    private void buttonLayout(JButton button) {
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 30));
        button.setFocusPainted(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setBackground(null);
    }

    private void addSpace(int height) {
        titleScreenPanel.add(Box.createRigidArea(new Dimension(500, height)));
    }

}
