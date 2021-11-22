package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Helper methods for the UI
 *
 * @author Dylan Young
 */
public class UIUtils {
    /**
     * Changes the layout for the button
     *
     * @param button a clickable component of the UI
     * @see JButton
     */
    public static void buttonLayout(JButton button) {
        button.setFont(UIConstants.boldArial35);
        button.setFocusPainted(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.RED);
    }

    /**
     * Add a spacer on the panel to align the components
     *
     * @param panel  holds the components of a UI
     * @param width  width of the space to be created
     * @param height height of the space to be created
     * @see JPanel
     */
    public static void addSpace(JPanel panel, int width, int height) {
        panel.add(Box.createRigidArea(new Dimension(width, height)));
    }
}
