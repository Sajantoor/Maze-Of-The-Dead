package ui.Screens;

import ui.components.PanelWithBackgroundImage;

import javax.swing.*;

import java.awt.*;

import static ui.GameUI.*;
import static ui.components.UIUtils.addSpace;
import static ui.components.Buttons.*;
import static ui.components.Elements.*;

/**
 * Represents the TitleScreen
 *
 * @author Dylan Young
 */
public class TitleScreen extends PanelWithBackgroundImage {

    /**
     * Represents the TitleScreen
     *
     * @param image The background image for the screen
     */
    public TitleScreen(Image image) {
        super(image);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addSpace(this, 500, 400);

        // Name of the game displayed
        addGameTitle(this);
        addSpace(this, 0, 60);

        // Start Button
        addPlayButton(this, "Start");
        addSpace(this, 0, 60);

        // Instruction button
        addInstructionButton(this, "Instruction");
        addSpace(this, 0, 60);

        // Exit Game button
        addExitGameButton(this, "Exit Game");

        // adds the title panel to the static frame
        getFrame().add(this);
    }

}
