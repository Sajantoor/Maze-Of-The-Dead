package ui.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import static ui.components.GameFonts.getGreatFighterFont;

/**
 * Constants used for the UI
 *
 * @author Dylan Young
 */
public class UIConstants {
    /**
     * heading font
     */
    public static final Font heading = new Font(getGreatFighterFont().getName(), Font.BOLD, 80);
    /**
     * title font
     */
    public static final Font title = new Font(getGreatFighterFont().getName(), Font.BOLD, 45);
    /**
     * Arial font of size 35
     */
    public static final Font plainArial35 = new Font("Arial", Font.PLAIN, 35);
    /**
     * Bold Arial font of size 35
     */
    public static final Font boldArial35 = new Font("Arial", Font.BOLD, 35);
    /**
     * Arial font of size 20
     */
    public static final Font plainArial20 = new Font("Arial", Font.PLAIN, 20);
    /**
     * Outline for the key images
     */
    public static final Border keyBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
    /**
     * Height of each cell
     */
    public static final int cellHeight = 35;
    /**
     * Width of each cell
     */
    public static final int cellWidth = 60;

    /**
     * The default X screen resolution for the game
     */
    public static final int defaultScreenSizeX = 1920;
    /**
     * The default Y screen resolution for the game
     */
    public static final int defaultScreenSizeY = 1080;
}
