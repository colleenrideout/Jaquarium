package ui;

import model.AquariumGame;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.*;


/*
 * Represents the panel in which the menu buttons are displayed.
 * Modeled off of SpaceInvadersBase.ScorePanel class
 */
public class ButtonPanel extends JPanel {

    private final AquariumGame game;

    // Constructs a button panel
    // effects: sets the background colour and draws the initial buttons;
    //          updates this with the game
    public ButtonPanel(AquariumGame g) {
        game = g;
        setBackground(new Color(144, 243, 222));
        add(Box.createHorizontalStrut(10));

        JButton addFishButton = new JButton(new AddFishAction());
        this.add(addFishButton);

        JButton feedFishButton = new JButton(new FeedFishAction());
        this.add(feedFishButton);

        JButton removeFishButton = new JButton(new RemoveFishAction());
        this.add(removeFishButton);

        JButton listFishButton = new JButton(new ListFishAction());
        this.add(listFishButton);

        JButton getHealthButton = new JButton(new GetHealthAction());
        this.add(getHealthButton);

        JButton saveAquariumButton = new JButton(new SaveAquariumAction());
        this.add(saveAquariumButton);

        JButton loadAquariumButton = new JButton(new LoadAquariumAction());
        this.add(loadAquariumButton);
    }

    /**
     * Represents the action to be taken when the user wants to add a new
     * fish to their aquarium.
     */
    // modifies: ap, ag, f
    // effects: create user dialogue to add a fish to the aquarium
    private class AddFishAction extends AbstractAction {

        AddFishAction() {
            super("Add Fish");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String fishName = JOptionPane.showInputDialog(null,
                    "What would you like to name your new fish friend?",
                    "Add Fish",
                    JOptionPane.QUESTION_MESSAGE);

            game.addFish(fishName);
            JOptionPane.showMessageDialog(null,
                    fishName + " was added to your aquarium.",
                    "",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Represents the action to be taken when the user wants to feed a
     * fish in their aquarium.
     */
    // modifies: f
    // effects: create user dialogue to feed one of the fish in the aquarium
    private class FeedFishAction extends AbstractAction {

        FeedFishAction() {
            super("Feed Fish");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String fishName = JOptionPane.showInputDialog(null,
                    "Which fish would you like to feed?",
                    "Feed Fish",
                    JOptionPane.QUESTION_MESSAGE);
            game.feedFish(fishName);
            JOptionPane.showMessageDialog(null,
                    fishName + " enjoyed their snack!",
                    "",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Represents the action to be taken when the user wants to remove a
     * fish from their aquarium.
     */
    // modifies: ag, ap
    // effects: creates user dialogue to remove one of the fish
    private class RemoveFishAction extends AbstractAction {

        RemoveFishAction() {
            super("Remove Fish");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String fishName = JOptionPane.showInputDialog(null,
                    "Which fish would you like to flush?",
                    "Remove Fish",
                    JOptionPane.QUESTION_MESSAGE);

            game.removeFish(fishName);

            JOptionPane.showMessageDialog(null,
                    "Your fish " + fishName + " was removed from your aquarium.","",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Represents the action to be taken when the user wants to view a
     * list of fish names their aquarium.
     */
    // effects: creates user dialogue to list fish in the fish list
    private class ListFishAction extends AbstractAction {

        ListFishAction() {
            super("List Fish");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            JOptionPane.showMessageDialog(null,
                    "Fish swimming in your aquarium: " + game.getFishNames() + ".","",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Represents the action to be taken when the user wants to view the
     * health level of a fish.
     */
    // effects: creates user dialogue to view the health of one of the fish
    private class GetHealthAction extends AbstractAction {

        GetHealthAction() {
            super("Fish Health");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

            String fishName = JOptionPane.showInputDialog(null,
                    "Which fish would you like to check?",
                    "Check Fish Health",
                    JOptionPane.QUESTION_MESSAGE);

            JOptionPane.showMessageDialog(null,
                    fishName + " has a current health of " + game.getFishHealth(fishName) + ".","",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Represents the action to be taken when the user wants to save their
     * aquarium to file.
     */
    // modifies: jw
    // effects: saves aquarium and prints dialogue to user that aquarium is saved
    private class SaveAquariumAction extends AbstractAction {

        SaveAquariumAction() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

            game.saveAquarium();

            JOptionPane.showMessageDialog(null,
                    "Aquarium saved.", "",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Represents the action to be taken when the user wants to load their
     * aquarium saved to file.
     */
    // modifies: ap, ag, f
    // effects: prompts to load a saved aquarium and notify user via dialogue
    private class LoadAquariumAction extends AbstractAction {

        LoadAquariumAction() {
            super("Load");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

            game.loadAquarium();

            JOptionPane.showMessageDialog(null,
                    "Aquarium loaded successfully.", "",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Updates the button panel
    // modifies: this
    // effects:  updates button panel and dialogue boxes/interaction windows
    public void update() {
        repaint();
    }
}
