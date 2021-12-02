package game;

/**
 * This class is used to keep track of the time in the game.
 * 
 * @author Sajan Toor
 */
public class Timer {
    private static Timer instance = null;
    private long timeElapsed;

    private Timer() {
        instance = this;
        timeElapsed = 0;
    }

    /**
     * This method is used to get the instance of the Timer class (Singleton).
     * 
     * @return The instance of the Timer class.
     */
    public static Timer getInstance() {
        if (instance == null)
            new Timer();

        return instance;
    }

    /**
     * This method sets the time (in seconds) elapsed since the start of the game.
     */
    protected void updateTime() {
        GameController gameController = GameController.getInstance();

        if (gameController.isPaused())
            return;

        timeElapsed++;
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

    /**
     * This method resets the timer to 0.
     */
    protected void reset() {
        timeElapsed = 0;
    }
}
