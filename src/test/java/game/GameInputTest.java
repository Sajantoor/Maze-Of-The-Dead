package game;

import character.Player;
import maze.Maze;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.Movement;
import utilities.Position;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static utilities.Constants.playerStartX;
import static utilities.Constants.playerStartY;
import static utilities.Movement.*;

class GameInputTest {

    private GameInput GI;
    private Player player;
    private Maze m;

    @BeforeEach
    void setUp() {
        GI = GameInput.getInstance();
        GI.reset();
        player = Player.getInstance();
        player.reset();
        m = Maze.getInstance();
        m.regenerateMaze();
    }

    @AfterEach
    void reset() {
        GI.reset();
        player.reset();
    }

    @Test
    void getInstance() {
        // testing if the instance is not null
        assertNotNull(GI);
        GameInput GI2 = GameInput.getInstance();
        // testing if the two instances point to the same singleton object
        assertSame(GI, GI2);
    }

    // movePlayer() test helper functions
    private void movePlayerCheck(Movement movement) {
        Position oldPos = new Position(player.getPosition());
        GI.addMovement(movement);
        Position newPos;
        switch (movement) {
            case UP -> newPos = new Position(oldPos.getX(), oldPos.getY() - 1);
            case DOWN -> newPos = new Position(oldPos.getX(), oldPos.getY() + 1);
            case LEFT -> newPos = new Position(oldPos.getX() - 1, oldPos.getY());
            case RIGHT -> newPos = new Position(oldPos.getX() + 1, oldPos.getY());
            default -> newPos = new Position(oldPos);
        }
        GI.movePlayer();
        // Player moves to latest added direction or does not move
        boolean playerMove = Objects.equals(player.getPosition().toString(), newPos.toString());
        boolean playerDidNotMove = Objects.equals(player.getPosition().toString(), oldPos.toString());
        boolean playerMoveRightOrStationary = playerMove || playerDidNotMove;
        assertTrue(playerMoveRightOrStationary);
    }

    @Test
    void movePlayer() {
        // Moving Player before adding any Movement (after resetting Movement)
        // The player's only movement must be STATIONARY
        GI.movePlayer();
        // testing if the player's movement is STATIONARY
        assertEquals(new Position(playerStartX, playerStartY), player.getPosition());
        // Testing if player moves right or does not move at all
        movePlayerCheck(RIGHT);
        // Testing if player moves left or does not move at all
        movePlayerCheck(LEFT);
        // Testing if player moves up or does not move at all
        movePlayerCheck(UP);
        // Testing if player moves down or does not move at all
        movePlayerCheck(DOWN);
        GI.reset();
    }

    // addMovement(), removeMovement() and checkMovement() test helper functions
    private void checkHasMove(Movement movement) {
        assertTrue(GI.checkMovement(movement));
    }

    private void checkDoesNotHaveMove(Movement movement) {
        assertFalse(GI.checkMovement(movement));
    }

    @Test
    void addMovement() {
        // testing after adding RIGHT
        GI.addMovement(RIGHT);
        checkHasMove(RIGHT);
        checkDoesNotHaveMove(LEFT);
        checkDoesNotHaveMove(UP);
        checkDoesNotHaveMove(DOWN);
        // testing after adding LEFT
        GI.addMovement(LEFT);
        checkHasMove(RIGHT);
        checkHasMove(LEFT);
        checkDoesNotHaveMove(UP);
        checkDoesNotHaveMove(DOWN);
        // testing after adding UP
        GI.addMovement(UP);
        checkHasMove(RIGHT);
        checkHasMove(LEFT);
        checkHasMove(UP);
        checkDoesNotHaveMove(DOWN);
        // testing after adding DOWN
        GI.addMovement(DOWN);
        checkHasMove(RIGHT);
        checkHasMove(LEFT);
        checkHasMove(UP);
        checkHasMove(DOWN);
        // testing STATIONARY
        checkHasMove(STATIONARY);
    }

    @Test
    void removeMovement() {
        // add all movements
        GI.addMovement(RIGHT);
        GI.addMovement(LEFT);
        GI.addMovement(UP);
        GI.addMovement(DOWN);
        // testing before removing anything
        checkHasMove(RIGHT);
        checkHasMove(LEFT);
        checkHasMove(UP);
        checkHasMove(DOWN);
        // testing after removing RIGHT
        GI.removeMovement(RIGHT);
        checkDoesNotHaveMove(RIGHT);
        checkHasMove(LEFT);
        checkHasMove(UP);
        checkHasMove(DOWN);
        // testing after removing LEFT
        GI.removeMovement(LEFT);
        checkDoesNotHaveMove(RIGHT);
        checkDoesNotHaveMove(LEFT);
        checkHasMove(UP);
        checkHasMove(DOWN);
        // testing after removing UP
        GI.removeMovement(UP);
        checkDoesNotHaveMove(RIGHT);
        checkDoesNotHaveMove(LEFT);
        checkDoesNotHaveMove(UP);
        checkHasMove(DOWN);
        // testing after removing DOWN
        GI.removeMovement(DOWN);
        checkDoesNotHaveMove(RIGHT);
        checkDoesNotHaveMove(LEFT);
        checkDoesNotHaveMove(UP);
        checkDoesNotHaveMove(DOWN);
    }

    @Test
    void checkMovement() {
        // checkMovement has been tested during the tests for addMovement() and
        // removeMovement()
    }

    @Test
    void resetMovement() {
        // adding a movement to GI
        GI.addMovement(RIGHT);
        checkHasMove(RIGHT);
        // adding another movement to GI
        GI.addMovement(LEFT);
        checkHasMove(LEFT);
        // testing after resetting the GI
        GI.reset();
        checkDoesNotHaveMove(RIGHT);
        checkDoesNotHaveMove(LEFT);
    }
}
