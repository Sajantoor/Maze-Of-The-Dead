package leaderboard;

/**
 * Stores the player's names and corresponding scores
 *
 * @author Maisha Supritee Chowdhury
 */
public class PlayerScore {
    private String name;
    private int score;

    /**
     * Sets the arguments as the name and score of the player
     *
     * @param name  name of the player
     * @param score score of the player
     */
    public PlayerScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Returns the name of the player
     *
     * @return name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the argument as the name of the player
     *
     * @param name name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the score of the player
     *
     * @return score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the argument as the score of the player
     *
     * @param score score of the player
     */
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return name + "#" + score;
    }
}
