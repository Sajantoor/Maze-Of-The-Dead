package reward;

import utilities.Constants;
import utilities.Position;

/**
 * Represents the Trap class
 *
 * @author Kaung Si Thu
 * @see Position
 * @see TrapType
 */
public class Trap extends Reward {
    private TrapType trapType;

    /**
     * Represents the
     * 
     * @param position the position where the trap will be placed
     * @param trapType Type of the trap (Boobytrap, Trapfall)
     */
    public Trap(Position position, TrapType trapType) {
        super(position);
        int points = switch (trapType) {
            case BOOBYTRAP -> Constants.boobyTrapDmg;
            case TRAPFALL -> Constants.trapFallDmg;
            default -> throw new IllegalArgumentException("Invalid TrapType");
        };

        this.setPoints(points);
        this.trapType = trapType;
    }

    /**
     * Trap class constructor method
     *
     * @param position Position of the trap
     * @param points   Points taken by the trap
     * @param trapType Type of the trap (Boobytrap, Trapfall)
     * @see Position
     * @see TrapType
     */
    public Trap(Position position, int points, TrapType trapType) {
        super(position, points);
        this.trapType = trapType;
    }

    /**
     * Return the type of the trap (Boobytrap, Trapfall)
     *
     * @return Type of the trap (Boobytrap, Trapfall)
     * @see TrapType
     */
    public TrapType getTrapType() {
        return trapType;
    }

    /**
     * Checks if the trap is a booby trap
     * 
     * @return true if the trap is a booby trap, else false
     */
    public boolean isBoobyTrap() {
        return trapType == TrapType.BOOBYTRAP;
    }

    /**
     * Checks if the trap is a trap fall
     * 
     * @return true if the trap is a trap fall, else false
     */
    public boolean isTrapFall() {
        return trapType == TrapType.TRAPFALL;
    }

    /**
     * Set the type of the trap (Boobytrap, Trapfall)
     *
     * @param trapType The type of the trap (Boobytrap, Trapfall)
     * @see TrapType
     */
    public void setTrapType(TrapType trapType) {
        this.trapType = trapType;
    }

    /**
     * returns the Position instance containing x and y coordinates
     *
     * @return the Position instance containing x and y coordinates
     * @see Position
     */
    public Position getPosition() {
        return super.getPosition();
    }

    /**
     * Sets the Position argument as the position of the trap
     *
     * @param position Position to be set to the trap
     * @see Position
     */
    public void setPosition(Position position) {
        super.setPosition(position);
    }
}
