package ui;

import javax.swing.*;
import java.awt.*;

import static ui.UIConstants.*;

public class CellPanel extends JPanel {
    public CellPanel() {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setPreferredSize(new Dimension(cellWidth, cellHeight));
        this.setBackground(Color.LIGHT_GRAY);
    }
}
