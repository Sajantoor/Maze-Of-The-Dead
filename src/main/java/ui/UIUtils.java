package ui;

import javax.swing.*;
import java.awt.*;

public class UIUtils {
    public static void buttonLayout(JButton button) {
        button.setForeground(Color.BLACK);
        button.setFont(UIConstants.boldArial35);
        button.setFocusPainted(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setBackground(null);
    }

    public static void addSpace(JPanel panel, int width, int height) {
        panel.add(Box.createRigidArea(new Dimension(width, height)));
    }
}
