package ui.components;

import game.GameController;
import game.GameInput;
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
        GameInput input = GameInput.getInstance();
        GameController gc = GameController.getInstance();

        switch (event.getKeyCode()) {
            case KeyEvent.VK_W:
                if (!input.checkMovement(Movement.UP)) {
                    input.addMovement(Movement.UP);
                }
                break;
            case KeyEvent.VK_A:
                if (!input.checkMovement(Movement.LEFT)) {
                    input.addMovement(Movement.LEFT);
                }
                break;
            case KeyEvent.VK_S:
                if (!input.checkMovement(Movement.DOWN)) {
                    input.addMovement(Movement.DOWN);
                }
                break;
            case KeyEvent.VK_D:
                if (!input.checkMovement(Movement.RIGHT)) {
                    input.addMovement(Movement.RIGHT);
                }
                break;
            case KeyEvent.VK_ESCAPE:
                if (gc.getIsRunning()) {
                    gc.pauseGame();
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
        GameInput input = GameInput.getInstance();

        switch (event.getKeyCode()) {
            case KeyEvent.VK_W:
                if (input.checkMovement(Movement.UP)) {
                    input.removeMovement(Movement.UP);
                }
                break;
            case KeyEvent.VK_A:
                if (input.checkMovement(Movement.LEFT)) {
                    input.removeMovement(Movement.LEFT);
                }
                break;
            case KeyEvent.VK_S:
                if (input.checkMovement(Movement.DOWN)) {
                    input.removeMovement(Movement.DOWN);
                }
                break;
            case KeyEvent.VK_D:
                if (input.checkMovement(Movement.RIGHT)) {
                    input.removeMovement(Movement.RIGHT);
                }
                break;
            default:
                break;
        }
    }

}
