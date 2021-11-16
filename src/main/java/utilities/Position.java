package utilities;

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

        if (!Functions.equals(this, obj))
            return false;

        Position position = (Position) obj;
        if (this.x != position.x)
            return false;

        if (this.y != position.y)
            return false;

        return true;
    }
}
