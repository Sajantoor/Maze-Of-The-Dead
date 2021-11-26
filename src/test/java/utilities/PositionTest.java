package utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

public class PositionTest {
    private Position pos;

    @BeforeEach
    public void setUp() {
        pos = new Position(1, 2);
    }

    @Test // Test the position getters
    public void testPositionGetters() {
        assertEquals(1, pos.getX());
        assertEquals(2, pos.getY());
    }

    @Test // Test the position setters
    public void testPositionSetters() {
        pos.setY(4);
        pos.setX(3);
        assertEquals(3, pos.getX());
        assertEquals(4, pos.getY());
    }

    @Test // Test the position copy constructor
    public void testPositionCopyConstructor() {
        Position copy = new Position(pos);
        assertEquals(1, copy.getX());
        assertEquals(2, copy.getY());
    }

    @Test // Test the position equals method
    public void testPositionEquals() {
        Position copy = new Position(pos);
        assertEquals(pos, copy);
    }

    @Test // Test equality of two different positions
    public void testPositionNotEquals() {
        Position copy = new Position(3, 4);
        assertEquals(false, pos.equals(copy));
    }

    @Test // Equality with object
    public void testPositionEqualsObject() {
        Object object = new Object();
        assertEquals(false, pos.equals(object));
    }
}
