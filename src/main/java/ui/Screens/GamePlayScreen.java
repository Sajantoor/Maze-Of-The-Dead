package ui.Screens;

import cell.CellType;
import game.GameController;
import game.Maze;
import reward.BonusReward;
import reward.Reward;
import reward.Trap;
import reward.TrapType;
import ui.components.KeyboardListener;
import utilities.Constants;

import javax.swing.*;
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
                if (Maze.getInstance().getCell(j, i).getCellType() == CellType.PATH ||
                        Maze.getInstance().getCell(j, i).getCellType() == CellType.START ||
                        Maze.getInstance().getCell(j, i).getCellType() == CellType.END) {
                    label.setIcon(getPath(cellWidth, cellHeight));
                } else {
                    label.setIcon(getWall(cellWidth, cellHeight));
                }
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
                while (GameController.getInstance().getIsRunning()) {
                    revalidateMaze();
                    if (!GameController.getInstance().getQuit() && !GameController.getInstance().getIsRunning() && GameController.getInstance().getHasWon()) {
                        addGameWonScreen(GameController.getInstance().getPlayer().getScore(), GameController.getInstance().getTimeElapsed());
                        getSubFrame().setVisible(true);
                    }
                    if (!GameController.getInstance().getQuit() && !GameController.getInstance().getIsRunning() && !GameController.getInstance().getHasWon()) {
                        addGameOverScreen(GameController.getInstance().getPlayer().getScore(), GameController.getInstance().getTimeElapsed(), rewardCount - GameController.getInstance().getRewardCount(), GameController.getInstance().getBonusRewardsCollected());
                        getSubFrame().setVisible(true);
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run(){
                while(GameController.getInstance().getIsRunning()){
                    rewardLabel.setText("Supplies Collected: " +
                            formatRewardCount(rewardCount ,
                                    GameController.getInstance().getRewardCount() ,
                                    GameController.getInstance().getNumberBonusRewards() ,
                                    GameController.getInstance().getBonusRewardsCollected()));
                    timerLabel.setText("Time: " + formatTime(GameController.getInstance().getTimeElapsed()));
                    scoreLabel.setText("Score: " + GameController.getInstance().getPlayer().getScore());
                }
            }
        });

        t.start();
        t2.start();
    }

    private void revalidateMaze() {
        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                if (GameController.getInstance().getPlayer().getPosition().getX() == j && GameController.getInstance().getPlayer().getPosition().getY() == i) {
                    cellLabels[j][i].setIcon(playerIcons[3]);
                } else if (GameController.getInstance().containsEnemy(j, i)) {
                    cellLabels[j][i].setIcon(enemyIcons[1]);
                } else if (GameController.getInstance().containsReward(j, i)) {
                    Reward reward = GameController.getInstance().getReward(j, i);
                    if (reward instanceof BonusReward) {
                        cellLabels[j][i].setIcon(getBonusReward(cellWidth, cellHeight));
                    } else {
                        cellLabels[j][i].setIcon(getReward(cellWidth, cellHeight));
                    }
                } else if (GameController.getInstance().containsTrap(j, i)) {
                    Trap trap = GameController.getInstance().getTrap(j, i);
                    if (trap.getTrapType() == TrapType.BOOBYTRAP) {
                        cellLabels[j][i].setIcon(getBoobyTrap(cellWidth, cellHeight));
                    } else {
                        cellLabels[j][i].setIcon(getTrapFall(cellWidth, cellHeight));
                    }
                } else if (Maze.getInstance().getCell(j, i).getCellType() == CellType.PATH || Maze.getInstance().getCell(j, i).getCellType() == CellType.END || Maze.getInstance().getCell(j, i).getCellType() == CellType.START) {
                    cellLabels[j][i].setIcon(getPath(cellWidth, cellHeight));
                } else {
                    cellLabels[j][i].setIcon(getWall(cellWidth, cellHeight));
                }
            }
        }
    }
}