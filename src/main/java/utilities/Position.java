package utilities;

import java.util.Objects;

/**
 * This class represents a position in a 2D space.
 * 
 * @author Sajan Toor
 */
public class Position {
    private int x;
    private int y;

    /**
     * Constructor for position
     * 
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor for position
     * 
     * @param position the position to be stored
     */
    public Position(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    /**
     * Getter for x-coordinate
     * 
     * @return x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y-coordinate
     * 
     * @return y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Setter for x-coordinate
     * 
     * @param x x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter for y-coordinate
     * 
     * @param y y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof Position))
            return false;

        Position position = (Position) obj;
        if (this.x != position.x)
            return false;

        return this.y == position.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
