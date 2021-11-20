package ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameFonts {
    private static Font greatFighterFont = null;

    public static Font getGreatFighterFont() {
        try {
            greatFighterFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/java/ui/GameFonts/GreatFighter-pKK1.ttf"));
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
