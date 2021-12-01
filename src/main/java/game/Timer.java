package game;

import utilities.Constants;
import utilities.Functions;

public class Timer {
    private static Timer instance = null;
    private long timeElapsed;

    private Timer() {
        instance = this;
        timeElapsed = 0;
    }

    public static Timer getInstance() {
        if (instance == null)
            new Timer();

        return instance;
    }

    /**
     * This method sets the time (in seconds) elapsed since the start of the game.
     */
    public void updateTime() {
        GameController gameController = GameController.getInstance();

        if (gameController.isPaused())
            return;

        timeElapsed++;
        // check if any bonus rewards have expired
        Entities.getInstance().checkBonusRewardExpired();
        // have a random chance of generating a new bonus reward
        if (Functions.getRandomNumber(0, 100) < Constants.bonusRewardChance) {
            EntitiesGenerator.getInstance().generateBonusReward();
        }
    }

    /**
     * This method returns the time (in seconds) elapsed since the start of the
     * game.
     * 
     * @return Time (in seconds) elapsed since the start of the game
     */
    public long getTimeElapsed() {
        return timeElapsed;
    }

}
