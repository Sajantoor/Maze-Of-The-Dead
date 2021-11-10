package character;

import utilities.Position;

/**
 * (Singleton Class) Represent the Player
 *
 * @author Kaung Si Thu
 */
public class Player extends CharacterModel {
    private int score;
    private static Player singlePlayerInstance = null;

    private Player(Position position, int score) {
        super(position);
        this.score = score;
    }

    /**
     * Returns singleton player Instance
     *
     * @param pos   The starting Position of the player
     * @param score The starting Score of the player
     * @return singleton player Instance
     * @see Position
     */
    public static Player getInstance(Position pos, int score) {
        if (singlePlayerInstance == null) {
            singlePlayerInstance = new Player(pos, score);
        } else {
            singlePlayerInstance.setPosition(pos);
            singlePlayerInstance.setScore(score);
        }
        return singlePlayerInstance;
    }

    /**
     * Returns score of the player
     *
     * @return Score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the argument score as the score of the player
     *
     * @param score Score to be set to the player
     */
    public void setScore(int score) {
        this.score = score;
    }
}
