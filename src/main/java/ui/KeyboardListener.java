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
    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_W:
                if(!GameController.getInstance().checkMovement(Movement.UP)){
                    GameController.getInstance().addMovement(Movement.UP);
                }
                break;
            case KeyEvent.VK_A:
                if(!GameController.getInstance().checkMovement(Movement.LEFT)){
                    GameController.getInstance().addMovement(Movement.LEFT);
                }
                break;
            case KeyEvent.VK_S:
                if(!GameController.getInstance().checkMovement(Movement.DOWN)){
                    GameController.getInstance().addMovement(Movement.DOWN);
                }
                break;
            case KeyEvent.VK_D:
                if(!GameController.getInstance().checkMovement(Movement.RIGHT)){
                    GameController.getInstance().addMovement(Movement.RIGHT);
                }
                break;
            case KeyEvent.VK_ESCAPE:
                if(GameController.getInstance().getIsRunning()) {
                    GameController.getInstance().pauseGame();
                    addPauseScreen();
                    getSubFrame().setVisible(true);
                    getFrame().setEnabled(false);
                    revalidateSubScreen();
                }
                break;
            default:
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_W:
                if(GameController.getInstance().checkMovement(Movement.UP)){
                    GameController.getInstance().removeMovement(Movement.UP);
                }
                break;
            case KeyEvent.VK_A:
                if(GameController.getInstance().checkMovement(Movement.LEFT)){
                    GameController.getInstance().removeMovement(Movement.LEFT);
                }
                break;
            case KeyEvent.VK_S:
                if(GameController.getInstance().checkMovement(Movement.DOWN)){
                    GameController.getInstance().removeMovement(Movement.DOWN);
                }
                break;
            case KeyEvent.VK_D:
                if(GameController.getInstance().checkMovement(Movement.RIGHT)){
                    GameController.getInstance().removeMovement(Movement.RIGHT);
                }
                break;
            default:
                break;
        }
    }

}
