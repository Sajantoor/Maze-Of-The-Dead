package reward;

/**
 * Traps that are found in the maze
 *
 * @author Dylan Young
 */
public enum TrapType {
    /**
     * Booby trap trap type
     */
    BOOBYTRAP(0),
    /**
     * Trap fall trap type
     */
    TRAPFALL(1);

    private int value;

    TrapType(int value) {
        this.value = value;
    }
}