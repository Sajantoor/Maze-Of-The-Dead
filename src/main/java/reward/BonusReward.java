package reward;

import utilities.Position;

/**
 * Represent the bonus reward
 *
 * @author Kaung Si Thu
 * @see Position
 */
public class BonusReward extends Reward {
    private long startTime;

    /**
     * BonusReward class constructor function
     *
     * @param position  Position of the bonus reward
     * @param points    Points offered by the bonus reward
     * @param startTime Time when the bonus reward appears
     * @see Position
     */
    public BonusReward(Position position, int points, long startTime) {
        super(position, points);
        this.startTime = startTime;
    }

    /**
     * Return the start time of the bonus reward
     *
     * @return Start time of the bonus reward
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Set the start time of the bonus reward
     *
     * @param startTime Start time of the bonus reward
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
