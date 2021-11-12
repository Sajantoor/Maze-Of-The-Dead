package ui;

import javax.swing.*;

public class GameUI {
    private JFrame frame;
    private TitleScreen titleScreen;
    private InstructionScreen instructionScreen;
    public GameUI(){
        titleScreen = new TitleScreen();
        instructionScreen = new InstructionScreen();

        //Full Screen
        frame = new JFrame("Maze of The Dead");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        //comment and uncomment to test screens
        frame = titleScreen.getTitleScreen(frame);
        //frame = instructionScreen.getInstructionsScreen(frame);

        frame.setVisible(true);
    }
}
