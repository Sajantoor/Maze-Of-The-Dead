package ui;

import cell.CellType;
import character.CharacterModel;
import character.Player;
import game.GameController;
import game.Maze;
import reward.Reward;
import reward.Trap;
import reward.TrapType;
import utilities.Constants;
import utilities.Position;

import javax.swing.*;
import java.awt.*;

import static ui.GameUI.getFrame;
import static ui.UIConstants.cellHeight;
import static ui.UIConstants.cellWidth;
import static utilities.Constants.mazeHeight;
import static utilities.Constants.mazeWidth;

/**
 * Represents the GamePlayScreen
 *
 * @author Dylan Young
 */
public class GamePlayScreen {
    private JPanel gamePlayScreen;
    private JPanel infoPanel;
    private JPanel mazePanel;
    private JLabel[][] cellLabels;

    /**
     * returns the GamePlayScreen panel
     *
     * @return the GamePlayScreen panel
     * @see JPanel
     */
    public JPanel getGamePlayScreen() {
        SpriteIcons s = new SpriteIcons();
        getFrame().addKeyListener(new KeyboardListener());
        GameController.getInstance().startGame();
        cellLabels = new JLabel[mazeWidth][Constants.mazeHeight];

        gamePlayScreen = new JPanel();
        gamePlayScreen.setLayout(new BoxLayout(gamePlayScreen, BoxLayout.Y_AXIS));
        gamePlayScreen.setAlignmentY(Component.TOP_ALIGNMENT);
        gamePlayScreen.addKeyListener(new KeyboardListener());

        infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(1920, 30));
        infoPanel.setBackground(Color.LIGHT_GRAY);
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        infoPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        gamePlayScreen.add(infoPanel);


        mazePanel = new JPanel();
        mazePanel.setPreferredSize(new Dimension(mazeWidth * cellWidth, mazeHeight * cellHeight));
        mazePanel.setLayout(new GridLayout(mazeHeight, mazeWidth));
        gamePlayScreen.add(mazePanel);

        for(int i = 0; i < mazeHeight; i++){
            for(int j = 0; j < mazeWidth; j++){
                JLabel label = new JLabel();
                if(Maze.getInstance().getCell(j, i).getCellType() == CellType.PATH || Maze.getInstance().getCell(j, i).getCellType() == CellType.START || Maze.getInstance().getCell(j, i).getCellType() == CellType.END){
                    label.setIcon(s.getPath());
                }
                else{
                    label.setIcon(s.getWall());
                }
                cellLabels[j][i]  = label;
                mazePanel.add(label);
            }
        }

        for(int i = 0; i < Constants.enemyCount; i++){
            CharacterModel enemy = GameController.getInstance().getEnemy(i);
            getCellLabel(enemy.getPosition()).setIcon(s.getEnemy(0));
        }

        for(int i = 0; i < Constants.rewardCount; i++){
            Reward reward= GameController.getInstance().getReward(i);
            getCellLabel(reward.getPosition()).setIcon(s.getReward());
        }

        for(int i = 0; i < Constants.boobyTrapCount + Constants.trapFallCount; i++){
            Trap trap = GameController.getInstance().getTrap(i);
            if(trap.getTrapType() == TrapType.BOOBYTRAP){
                getCellLabel(trap.getPosition()).setIcon(s.getBoobyTrap());
            }else {
                getCellLabel(trap.getPosition()).setIcon(s.getTrapFall());
            }

        }

        Player player = Player.getInstance();
        getCellLabel(player.getPosition()).setIcon(s.getPerson(2));


        return gamePlayScreen;
    }
    private JLabel getCellLabel(Position pos){
        return cellLabels[pos.getX()][pos.getY()];
    }
}