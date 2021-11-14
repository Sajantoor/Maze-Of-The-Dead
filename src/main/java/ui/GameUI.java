package ui;

import javax.swing.*;

public class GameUI {
    private static JFrame frame;

    public GameUI(){
        //Full Screen
        frame = new JFrame("Maze of The Dead");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        addTitleScreen();

        frame.setVisible(true);
    }
    public static JFrame getFrame(){
        return frame;
    }

    public static void addInstructionScreen(){
        InstructionScreen instructionScreen = new InstructionScreen();
        frame.add(instructionScreen.getInstructionScreen());
    }
    public static void addTitleScreen(){
        TitleScreen titleScreen = new TitleScreen();
        frame.add(titleScreen.getTitleScreen());
    }

    public static void revalidate(){
        frame.revalidate();
        frame.repaint();
    }
    //create static methods to call for your panel below. Follow format above

}
