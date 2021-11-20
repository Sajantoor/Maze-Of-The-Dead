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

    public Trap(Position position, TrapType trapType) {
        super(position);
        int points;
        switch (trapType) {
            case BOOBYTRAP:
                points = Constants.boobyTrapDmg;
                break;
            case TRAPFALL:
                points = Constants.trapFallDmg;
                break;
            default:
                throw new IllegalArgumentException("Invalid TrapType");
        }

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
     * Set the type of the trap (Boobytrap, Trapfall)
     *
     * @param trapType The type of the trap (Boobytrap, Trapfall)
     * @see TrapType
     */
    public void setTrapType(TrapType trapType) {
        this.trapType = trapType;
    }

    public Position getPosition() {
        return super.getPosition();
    }

    public void setPosition(Position position) {
        setPosition(position);
    }
}
