package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Extension class of JPanel, that accepts image as background
 *
 * @author Kaung Si Thu
 */
class PanelWithBackgroundImage extends JPanel {

    private Image image;

    /**
     * Constructor function for Panel with background image
      * @param image Image to be set as background
     */
    public PanelWithBackgroundImage(Image image) {
        this.image = image;
        setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        setPreferredSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        setSize(getPreferredSize());
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}
