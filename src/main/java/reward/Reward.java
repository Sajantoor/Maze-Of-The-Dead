package reward;

import utilities.Constants;
import utilities.Position;

/**
 * Represents the reward
 *
 * @author Kaung Si Thu
 * @see Position
 */
public class Reward {

    private Position position;
    private int points;

    /**
     * Reward constructor function
     *
     * @param position Position of the reward
     * @param points   Points offered by the reward
     * @see Position
     */
    public Reward(Position position, int points) {
        this.position = position;
        this.points = points;
    }

    /**
     * Reward constructor function
     *
     * @param position Position of the reward
     * @see Position
     */
    public Reward(Position position) {
        this.position = position;
        this.points = Constants.regularRewardPoints;
    }

    /**
     * Return the position of the reward
     *
     * @return Position of the reward
     * @see Position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Set the position of the reward
     *
     * @param position Position to be set to the reward
     * @see Position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Return the points offered by the reward
     *
     * @return Points offered by the reward
     */
    public int getPoints() {
        return points;
    }

    /**
     * Set the points offered by the reward
     *
     * @param points Points offered by the reward
     */
    public void setPoints(int points) {
        this.points = points;
    }
}
