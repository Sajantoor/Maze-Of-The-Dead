package reward;

import utilities.Position;

public class Trap extends Reward {
    private TrapType trapType;

    public Trap(Position position, int points, TrapType trapType) {
        super(position, points);
        // TODO: Implement me!
    }

    public TrapType getTrapType() {
        return trapType;
    }

    public void setTrapType(TrapType trapType) {
        this.trapType = trapType;
    }

    public Position getPosition() {
        return getPosition();
    }

    public void setPosition(Position position) {
        setPosition(position);
    }
}
