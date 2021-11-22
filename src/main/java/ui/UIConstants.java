package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import static ui.GameFonts.getGreatFighterFont;

/**
 * Constants used for the UI
 *
 * @author Dylan Young
 */
public class UIConstants {
    public static final Font heading = new Font(getGreatFighterFont().getName(), Font.BOLD, 80);
    public static final Font title = new Font(getGreatFighterFont().getName(), Font.BOLD, 45);
    public static final Font plainArial35 = new Font("Arial", Font.PLAIN, 35);
    public static final Font boldArial35 = new Font("Arial", Font.BOLD, 35);
    public static final Font plainArial20 = new Font("Arial", Font.PLAIN, 20);
    public static final Font boldArial20 = new Font("Arial", Font.BOLD, 20);
    public static final Border keyBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
    public static final int cellHeight = 35;
    public static final int cellWidth = 60;
}
