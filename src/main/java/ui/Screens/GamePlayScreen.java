package ui.Screens;

import game.GameController;
import game.Entities;
import game.Timer;
import maze.Cell;
import maze.Maze;
import reward.BonusReward;
import reward.Reward;
import reward.Trap;
import ui.components.KeyboardListener;
import utilities.Constants;

import javax.swing.*;

import character.Player;

import java.awt.*;

import static ui.GameUI.*;
import static ui.components.SpriteIcons.*;
import static ui.components.UIConstants.*;
import static ui.components.UIUtils.*;
import static utilities.Constants.*;

/**
 * Represents the GamePlayScreen
 *
 * @author Dylan Young
 */
public class GamePlayScreen extends JPanel {
    private JPanel infoPanel;
    private JPanel mazePanel;
    private JLabel[][] cellLabels;
    private static JPanel instance = null;

    private JLabel timerLabel;
    private JLabel rewardLabel;
    private JLabel scoreLabel;

    private ImageIcon[] playerIcons;
    private ImageIcon[] enemyIcons;

    /**
     * Represents the GamePlayScreen panel
     *
     * @see JPanel
     */
    private GamePlayScreen() {
        playerIcons = getPerson(cellWidth, cellHeight);
        enemyIcons = getEnemy(cellWidth, cellHeight);

        getFrame().addKeyListener(new KeyboardListener());
        GameController.getInstance().startGame();
        cellLabels = new JLabel[mazeWidth][Constants.mazeHeight];

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentY(Component.TOP_ALIGNMENT);
        this.addKeyListener(new KeyboardListener());

        infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(1920, 30));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
        infoPanel.setBackground(Color.LIGHT_GRAY);
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        infoPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        addSpace(infoPanel, 750, 0);

        timerLabel = new JLabel();
        // 3 lines method
        timerLabel.setFont(plainArial20);
        infoPanel.add(timerLabel);
        addSpace(infoPanel, 100, 0);

        rewardLabel = new JLabel();
        rewardLabel.setFont(plainArial20);
        infoPanel.add(rewardLabel);
        addSpace(infoPanel, 100, 0);

        scoreLabel = new JLabel();
        scoreLabel.setFont(plainArial20);
        infoPanel.add(scoreLabel);

        addSpace(infoPanel, 750, 0);

        this.add(infoPanel);

        mazePanel = new JPanel();
        mazePanel.setPreferredSize(new Dimension(mazeWidth * cellWidth, mazeHeight * cellHeight));
        mazePanel.setLayout(new GridLayout(mazeHeight, mazeWidth));
        this.add(mazePanel);

        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                JLabel label = new JLabel();
                if (isValidPathImage(j, i))
                    label.setIcon(getPath(cellWidth, cellHeight));
                else
                    label.setIcon(getWall(cellWidth, cellHeight));

                cellLabels[j][i] = label;
                mazePanel.add(label);
            }
        }

        revalidateMaze();
        startThread();
    }

    /**
     * returns an instance of the GamePlayScreen
     *
     * @return an instance of the GamePlayScreen
     */
    public static JPanel getInstance() {
        if (instance == null)
            return new GamePlayScreen();
        return instance;
    }

    private void startThread() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                GameController gc = GameController.getInstance();
                Player player = Player.getInstance();
                Timer timer = Timer.getInstance();

                while (gc.getIsRunning()) {
                    revalidateMaze();

                    if (!gc.getQuit() && !gc.getIsRunning() && gc.getHasWon()) {
                        addGameWonScreen(player.getScore(), timer.getTimeElapsed());
                        getSubFrame().setVisible(true);
                    }

                    if (!gc.getQuit() && !gc.getIsRunning() && !gc.getHasWon()) {
                        Entities entities = Entities.getInstance();
                        int collectedRewards = rewardCount - entities.getRewardCount();

                        addGameOverScreen(player.getScore(), timer.getTimeElapsed(),
                                collectedRewards, entities.getBonusRewardsCollected());

                        getSubFrame().setVisible(true);
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                GameController gc = GameController.getInstance();
                Entities entities = Entities.getInstance();
                Timer timer = Timer.getInstance();

                while (gc.getIsRunning()) {
                    rewardLabel.setText("Supplies Collected: " +
                            formatRewardCount(rewardCount,
                                    entities.getRewardCount(),
                                    entities.getNumBonusRewards(),
                                    entities.getBonusRewardsCollected()));
                    timerLabel.setText("Time: " + formatTime(timer.getTimeElapsed()));
                    scoreLabel.setText("Score: " + Player.getInstance().getScore());
                }
            }
        });

        t.start();
        t2.start();
    }

    private void revalidateMaze() {
        Maze maze = Maze.getInstance();
        Entities entities = Entities.getInstance();

        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                utilities.Position pos = new utilities.Position(j, i);
                Cell cell = maze.getCell(pos);

                if (Player.getInstance().getPosition().equals(pos)) {
                    cellLabels[j][i].setIcon(playerIcons[3]);
                    continue;
                }

                if (entities.containsEnemy(pos)) {
                    cellLabels[j][i].setIcon(enemyIcons[1]);
                    continue;
                }

                if (entities.containsReward(pos)) {
                    Reward reward = entities.getReward(pos);

                    if (reward instanceof BonusReward)
                        cellLabels[j][i].setIcon(getBonusReward(cellWidth, cellHeight));
                    else
                        cellLabels[j][i].setIcon(getReward(cellWidth, cellHeight));
                    continue;
                }

                if (entities.containsTrap(pos)) {
                    Trap trap = entities.getTrap(pos);

                    if (trap.isBoobyTrap())
                        cellLabels[j][i].setIcon(getBoobyTrap(cellWidth, cellHeight));
                    else
                        cellLabels[j][i].setIcon(getTrapFall(cellWidth, cellHeight));
                    continue;
                }

                if (cell.isPath() || cell.isEnd() || cell.isStart()) {
                    cellLabels[j][i].setIcon(getPath(cellWidth, cellHeight));
                    continue;
                }

                cellLabels[j][i].setIcon(getWall(cellWidth, cellHeight));
            }
        }
    }

    private static boolean isValidPathImage(int x, int y) {
        Maze maze = Maze.getInstance();
        return maze.getCell(x, y).isPath() || maze.getCell(x, y).isStart() || maze.getCell(x, y).isEnd();
    }

}