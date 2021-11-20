package utilities;

public class Constants {
    // Maze related
    public static final int mazeHeight = 30;
    public static final int mazeWidth = 32;
    public static final int mazeRooms = 5;
    // Player related
    public static final int playerStartX = 0;
    public static final int playerStartY = 1;
    public static final int playerEndX = mazeWidth - 1;
    public static final int playerEndY = mazeHeight - 2;
    public static final int playerStartScore = 0;
    // Controls related
    public static final String playerMoveUp = "W";
    public static final String playerMoveDown = "S";
    public static final String playerMoveLeft = "A";
    public static final String playerMoveRight = "D";
    // Leaderboard related
    public static final int playerListSize = 5;
    public static final String leardboardFile = "LeaderBoard.txt";
    // Entities related
    public static final int boobyTrapDmg = -10;
    public static final int trapFallDmg = -20;
    public static final int boobyTrapCount = 5;
    public static final int trapFallCount = 3;
    public static final int rewardCount = 5;
    public static final int regularRewardPoints = 10;
    public static final int enemyCount = 3;
    public static final int rewardPoints = 10;
    public static final int bonusRewardPoints = 50;
    public static final int bonusRewardTimeLower = 10 * 1000;
    public static final int bonusRewardTimeUpper = 20 * 1000;
    public static final int bonusRewardChance = 5;
    // Threads
    public static final int gameLoopSleep = 100;
    public static final int enemyLoopSleep = 500;
}
