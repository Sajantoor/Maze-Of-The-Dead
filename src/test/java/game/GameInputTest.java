package game;

import character.Player;
import maze.Maze;
import org.junit.Before;
import org.junit.BeforeClass;
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
        GI.resetMovement();
        player = Player.getInstance();
        player.resetPlayer();
        m = Maze.getInstance();
        m.regenerateMaze();
    }

    @AfterEach
    void reset() {
        GI.resetMovement();
        player.resetPlayer();
    }

    @Test
    void getInstance() {
        GameInput GI2 = GameInput.getInstance();
        GI2.resetMovement();
        GI.addMovement(RIGHT);
        //Both Instances must have the same arrayList, only the added Movement and STATIONARY movement
        //must be in the arrayList of both instances
        assertTrue(GI.checkMovement(RIGHT));
        assertFalse(GI.checkMovement(LEFT));
        assertFalse(GI.checkMovement(UP));
        assertFalse(GI.checkMovement(DOWN));
        assertTrue(GI.checkMovement(STATIONARY));
        assertTrue(GI2.checkMovement(RIGHT));
        assertFalse(GI2.checkMovement(LEFT));
        assertFalse(GI2.checkMovement(UP));
        assertFalse(GI2.checkMovement(DOWN));
        assertTrue(GI2.checkMovement(STATIONARY));
    }

    //movePlayer() test helper functions
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
        System.out.println(oldPos.toString());
        System.out.println(newPos.toString());
        System.out.println(player.getPosition().toString());
        //Player moves to latest added direction or does not move
        boolean playerMove = Objects.equals(player.getPosition().toString(), newPos.toString());
        boolean playerDidNotMove = Objects.equals(player.getPosition().toString(), oldPos.toString());
        boolean playerMoveRightOrStationary = playerMove || playerDidNotMove;
        assertTrue(playerMoveRightOrStationary);
    }


    @Test
    void movePlayer() {
        //Moving Player before adding any Movement (after resetting Movement)
        //The player's only movement must be STATIONARY
        GI.movePlayer();
        assertEquals(new Position(playerStartX, playerStartY), player.getPosition());
        //Testing if player moves right or does not move at all
        movePlayerCheck(RIGHT);
        //Testing if player moves left or does not move at all
        movePlayerCheck(LEFT);
        //Testing if player moves up or does not move at all
        movePlayerCheck(UP);
        //Testing if player moves down or does not move at all
        movePlayerCheck(DOWN);
        GI.resetMovement();
    }

    //addMovement(), removeMovement() and checkMovement() test helper functions
    private void checkTrue(Movement movement) {
        assertTrue(GI.checkMovement(movement));
    }

    private void checkFalse(Movement movement) {
        assertFalse(GI.checkMovement(movement));
    }


    @Test
    void addMovement() {
        //testing after adding RIGHT
        GI.addMovement(RIGHT);
        checkTrue(RIGHT);
        checkFalse(LEFT);
        checkFalse(UP);
        checkFalse(DOWN);
        //testing after adding LEFT
        GI.addMovement(LEFT);
        checkTrue(RIGHT);
        checkTrue(LEFT);
        checkFalse(UP);
        checkFalse(DOWN);
        //testing after adding UP
        GI.addMovement(UP);
        checkTrue(RIGHT);
        checkTrue(LEFT);
        checkTrue(UP);
        checkFalse(DOWN);
        //testing after adding DOWN
        GI.addMovement(DOWN);
        checkTrue(RIGHT);
        checkTrue(LEFT);
        checkTrue(UP);
        checkTrue(DOWN);
        //testing STATIONARY
        checkTrue(STATIONARY);
    }

    @Test
    void removeMovement() {
        //add all movements
        GI.addMovement(RIGHT);
        GI.addMovement(LEFT);
        GI.addMovement(UP);
        GI.addMovement(DOWN);
        //testing before removing anything
        checkTrue(RIGHT);
        checkTrue(LEFT);
        checkTrue(UP);
        checkTrue(DOWN);
        //testing after removing RIGHT
        GI.removeMovement(RIGHT);
        checkFalse(RIGHT);
        checkTrue(LEFT);
        checkTrue(UP);
        checkTrue(DOWN);
        //testing after removing LEFT
        GI.removeMovement(LEFT);
        checkFalse(RIGHT);
        checkFalse(LEFT);
        checkTrue(UP);
        checkTrue(DOWN);
        //testing after removing UP
        GI.removeMovement(UP);
        checkFalse(RIGHT);
        checkFalse(LEFT);
        checkFalse(UP);
        checkTrue(DOWN);
        //testing after removing DOWN
        GI.removeMovement(DOWN);
        checkFalse(RIGHT);
        checkFalse(LEFT);
        checkFalse(UP);
        checkFalse(DOWN);
    }

    @Test
    void checkMovement() {
        //checkMovement has been tested during the tests for addMovement() and removeMovement()
    }

    @Test
    void resetMovement() {
        //adding a movement to GI
        GI.addMovement(RIGHT);
        checkTrue(RIGHT);
        //adding another movement to GI
        GI.addMovement(LEFT);
        checkTrue(LEFT);
        //testing after resetting the GI
        GI.resetMovement();
        checkFalse(RIGHT);
        checkFalse(LEFT);
    }
}
