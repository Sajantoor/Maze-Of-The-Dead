package maze;

/**
 * Cell types that can be in a cell
 *
 * @author Dylan Young
 */
public enum CellType {
    /**
     * Wall type
     */
    WALL(0),
    /**
     * Path type
     */
    PATH(1),
    /**
     * Start type
     */
    START(2),
    /**
     * End type
     */
    END(3),
    /**
     * Reward type
     */
    REWARD(4),
    /**
     * Trap type
     */
    TRAP(5);

    private int value;

    CellType(int value) {
        this.value = value;
    }
}
