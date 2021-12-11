package ui.Frames;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the main frame for the game
 * This frame remains full screen
 *
 * @author Dylan Young
 */
public class GameFrame extends JFrame {
    /**
     * This represents the main frame for the game
     */
    public GameFrame() {
        this.setTitle("Maze of The Dead");
        this.setLayout(new BorderLayout());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setUndecorated(true);
    }
}
