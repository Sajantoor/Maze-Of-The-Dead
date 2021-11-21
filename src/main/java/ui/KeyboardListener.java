package ui;

import game.GameController;
import utilities.Movement;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static ui.GameUI.*;

/**
 * Detection component for the game which detects the inputs of the player
 *
 * @author Dylan Young
 */
public class KeyboardListener extends KeyAdapter {
    GameController gc =GameController.getInstance();
    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_W:
                if(!gc.checkMovement(Movement.UP)){
                    gc.addMovement(Movement.UP);
                }
                break;
            case KeyEvent.VK_A:
                if(!gc.checkMovement(Movement.LEFT)){
                    gc.addMovement(Movement.LEFT);
                }
                break;
            case KeyEvent.VK_S:
                if(!gc.checkMovement(Movement.DOWN)){
                    gc.addMovement(Movement.DOWN);
                }
                break;
            case KeyEvent.VK_D:
                if(!gc.checkMovement(Movement.RIGHT)){
                    gc.addMovement(Movement.RIGHT);
                }
                break;
            case KeyEvent.VK_ESCAPE:
                gc.pauseGame();
                addPauseScreen();
                getSubFrame().setVisible(true);
                getFrame().setEnabled(false);
                getFrame().setFocusable(false);
                revalidateSubScreen();
                break;
            default:
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_W:
                if(gc.checkMovement(Movement.UP)){
                    gc.removeMovement(Movement.UP);
                }
                break;
            case KeyEvent.VK_A:
                if(gc.checkMovement(Movement.LEFT)){
                    gc.removeMovement(Movement.LEFT);
                }
                break;
            case KeyEvent.VK_S:
                if(gc.checkMovement(Movement.DOWN)){
                    gc.removeMovement(Movement.DOWN);
                }
                break;
            case KeyEvent.VK_D:
                if(gc.checkMovement(Movement.RIGHT)){
                    gc.removeMovement(Movement.RIGHT);
                }
                break;
            default:
                break;
        }
    }

}
