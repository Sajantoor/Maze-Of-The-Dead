package game;

import java.util.ArrayList;

import character.CharacterModel;
import character.Player;
import maze.Cell;
import maze.Maze;
import reward.BonusReward;
import reward.Reward;
import reward.Trap;
import utilities.Constants;
import utilities.Position;

/**
 * This class handles the game logic, for example, has the player won, collides
 * with anything, etc.
 * 
 * @author Sajan Toor
 */
public class GameLogic {
    private static GameLogic instance = null;
    private boolean hasCollectedAllRewards;

    private GameLogic() {
        instance = this;
        hasCollectedAllRewards = false;
    }

    /**
     * This method returns the instance of GameLogic (Singleton).
     * 
     * @return instance of GameLogic
     */
    public static GameLogic getInstance() {
        if (instance == null)
            new GameLogic();

        return instance;
    }

    /**
     * Game loop that runs every 'tick' Involves player input, collidables and
     * checking if the game is over
     */
    protected void updateGame() {
        hasWon();
        GameController gc = GameController.getInstance();

        if (gc.getIsRunning() && !gc.isPaused()) {
            GameInput.getInstance().movePlayer();
            checkCollidables();
        }
    }

    /**
     * Checks if the score is below zero and if so, the game is lost Invokes the
     * lose game method.
     */
    private void checkScore() {
        if (Player.getInstance().getScore() < 0) {
            GameController.getInstance().loseGame();
        }
    }

    /**
     * Checks if the player has won and if so, invokes the win game method.
     */
    private void hasWon() {
        // check if player has collected all the rewards
        if (!hasCollectedAllRewards)
            return;

        // check if the player has reached the exit
        Position playerPosition = Player.getInstance().getPosition();
        boolean reachedEnd = Maze.getInstance().isEnd(playerPosition);
        if (reachedEnd)
            GameController.getInstance().winGame();
    }

    /**
     * Checks if the player has collected all regular rewards and if so returns
     * true. Used to set the hasCollectedAllRewards boolean.
     * 
     * @return True if the player has collected all regular rewards, false
     *         otherwise.
     */
    private boolean collectedAllRewards() {
        ArrayList<Reward> rewards = Entities.getInstance().getRewards();

        if (rewards.size() == 0) {
            return true;
        }

        for (Reward reward : rewards) {
            // if the points are equal to reward points, then they aren't bonus
            // rewards, so they must be collected.
            if (reward.getPoints() == Constants.rewardPoints) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the player has colided with a reward or a trap. If so, the
     * reward/trap is removed from the game and the player's score changes
     * accordingly.
     * 
     * @param object this is either a reward or a trap, casted according to cell
     *               type
     * @param cell   this is the cell the player has collided with
     */
    private synchronized void collided(Object object, Cell cell) {
        Player player = Player.getInstance();
        int scoreUpdate;
        Entities entities = Entities.getInstance();

        switch (cell.getCellType()) {
            case REWARD:
                Reward reward = (Reward) object;
                if (reward instanceof BonusReward)
                    entities.updateBonusRewardsCollected(1);

                scoreUpdate = reward.getPoints();
                entities.removeReward(reward);
                // check if player has collected all rewards
                hasCollectedAllRewards = collectedAllRewards();
                break;
            case TRAP:
                Trap trap = (Trap) object;
                scoreUpdate = ((Trap) object).getPoints();
                entities.removeTrap(trap);
                break;
            default:
                // just return here if there is no reward or trap
                return;
        }

        player.updateScore(scoreUpdate);
        cell.setEmpty();

        // If the score is decreasing, then check if the player has lost
        if (scoreUpdate < 0) {
            checkScore();
        }
    }

    /**
     * Checks if the player has collided with any collidables and acts accordingly.
     * For example, coliding with a reward increases the player's score and removes
     * the reward. Coliding with a zombie, makes the player lose the game.
     */
    private void checkCollidables() {
        Position position = Player.getInstance().getPosition();
        Cell cell = Maze.getInstance().getCell(position);

        // if the next position has an enemy, then the player loses the game
        Entities entities = Entities.getInstance();
        CharacterModel enemy = entities.getEnemy(position);

        if (enemy != null) {
            GameController.getInstance().loseGame();
        }

        // check for rewards or traps
        switch (cell.getCellType()) {
            // if the position has a reward, the player picks it up and adds to score
            case REWARD:
                Reward reward = entities.getReward(position);

                if (reward != null)
                    collided(reward, cell);
                break;
            case TRAP:
                Trap trap = entities.getTrap(position);

                if (trap != null)
                    collided(trap, cell);
                break;
            default:
                break;
        }
    }
}
