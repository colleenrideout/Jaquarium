package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AquariumGameTest {
    public AquariumGame aquarium;
    public Fish nemo;
    public Fish gill;

    @BeforeEach
    void runBefore() {
        aquarium = new AquariumGame();
        nemo = new Fish("Nemo");
        gill = new Fish("Gill");
    }

    @Test
    void testConstructor() {
        assertEquals(0, aquarium.getSize());
    }

    @Test
    void testUpdate() {
        aquarium.addFish("Nemo");
        nemo.setX(10);
        aquarium.update();
        assertEquals(10, nemo.getX());
        assertEquals(50.0, nemo.getHealth());
    }

    @Test
    void testReduceHealth() {
        aquarium.addFish("Nemo");
        aquarium.reduceHealth();
        assertEquals(50.0, nemo.getHealth());
    }

    @Test
    void testAddFish() {
        aquarium.addFish("Nemo");
        assertEquals(1, aquarium.getSize());

        aquarium.addFish("Gill");
        assertEquals(2, aquarium.getSize());
    }

    @Test
    void testFeedFishInList() {
        aquarium.addFish("Nemo");
        aquarium.feedFish("Nemo");
        assertEquals(50.0, nemo.getHealth());
    }

    @Test
    void testFeedFishNotInList() {
        aquarium.addFish("Nemo");
        aquarium.feedFish("Gill");
        assertEquals(50.0, nemo.getHealth());
    }

    @Test
    void testRemoveFishInList() {
        aquarium.addFish("Nemo");
        aquarium.removeFish("Nemo");
        assertEquals(0, aquarium.getSize());
    }

    @Test
    void testRemoveFishNotInList() {
        aquarium.addFish("Nemo");
        aquarium.removeFish("Gill");
        assertEquals(1, aquarium.getSize());
    }

    @Test
    void testRemoveAllFish() {
        aquarium.addFish("Nemo");
        aquarium.addFish("Gill");
        aquarium.removeAllFish();
        assertEquals(0, aquarium.getSize());
    }

    @Test
    void testRemoveAllFishEmptyList() {
        aquarium.removeAllFish();
        assertEquals(0, aquarium.getSize());
    }

    @Test
    void testGetFishHealth() {
        aquarium.addFish("Nemo");
        aquarium.addFish("Gill");
        assertEquals(50.0, aquarium.getFishHealth("Nemo"));
        assertEquals(50.0, aquarium.getFishHealth("Gill"));
    }

    @Test
    void testGetList() {
        aquarium.addFish("Nemo");
        ArrayList<Fish> fishList = aquarium.getFishList();
        assertEquals("Nemo", fishList.get(0).getName());
    }

//    @Test
//    void testMoveFishList() {
//        aquarium.addFish("Nemo");
//        aquarium.moveFishList();
//    }
}
