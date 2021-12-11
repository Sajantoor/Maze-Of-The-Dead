package reward;

import utilities.Position;

/**
 * Represent the bonus reward
 *
 * @author Kaung Si Thu
 * @see Position
 */
public class BonusReward extends Reward {
    private long endTime;

    /**
     * BonusReward class constructor function
     *
     * @param position Position of the bonus reward
     * @param points   The point value of the bonus reward
     * @param endTime  Time when the bonus reward disappears
     * @see Position
     */
    public BonusReward(Position position, int points, long endTime) {
        super(position, points);
        this.endTime = endTime;
    }

    /**
     * Return the start time of the bonus reward
     *
     * @return End time of the bonus reward
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * Set the start time of the bonus reward
     *
     * @param endTime End time of the bonus reward
     */
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
