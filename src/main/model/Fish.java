package model;

import org.json.JSONObject;

import java.awt.Color;
import java.util.Random;

/*
 * Represents a fish
 * Modeled after SpaceInvadersBase.Invaders class.
 */
public class Fish {

    private static final int JIGGLE_Y = 1;
    private final String name;
    private double health;
    private int horizontalSpeed = 1;
    private int xcoordinate;
    private int ycoordinate;

    public static final int SIZE_X = 25;
    public static final int SIZE_Y = 19;
    public static final Color COLOR = new Color(238, 65, 33);

    Random random = new Random();

    // Constructs a fish
    // effects: fish is positioned at random coordinates, with a name and hunger set to 50.
    public Fish(String name) {
        this.name = name;
        this.health = 50;

        int widthBound = AquariumGame.WIDTH;
        this.xcoordinate = random.nextInt(widthBound);
        int heightBound = AquariumGame.HEIGHT;
        this.ycoordinate = random.nextInt(heightBound);
    }

    // Constructs a fish
    // effects: fish is positioned at random coordinates, with a name and health level.
    public Fish(String name, int health) {
        this.name = name;
        this.health = health;

        int widthBound = AquariumGame.WIDTH;
        this.xcoordinate = random.nextInt(widthBound);
        int heightBound = AquariumGame.HEIGHT;
        this.ycoordinate = random.nextInt(heightBound);
    }

    public int getX() {
        return this.xcoordinate;
    }

    public int getY() {
        return this.ycoordinate;
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return (int) this.health;
    }

    public int getHorizontalSpeed() {
        return horizontalSpeed;
    }

    public void setX(int x) {
        this.xcoordinate = x;
    }

    public void setY(int y) {
        this.ycoordinate = y;
    }

    public void setHunger(int health) {
        this.health = health;
    }

    // Updates fish on clock tick
    // modifies: this
    // effects:  fish is moved left and right across the screen HORIZONTAL_SPEED units and randomly takes
    //           a step of size no greater than JIGGLE_Y up or down.
    public void move() {
        xcoordinate = xcoordinate + horizontalSpeed;
        ycoordinate = ycoordinate + AquariumGame.RND.nextInt(2 * JIGGLE_Y + 1) - JIGGLE_Y;

        handleBoundary();
    }

    // Updates health on clock tick
    // modifies: this
    // effects: reduces health .001 per clock tick
    public void reduceHealth() {
        this.health = this.health - .001;
    }

    // modifies: this
    // effects: increases health by 10
    public void feedFish() {
        this.health = this.health + 10;
    }

    // Constrains fish so that it doesn't travel off sides of screen
    // modifies: this
    // effects: fish are constrained to remain within horizontal and vertical boundaries of game
    private void handleBoundary() {
        if (ycoordinate < 0) {
            ycoordinate = 0;
        } else if (ycoordinate > AquariumGame.HEIGHT) {
            ycoordinate = AquariumGame.HEIGHT;
        } else if (xcoordinate < 0) {
            swimRight();
        } else if (xcoordinate > AquariumGame.WIDTH) {
            swimLeft();
        }
    }

    // modifies: this
    // effects: sets the swimming direction 1 per clock tick right
    private void swimRight() {
        horizontalSpeed = 1;
    }

    // modifies: this
    // effects: sets the swimming direction 1 per clock tick left
    private void swimLeft() {
        horizontalSpeed = -1;
    }

    // modeled from Thingy.toJson
    // EFFECTS: creates a json object with fish name and current health level
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("health", health);
        return json;
    }
}
