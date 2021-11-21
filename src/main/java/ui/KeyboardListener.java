package ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Detection component for the game which detects the inputs of the player
 *
 * @author Dylan Young
 */
public class KeyboardListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_W:
                System.out.println("w");
                break;
            case KeyEvent.VK_A:
                System.out.println("a");
                break;
            case KeyEvent.VK_S:
                System.out.println("s");
                break;
            case KeyEvent.VK_D:
                System.out.println("d");
                break;
            case KeyEvent.VK_ESCAPE:
                System.out.println("esc");
                break;
            default:
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_W:
                System.out.println("w");
                break;
            case KeyEvent.VK_A:
                System.out.println("a");
                break;
            case KeyEvent.VK_S:
                System.out.println("s");
                break;
            case KeyEvent.VK_D:
                System.out.println("d");
                break;
            case KeyEvent.VK_ESCAPE:
                System.out.println("esc");
                break;
            default:
                break;
        }
    }

}
