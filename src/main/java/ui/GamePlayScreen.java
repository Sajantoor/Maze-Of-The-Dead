package ui;

import utilities.Constants;

import javax.swing.*;
import java.awt.*;

import static ui.UIConstants.cellHeight;
import static ui.UIConstants.cellWidth;
import static utilities.Constants.mazeHeight;
import static utilities.Constants.mazeWidth;

public class GamePlayScreen {
    private JPanel gamePlayScreen;
    private JPanel infoPanel;
    private JPanel mazePanel;
    private CellPanel[][] cellPanel;

    public JPanel getGamePlayScreen() {

        cellPanel = new CellPanel[mazeWidth][Constants.mazeHeight];

        gamePlayScreen = new JPanel();
        gamePlayScreen.setLayout(new BoxLayout(gamePlayScreen, BoxLayout.Y_AXIS));
        gamePlayScreen.setAlignmentY(Component.TOP_ALIGNMENT);

        infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(1920, 50));
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
                CellPanel cell = new CellPanel();
                cellPanel[j][i] = cell;
                mazePanel.add(cell);
            }
        }

        return gamePlayScreen;
    }
}