package utilities;

/**
 * Holds the constants of the game
 *
 * @author Sajan Toor
 */
public class Constants {
    // Maze related
    /**
     * The height of the maze
     */
    public static final int mazeHeight = 30;
    /**
     * The width of the maze
     */
    public static final int mazeWidth = 32;
    /**
     * The number of rooms to be generated in the maze
     */
    public static final int mazeRooms = 10;
    /**
     * The minimum size of the rooms
     */
    public static final int minRoomSize = 1;
    /**
     * The maximum size of the rooms
     */
    public static final int maxRoomSize = 4;
    // Player related
    /**
     * The starting X position of the player
     */
    public static final int playerStartX = 0;
    /**
     * The starting Y position of the player
     */
    public static final int playerStartY = 1;
    /**
     * The X position of the maze exit
     */
    public static final int playerEndX = mazeWidth - 1;
    /**
     * The Y position of the maze exit
     */
    public static final int playerEndY = mazeHeight - 2;
    // Leaderboard related
    /**
     * The number of spaces in the leaderboard
     */
    public static final int leaderboardCapacity = 5;
    /**
     * The file location of the current leaderboard
     */
    public static final String leaderboardFile = "src/main/java/leaderboard/leaderBoard.txt";
    // Entities related
    /**
     * The number of points that will be removed by the player in they step on a
     * booby trap
     */
    public static final int boobyTrapDmg = -10;
    /**
     * The number of points that will be removed by the player in they step on a
     * trap fall
     */
    public static final int trapFallDmg = -20;
    /**
     * The number of booby traps that will be generated
     */
    public static final int boobyTrapCount = 5;
    /**
     * The number of trap falls that will be generated
     */
    public static final int trapFallCount = 3;
    /**
     * The number of rewards that will be generated
     */
    public static final int rewardCount = 3;
    /**
     * The number of points the rewards are worth
     */
    public static final int regularRewardPoints = 10;
    /**
     * The number of enemies that will be generated
     */
    public static final int enemyCount = 2;
    /**
     * The number of points the rewards are worth
     */
    public static final int rewardPoints = 10;
    /**
     * The number of points the bonus reward is worth
     */
    public static final int bonusRewardPoints = 50;
    /**
     * The shortest amount of time that bonus reward will stay on the maze in
     * seconds
     */
    public static final int bonusRewardTimeLower = 10;
    /**
     * The longest amount of time that bonus reward will stay on the maze in seconds
     */
    public static final int bonusRewardTimeUpper = 20;
    /**
     * The chance that a bonus reward will be generated at a specific point in time
     */
    public static final int bonusRewardChance = 2;
    // Threads
    /**
     * The interval of time between each game logic update
     */
    public static final int gameLoopSleep = 100;
    /**
     * The amount of time between each time the enemies will move
     */
    public static final int enemyLoopSleep = 1000;
}
