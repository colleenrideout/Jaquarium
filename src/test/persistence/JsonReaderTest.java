package persistence;

import model.AquariumGame;
import model.Fish;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// tests are modeled after JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNoSuchFile() {
        JsonReader reader = new JsonReader("./data/noFile.json");
        try {
            AquariumGame ag = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAquarium() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAquarium.json");
        try {
            AquariumGame ag = reader.read();
            assertEquals(0, ag.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderSampleAquarium() {
        JsonReader reader = new JsonReader("./data/testReaderSampleAquarium.json");
        try {
            AquariumGame ag = reader.read();
            ArrayList<Fish> fishList = ag.getFishList();
            assertEquals(4, fishList.size());
            checkFish("Nemo", 50, fishList.get(0));
            checkFish("Dory", 50, fishList.get(1));
            checkFish("Bubbles", 50, fishList.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
