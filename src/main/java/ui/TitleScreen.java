package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.GameUI.addInstructionScreen;
import static ui.GameUI.getFrame;

public class TitleScreen {
    private JPanel titleScreenPanel;
    private JLabel titleLabel;
    private JButton startButton;
    private JButton quitButton;
    private JButton instructionButton;

    /**
     *
     * @return the title screen panel
     */
    public JPanel getTitleScreen() {
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
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().remove(titleScreenPanel);
                getFrame().revalidate();
                getFrame().repaint();
            }
        });
        UIUtils.buttonLayout(startButton);
        titleScreenPanel.add(startButton);

        UIUtils.addSpace(titleScreenPanel, 0, 30);

        //Quit button
        quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        UIUtils.buttonLayout(quitButton);
        titleScreenPanel.add(quitButton);
        UIUtils.addSpace(titleScreenPanel, 0, 30);

        //Instruction button
        instructionButton = new JButton("Instruction");
        instructionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().remove(titleScreenPanel);
                addInstructionScreen();
                getFrame().revalidate();
                getFrame().repaint();
            }
        });
        UIUtils.buttonLayout(instructionButton);
        titleScreenPanel.add(instructionButton);

        //adds the title panel to the static frame
        getFrame().add(titleScreenPanel);
        return titleScreenPanel;
    }

}
