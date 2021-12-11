package character;

import utilities.*;

/**
 * Controls the Position of the Character
 *
 * @author Kaung Si Thu
 * @see Position
 */
public class CharacterModel {
    private Position position;

    /**
     * CharacterModel constructor function
     *
     * @param pos Position of the character
     * @see Position
     */
    public CharacterModel(Position pos) {
        this.position = pos;
    }

    /**
     * Returns the Position of the character
     *
     * @return Position of the character
     * @see Position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the Position argument as the position of the character
     *
     * @param pos the Position instance containing x and y coordinates
     * @see Position
     */
    public void setPosition(Position pos) {
        this.position = pos;
    }

    /**
     * Sets the arguments as x and y coordinates of the Position of the character
     *
     * @param x x coordinate of a position
     * @param y y coordinate of a position
     * @see Position
     */
    public void setPosition(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    /**
     * Moves the character model in the specified direction
     * 
     * @param direction Defines how the character model will move
     */
    public void move(Movement direction) {
        switch (direction) {
            case UP -> position.setY(position.getY() - 1);
            case DOWN -> position.setY(position.getY() + 1);
            case LEFT -> position.setX(position.getX() - 1);
            case RIGHT -> position.setX(position.getX() + 1);
            default -> {
            }
        }
    }

    /**
     * Moves the character one unit up, changing the Position
     *
     * @see Position
     */
    public void moveForward() {
        this.move(Movement.UP);
    }

    /**
     * Moves the character one unit down, changing the Position
     *
     * @see Position
     */
    public void moveBackward() {
        this.move(Movement.DOWN);
    }

    /**
     * Moves the character to the left, changing the Position
     *
     * @see Position
     */
    public void moveLeft() {
        this.move(Movement.LEFT);
    }

    /**
     * Moves the character to the right, changing the Position
     *
     * @see Position
     */
    public void moveRight() {
        this.move(Movement.RIGHT);
    }
}