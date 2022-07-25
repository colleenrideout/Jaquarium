package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FishTest {
    private Fish nemo;
    private Fish bubbles;
    private Fish gill;
    private Fish marvin;

    @BeforeEach
    void runBefore() {
        nemo = new Fish("Nemo");
        bubbles = new Fish("Bubbles");
        gill = new Fish("Gill");
        marvin = new Fish("Marvin", 30);
    }

    @Test
    // test constructor fields
    void testFishConstructorName() {
        assertEquals("Nemo", nemo.getName());
        assertEquals("Bubbles", bubbles.getName());
        assertEquals("Gill", gill.getName());

        assertEquals(50.0, nemo.getHealth());

        assertEquals(nemo.getX(), (nemo.getX())); // provided for code test coverage; but generation of
        assertEquals(nemo.getY(), nemo.getY());   //  random values does not need to be tested.
    }

    @Test
    void testFishConstructorNameAndHealth() {
        assertEquals("Marvin", marvin.getName());
        assertEquals(30.0, marvin.getHealth());

        assertEquals(marvin.getX(), (marvin.getX())); // provided for code test coverage; but generation of
        assertEquals(marvin.getY(), marvin.getY());   //  random values does not need to be tested.
    }

    @Test
    void testMove() {
        nemo.setX(10);
        int previousX = nemo.getX();
        nemo.move();
        assertEquals((previousX + nemo.getHorizontalSpeed()), nemo.getX());
    }

    @Test
    void testReduceHealth() {
        nemo.reduceHealth();
        assertEquals(49.0, nemo.getHealth());
    }

    @Test
    void testFeedFish() {
        nemo.feedFish();
        assertEquals(60.0, nemo.getHealth());
    }

    @Test
    void testHandleBoundaryAboveBorder() {
        nemo.setY(-3);
        nemo.move();
        assertEquals(0, nemo.getY());
    }

    @Test
    void testHandleBoundaryHitBelowBorder() {
        nemo.setY(AquariumGame.HEIGHT + 1);
        assertEquals(AquariumGame.HEIGHT + 1, nemo.getY());
    }

    @Test
    void testHandleBoundaryBelowBorder() {
        nemo.setY(AquariumGame.HEIGHT + 1);
        nemo.move();
        assertEquals(AquariumGame.HEIGHT, nemo.getY());
    }

    @Test
    void testHandleBoundaryOffScreenLeft() {
        nemo.setX(-3);
        nemo.move();
        assertEquals(1, nemo.getHorizontalSpeed());
    }

    @Test
    void testHandleBoundaryOffScreenRight() {
        nemo.setX(AquariumGame.WIDTH + 1);
        nemo.move();
        assertEquals(-1, nemo.getHorizontalSpeed());
    }
}
