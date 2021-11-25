package utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionsTest {

    @Test
    void updatePosition() {
        Position position = new Position(5, 10);
        position = Functions.updatePosition(position, Movement.UP);
        assertEquals(new Position(5, 9), position);
        position = Functions.updatePosition(position, Movement.LEFT);
        assertEquals(new Position(4, 9), position);
        position = Functions.updatePosition(position, Movement.DOWN);
        assertEquals(new Position(4, 10), position);
        position = Functions.updatePosition(position, Movement.RIGHT);
        assertEquals(new Position(5, 10), position);

    }

    @Test
    void getRandomNumber() {
        for (int i = 0; i < 1000; i++) {
            int num = Functions.getRandomNumber(0, 10);
            assertTrue(num >= 0 && num <= 10);
            int num2 = Functions.getRandomNumber(0, 10);
            assertFalse(num2 < 0 || num2 > 10);
        }
    }

    @Test
    void testGetRandomNumber() {
        for (int i = 0; i < 1000; i++) {
            long num = Functions.getRandomNumber(0, 10);
            assertTrue(num >= 0 && num <= 10);
            long num2 = Functions.getRandomNumber(0, 10);
            assertFalse(num2 < 0 || num2 > 10);
        }
    }
}