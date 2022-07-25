package persistence;

import model.AquariumGame;
import model.Fish;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// tests are modeled after JsonSerializationDemo
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:filename.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAquarium() {
        try {
            AquariumGame ag = new AquariumGame();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAquarium.json");
            writer.open();
            writer.write(ag);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAquarium.json");
            ag = reader.read();
            assertEquals(0, ag.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testWriterSampleAquarium() {
        try {
            AquariumGame ag = new AquariumGame();
            ag.addFish("Nemo");
            ag.addFish("Dory");
            ag.addFish("Bubbles");
            JsonWriter writer = new JsonWriter("./data/testWriterSampleAquarium.json");
            writer.open();
            writer.write(ag);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterSampleAquarium.json");
            ag = reader.read();
            assertEquals(3, ag.getSize());
            List<Fish> fishList = ag.getFishList();
            checkFish("Nemo", 50, fishList.get(0));
            checkFish("Dory", 50, fishList.get(1));
            checkFish("Bubbles", 50, fishList.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
