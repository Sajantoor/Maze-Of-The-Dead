package reward;

/**
 * Traps that are found in the maze
 *
 * @author Sajan Toor
 */
public enum RewardType {
    /**
     * Regular reward type
     */
    REGULAR(0),
    /**
     * Bonus reward type type
     */
    BONUS(1);

    private int value;

    RewardType(int value) {
        this.value = value;
    }
}