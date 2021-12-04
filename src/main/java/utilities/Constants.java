package utilities;

/**
 * Holds the constants of the game
 *
 * @author Sajan Toor
 */
public class Constants {
    // Maze related
    public static final int mazeHeight = 30;
    public static final int mazeWidth = 32;
    public static final int mazeRooms = 10;
    public static final int minRoomSize = 1;
    public static final int maxRoomSize = 4;
    // Player related
    public static final int playerStartX = 0;
    public static final int playerStartY = 1;
    public static final int playerEndX = mazeWidth - 1;
    public static final int playerEndY = mazeHeight - 2;
    public static final int playerStartScore = 0;
    // Leaderboard related
    public static final int playerListSize = 5;
    public static final String leaderboardFile = "src/main/java/leaderboard/leaderBoard.txt";
    // Entities related
    public static final int boobyTrapDmg = -10;
    public static final int trapFallDmg = -20;
    public static final int boobyTrapCount = 5;
    public static final int trapFallCount = 3;
    public static final int rewardCount = 3;
    public static final int regularRewardPoints = 10;
    public static final int enemyCount = 2;
    public static final int rewardPoints = 10;
    public static final int bonusRewardPoints = 50;
    public static final int bonusRewardTimeLower = 10 * 1000;
    public static final int bonusRewardTimeUpper = 20 * 1000;
    public static final int bonusRewardChance = 2;
    // Threads
    public static final int gameLoopSleep = 100;
    public static final int enemyLoopSleep = 1000;
}
