package ui;

import cell.CellType;
import game.GameController;
import game.Maze;
import reward.BonusReward;
import reward.Reward;
import reward.Trap;
import reward.TrapType;
import utilities.Constants;

import javax.swing.*;
import java.awt.*;

import static ui.GameUI.*;
import static ui.UIConstants.cellHeight;
import static ui.UIConstants.cellWidth;
import static utilities.Constants.mazeHeight;
import static utilities.Constants.mazeWidth;

/**
 * Represents the GamePlayScreen
 *
 * @author Dylan Young
 */
public class GamePlayScreen extends JPanel {
    private JPanel infoPanel;
    private JPanel mazePanel;
    private JLabel[][] cellLabels;
    private SpriteIcons s;
    private static JPanel instance = null;

    /**
     * returns the GamePlayScreen panel
     *
     * @return the GamePlayScreen panel
     * @see JPanel
     */
    private GamePlayScreen() {
        s = new SpriteIcons();
        getFrame().addKeyListener(new KeyboardListener());
        GameController.getInstance().startGame();
        cellLabels = new JLabel[mazeWidth][Constants.mazeHeight];

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentY(Component.TOP_ALIGNMENT);
        this.addKeyListener(new KeyboardListener());

        infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(1920, 30));
        infoPanel.setBackground(Color.LIGHT_GRAY);
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        infoPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        this.add(infoPanel);


        mazePanel = new JPanel();
        mazePanel.setPreferredSize(new Dimension(mazeWidth * cellWidth, mazeHeight * cellHeight));
        mazePanel.setLayout(new GridLayout(mazeHeight, mazeWidth));
        this.add(mazePanel);

        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                JLabel label = new JLabel();
                if (Maze.getInstance().getCell(j, i).getCellType() == CellType.PATH || Maze.getInstance().getCell(j, i).getCellType() == CellType.START || Maze.getInstance().getCell(j, i).getCellType() == CellType.END) {
                    label.setIcon(s.getPath());
                } else {
                    label.setIcon(s.getWall());
                }
                cellLabels[j][i] = label;
                mazePanel.add(label);
            }
        }

        revalidateMaze();
        startThread();
    }

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
                        addGameOverScreen(GameController.getInstance().getPlayer().getScore(), GameController.getInstance().getTimeElapsed(), Constants.rewardCount - GameController.getInstance().getRewardCount(), GameController.getInstance().getBonusRewardsCollected());
                        getSubFrame().setVisible(true);
                    }
                }
            }
        });

        t.start();
    }

    private void revalidateMaze() {
        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                if (GameController.getInstance().getPlayer().getPosition().getX() == j && GameController.getInstance().getPlayer().getPosition().getY() == i) {
                    cellLabels[j][i].setIcon(s.getPerson(2));
                } else if (GameController.getInstance().containsEnemy(j, i)) {
                    cellLabels[j][i].setIcon(s.getEnemy(0));
                } else if (GameController.getInstance().containsReward(j, i)) {
                    Reward reward = GameController.getInstance().getReward(j, i);
                    if (reward instanceof BonusReward) {
                        cellLabels[j][i].setIcon(s.getBonusReward());
                    } else {
                        cellLabels[j][i].setIcon(s.getReward());
                    }
                } else if (GameController.getInstance().containsTrap(j, i)) {
                    Trap trap = GameController.getInstance().getTrap(j, i);
                    if (trap.getTrapType() == TrapType.BOOBYTRAP) {
                        cellLabels[j][i].setIcon(s.getBoobyTrap());
                    } else {
                        cellLabels[j][i].setIcon(s.getTrapFall());
                    }
                } else if (Maze.getInstance().getCell(j, i).getCellType() == CellType.PATH || Maze.getInstance().getCell(j, i).getCellType() == CellType.END || Maze.getInstance().getCell(j, i).getCellType() == CellType.START) {
                    cellLabels[j][i].setIcon(s.getPath());
                } else {
                    cellLabels[j][i].setIcon(s.getWall());
                }
            }
        }
    }
}