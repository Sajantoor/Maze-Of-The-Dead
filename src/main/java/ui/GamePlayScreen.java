package ui;

import cell.CellType;
import character.CharacterModel;
import character.Player;
import game.GameController;
import game.Maze;
import reward.BonusReward;
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
    private SpriteIcons s;

    /**
     * returns the GamePlayScreen panel
     *
     * @return the GamePlayScreen panel
     * @see JPanel
     */
    public JPanel getGamePlayScreen() {
        s = new SpriteIcons();
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

        revalidateMaze();
        startThread();


        return gamePlayScreen;
    }
    private JLabel getCellLabel(Position pos){
        return cellLabels[pos.getX()][pos.getY()];
    }

    private void startThread(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(GameController.getInstance().getIsRunning()){
                    revalidateMaze();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(GameController.getInstance().getIsRunning()){
                    for(int i = 0; i < mazeHeight; i++){
                        for(int j = 0; j < mazeWidth; j++){
                            if(Maze.getInstance().getCell(j, i).getCellType() == CellType.PATH || Maze.getInstance().getCell(j, i).getCellType() == CellType.START || Maze.getInstance().getCell(j, i).getCellType() == CellType.END){
                                cellLabels[j][i].setIcon(s.getPath());
                            }
                            else{
                                cellLabels[j][i].setIcon(s.getWall());
                            }
                        }
                    }
                }
            }
        });

        t.start();
        t2.start();
    }

    private void revalidateMaze(){
        for(int i = 0; i < GameController.getInstance().getEnemyCount(); i++){
            CharacterModel enemy = GameController.getInstance().getEnemy(i);
            getCellLabel(enemy.getPosition()).setIcon(s.getEnemy(0));
        }

        for(int i = 0; i < GameController.getInstance().getRewardCount(); i++){
            Reward reward= GameController.getInstance().getReward(i);
            if (reward instanceof BonusReward){
                getCellLabel(reward.getPosition()).setIcon(s.getBonusReward());
            }else{
                getCellLabel(reward.getPosition()).setIcon(s.getReward());
            }
        }

        for(int i = 0; i < GameController.getInstance().getTrapCount(); i++){
            Trap trap = GameController.getInstance().getTrap(i);
            if(trap.getTrapType() == TrapType.BOOBYTRAP){
                getCellLabel(trap.getPosition()).setIcon(s.getBoobyTrap());
            }else {
                getCellLabel(trap.getPosition()).setIcon(s.getTrapFall());
            }

        }

        Player player = Player.getInstance();
        getCellLabel(player.getPosition()).setIcon(s.getPerson(2));
    }
}