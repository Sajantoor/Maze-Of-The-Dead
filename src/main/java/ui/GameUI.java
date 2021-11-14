package ui;

import javax.swing.*;

public class GameUI {
    private static JFrame frame;

    public GameUI(){
        //Full Screen
        frame = new JFrame("Maze of The Dead");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        addTitlesScreen();

        frame.setVisible(true);
    }
    public static JFrame getFrame(){
        return frame;
    }

    public static void addInstructionScreen(){
        InstructionScreen instructionScreen = new InstructionScreen();
        frame.add(instructionScreen.getInstructionScreen());
    }
    public static void addTitlesScreen(){
        TitleScreen titleScreen = new TitleScreen();
        frame.add(titleScreen.getTitleScreen());
    }

    //create static methods to call for your panel below. Follow format above

}
