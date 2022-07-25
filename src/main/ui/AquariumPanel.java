package ui;

import model.AquariumGame;
import model.Fish;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


/*
 * The panel in which the game is rendered
 * Modeled off of SpaceInvadersBase.GamePanel class
 */
public class AquariumPanel extends JPanel {

    private final AquariumGame game;

    // Constructs a game panel
    // effects:  sets size and background colour of panel,
    //           updates this with the game to be displayed
    public AquariumPanel(AquariumGame g) {
        setPreferredSize(new Dimension(AquariumGame.WIDTH, AquariumGame.HEIGHT));
        setBackground(new Color(72, 111, 241, 255));
        this.game = g;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    // Draws the game
    // modifies: g
    // effects:  draws the game onto g
    private void drawGame(Graphics g) {
        drawFishList(g);
    }

    // Draws the fish
    // modifies: g
    // effects:  draws the fish (plural) onto g
    private void drawFishList(Graphics g) {
        for (Fish next : game.getFishList()) {
            drawFish(g, next);
        }
    }

    // Draws a fish
    // modifies: g
    // effects:  draws the fish f onto g
    private void drawFish(Graphics g, Fish f) {
        Color savedCol = g.getColor();
        g.setColor(Fish.COLOR);
        g.fillOval(f.getX() - Fish.SIZE_X / 2, f.getY() - Fish.SIZE_Y / 2, Fish.SIZE_X, Fish.SIZE_Y);
        g.setColor(savedCol);
    }
}
