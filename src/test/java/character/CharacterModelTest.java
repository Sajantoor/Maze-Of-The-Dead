package character;

import utilities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterModelTest {
    private CharacterModel characterModel;

    @BeforeEach
    void setUp() {
        characterModel = new CharacterModel(new Position(10, 10));
    }

    @Test
    void getPosition() {
        Position outcome = characterModel.getPosition();
        Position testPos = new Position(10, 10);
        assertEquals(testPos, outcome);
    }

    @Test
    void setPosition() {
        // for setter with position parameter
        Position testPos = new Position(100, 100);
        characterModel.setPosition(testPos);
        Position outcome = characterModel.getPosition();
        assertEquals(testPos, outcome);

        // for setter with x,y coordinates as parameter
        characterModel.setPosition(200, 200);
        Position result = characterModel.getPosition();
        assertEquals((new Position(200, 200)), result);
    }

    @Test
    void moveForward() {
        Position testPos = new Position(10, 9);
        characterModel.moveForward();
        Position outcome = characterModel.getPosition();
        assertEquals(testPos, outcome);
    }

    @Test
    void moveBackward() {
        Position testPos = new Position(10, 11);
        characterModel.moveBackward();
        Position outcome = characterModel.getPosition();
        assertEquals(testPos, outcome);
    }

    @Test
    void moveLeft() {
        Position testPos = new Position(9, 10);
        characterModel.moveLeft();
        Position outcome = characterModel.getPosition();
        assertEquals(testPos, outcome);
    }

    @Test
    void moveRight() {
        Position testPos = new Position(11, 10);
        characterModel.moveRight();
        Position outcome = characterModel.getPosition();
        assertEquals(testPos, outcome);
    }

}
