package reward;

import utilities.Position;

public class BonusReward extends Reward {
    private long startTime;

    public BonusReward(Position position, int points, long startTime) {
        super(position, points);
        // TODO: Implement me!
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
