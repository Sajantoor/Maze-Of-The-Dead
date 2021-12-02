package character;

import utilities.Constants;
import utilities.Position;

/**
 * (Singleton Class) Represent the Player
 *
 * @author Kaung Si Thu
 */
public class Player extends CharacterModel {
    private int score;
    private static Player instance = null;

    private Player(Position position, int score) {
        super(position);
        this.score = score;
    }

    /**
     * Follows singleton pattern to create a player instance. Initialized the
     * player's position and score with default values
     *
     * @return player instance
     */
    public static Player getInstance() {
        if (instance == null) {
            Position pos = new Position(Constants.playerStartX, Constants.playerStartY);
            instance = new Player(pos, 0);
        }

        return instance;
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

    /**
     * Updates the score of the player by adding the argument to score
     * 
     * @param score Score to be added to the player's score
     */
    public void updateScore(int score) {
        this.score += score;
    }

    /**
     * Returns the player to its original state, i.e. resets position and score
     */
    public void reset() {
        this.score = 0;
        this.setPosition(new Position(Constants.playerStartX, Constants.playerStartY));
    }
}
