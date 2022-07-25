package ui;

import model.AquariumGame;
import model.Event;
import model.EventLog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

import javax.swing.*;

/*
 * Represents the main window in which the aquarium game is played
 * Modeled after SpaceInvadersBase.SpaceInvaders class
 */
public class Main extends JFrame {

    private static final int INTERVAL = 10;

    private final AquariumGame game;
    private final AquariumPanel gp;
    private final ButtonPanel sp;
    private final InformationPanel ip;

    // Constructs main window
    // effects: sets up window in which the aquarium game will be played
    public Main() throws FileNotFoundException {
        super("Jaquarium");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                    printLog(EventLog.getInstance());
                    System.exit(0);
                }
            });

        setUndecorated(false);
        game = new AquariumGame();
        gp = new AquariumPanel(game);
        sp = new ButtonPanel(game);
        ip = new InformationPanel(game);
        add(gp);
        add(sp, BorderLayout.NORTH);
        add(ip, BorderLayout.SOUTH);
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
    }

    // Set up timer
    // modifies: none
    // effects:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    private void addTimer() {
        Timer t = new Timer(INTERVAL, ae -> {
            game.update();
            gp.repaint();
            sp.update();
            ip.update();
        });

        t.start();
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    // effects: prints logged items to console
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
        EventLog.getInstance().clear();
    }

    /*
     * Plays the game. Throws exception is you try to load from a file
     * that the game is not saved to.
     */
    public static void main(String[] args) {
        try {
            new Main();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found.");
        }
    }
}
