package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.AquariumGame;
import org.json.*;

// modeled after JsonSerializationDemo - JsonReader
// Represents a reader that reads aquariums from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads aquarium from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AquariumGame read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAquariumGame(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // throws IOException if an error occurs reading data from file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses aquarium from JSON object and returns it
    private AquariumGame parseAquariumGame(JSONObject jsonObject) {
        AquariumGame ag = new AquariumGame();
        addFishList(ag, jsonObject);
        return ag;
    }

    // MODIFIES: ag
    // EFFECTS: parses list of fish from JSON object and adds them to aquarium
    private void addFishList(AquariumGame ag, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("fishList");
        for (Object json: jsonArray) {
            JSONObject nextFish = (JSONObject) json;
            addFish(ag, nextFish);
        }
    }

    // MODIFIES: ag
    // EFFECTS: parses fish from JSON object and adds it to aquarium
    private void addFish(AquariumGame ag, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int health = jsonObject.getInt("health");
        ag.addFish(name, health);
    }
}

