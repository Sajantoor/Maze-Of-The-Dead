package ui.components;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static ui.components.FontPathString.GREAT_FIGHTER_FONT_LOCATION;

/**
 * Creates custom font styles
 * 
 * @author Dylan Young
 */
public class GameFonts {
    private static Font greatFighterFont = null;

    /**
     * returns the font GreatFighter font
     *
     * @return the font GreatFighter font
     */
    // https://www.fontspace.com/great-fighter-font-f39648
    public static Font getGreatFighterFont() {
        try {
            greatFighterFont = Font.createFont(Font.TRUETYPE_FONT, new File(GREAT_FIGHTER_FONT_LOCATION));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(greatFighterFont);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return greatFighterFont;
    }
}
