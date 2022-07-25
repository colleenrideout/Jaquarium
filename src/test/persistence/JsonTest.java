package persistence;

import model.Fish;

import static org.junit.jupiter.api.Assertions.*;

// tests are modeled after JsonSerializationDemo
public class JsonTest {
    protected void checkFish(String name, int health, Fish fish) {
        assertEquals(name, fish.getName());
        assertEquals(health, fish.getHealth());
    }
}
