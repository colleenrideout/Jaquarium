package ui;

import model.AquariumGame;
import model.Fish;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Represents the panel in which the fishList is displayed.
 * Modeled after SpaceInvadersBase.ScorePanel class
 */
public class InformationPanel extends JPanel {

    private static final String FISH_TXT = "Fish: ";
    private static final int LBL_WIDTH = 600;
    private static final int LBL_HEIGHT = 30;
    private final AquariumGame game;
    private final JLabel fishLbl;

    // Constructs a fish list panel
    // effects: sets the background colour and draws the initial label;
    //          updates this with the game whose list of fish names is to be displayed
    public InformationPanel(AquariumGame g) {
        game = g;
        setBackground(new Color(243, 143, 223, 176));
        fishLbl = new JLabel(FISH_TXT + printFishNames());
        fishLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(fishLbl);
        add(Box.createHorizontalStrut(10));
    }

    // Updates the information panel
    // modifies: this
    // effects:  updates the list of fish names
    public void update() {
        fishLbl.setText(FISH_TXT + printFishNames());
        repaint();
    }

    // effects: generates the list of names to be printed in the info panel
    private String printFishNames() {
        java.util.List<Fish> fishList = game.getFishList();
        List<String> fishNames = new ArrayList<>();
        for (Fish f : fishList) {
            fishNames.add(f.getName());
        }
        return String.join(", ", fishNames);
    }
}
