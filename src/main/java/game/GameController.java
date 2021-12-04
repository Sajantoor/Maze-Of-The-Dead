package game;

import java.util.ArrayList;

import character.Enemy;
import character.Player;
import maze.Maze;
import utilities.Constants;

/**
 * This class is responsible for running the game and handling the states of the
 * game (i.e paused, won, etc.), it also handles the game loop.
 *
 * @author Sajan Toor
 */
public class GameController {
    private static GameController instance = null;
    private boolean isRunning;
    private boolean hasWon;
    private boolean isPaused;
    private boolean quit;

    private GameController() {
        instance = this;
        isRunning = false;
        isPaused = false;
        quit = false;
    }

    /**
     * GameController follows the singleton design pattern. This method returns the
     * instance of the GameController.
     * 
     * @return Instance of GameController
     */
    public static GameController getInstance() {
        if (GameController.instance == null)
            new GameController();

        return GameController.instance;
    }

    /**
     * Sets whether or not the game is running
     *
     * @param isRunning True if the game is running, false otherwise
     */
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * Method to start the game. This will generate the maze and entities as well as
     * start the threads.
     */
    public void startGame() {
        setRunning(true);
        Timer.getInstance().reset();
        Player.getInstance().reset();
        GameInput.getInstance().reset();
        Maze.getInstance().regenerateMaze();
        startThreads();
    }

    /**
     * Starts the threads for the game, including game loop, enemy loop and time
     * loop.
     */
    private void startThreads() {
        GameLogic gameLogic = GameLogic.getInstance();

        Thread gameLoop = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    GameLogic.getInstance().updateGame();
                    try {
                        Thread.sleep(Constants.gameLoopSleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread enemyLoop = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    gameLogic.generateEnemyMovement();
                    try {
                        Thread.sleep(Constants.enemyLoopSleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread enemyRegenPath = new Thread(new Runnable() {
            @Override
            public void run() {
                Entities entities = Entities.getInstance();
                ArrayList<Enemy> enemies = entities.getEnemies();
                while (isRunning) {
                    for (int i = 0; i < enemies.size(); i++) {
                        Enemy enemy = enemies.get(i);
                        enemy.regeneratePath();
                    }

                    try {
                        Thread.sleep(Constants.gameLoopSleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread timeLoop = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    Timer.getInstance().updateTime();
                    gameLogic.bonusRewardLoop();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread bonusRewardLoop = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    gameLogic.bonusRewardLoop();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        timeLoop.start();
        gameLoop.start();
        enemyLoop.start();
        bonusRewardLoop.start();
        enemyRegenPath.start();
    }

    /**
     * Updates the winning state of the game.
     */
    private void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    /**
     * Checks whether the player has won the game.
     * 
     * @return True if the player has won the game, false otherwise.
     */
    public boolean getHasWon() {
        return hasWon;
    }

    /**
     * Ends the game, setting the running flag to false.
     */
    public void endGame() {
        setRunning(false);
    }

    /**
     * Pauses the game
     */
    public void pauseGame() {
        this.isPaused = true;
    }

    /**
     * Resumes the game
     */
    public void unpauseGame() {
        this.isPaused = false;
    }

    /**
     * Sets the win state of the game to true and ends the game
     */
    public void winGame() {
        setHasWon(true);
        endGame();
    }

    /**
     * Sets the win state of the game to false and ends the game
     */
    public void loseGame() {
        setHasWon(false);
        endGame();
    }

    /**
     * Sets quit to true to end the game
     */
    public void setQuit() {
        quit = true;
    }

    /**
     * Gets the value of the quit state
     * 
     * @return the value of quit state
     */
    public boolean getQuit() {
        return quit;
    }

    /**
     * Returns the value of the isPaused state
     * 
     * @return the value of isPaused state
     */
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * Returns the value of the isRunning state
     * 
     * @return the value of isRunning state
     */
    public boolean getIsRunning() {
        return isRunning;
    }
}
