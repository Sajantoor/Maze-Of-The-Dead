package utilities;

/**
 * Movement types for characterModel and player movement
 * 
 */
public enum Movement {
    /**
     * Up Direction
     */
    UP(0),
    /**
     * Down Direction
     */
    DOWN(1),
    /**
     * Left Direction
     */
    LEFT(2),
    /**
     * Right Direction
     */
    RIGHT(3),
    /**
     * No movement
     */
    STATIONARY(4);

    private int value;

    Movement(int value) {
        this.value = value;
    }

}