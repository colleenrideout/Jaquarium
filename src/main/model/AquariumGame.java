package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Represents an aquarium game.
 * Modeled after SpaceInvadersBase.SIGGame class
 */
public class AquariumGame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final Random RND = new Random();
    private static final String JSON_FILE = "./data/aquarium.json";

    private final ArrayList<Fish> fishList;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    // Constructs an AquariumGame
    // effects:  creates empty list of fish
    public AquariumGame() {
        fishList = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_FILE);
        jsonReader = new JsonReader(JSON_FILE);
    }

    // Updates the game on clock tick
    // modifies: this, fish
    // effects:  updates fish, reduces health
    public void update() {
        moveFishList();
        reduceHealth();
    }

    // modifies: this, fish
    // effects: reduces health each clock tick
    public void reduceHealth() {
        for (Fish fish : fishList) {
            fish.reduceHealth();
        }
    }

    // modifies: this, fish
    // effects: adds fish object to the list of fish
    public void addFish(String name) {
        fishList.add(new Fish(name));
        EventLog.getInstance().logEvent(new Event("Fish " + name + " added to aquarium."));
    }

    // modifies: this, fish
    // effects: adds saved fish object to the list of fish
    public void addFish(String name, int health) {
        Fish fish = new Fish(name);
        fish.setHunger(health);
        fishList.add(fish);
    }

    public ArrayList<Fish> getFishList() {
        return fishList;
    }

    // modifies: this, fish
    // effects: increases fish health by 10
    public void feedFish(String name) {
        String tempName = "";
        for (int i = 0; i < fishList.size(); i++) {
            if (fishList.get(i).getName().equals(name)) {
                tempName = fishList.get(i).getName();
                fishList.get(i).feedFish();
            }
        }
        EventLog.getInstance().logEvent(new Event("Fish " + tempName + " fed."));
    }

    // modifies: this
    // effects: removes fish with given name from list of fish
    public void removeFish(String name) {
        String tempName = "";
        for (int i = 0; i < fishList.size(); i++) {
            if (fishList.get(i).getName().equals(name)) {
                tempName = fishList.get(i).getName();
                fishList.remove(fishList.get(i));
            }
        }
        EventLog.getInstance().logEvent(new Event("Fish " + tempName + " removed from aquarium."));
    }

    // modifies: this
    // effects: removes all fish from the given list
    public void removeAllFish() {
        while (!fishList.isEmpty()) {
            fishList.remove(0);
        }
    }

    // effects: gets the health level of the fish with the given name
    public int getFishHealth(String name) {
        String tempName = "";
        int health = 0;
        for (int i = 0; i < fishList.size(); i++) {
            if (fishList.get(i).getName().equals(name)) {
                tempName = fishList.get(i).getName();
                health = fishList.get(i).getHealth();
            }
        }
        EventLog.getInstance().logEvent(new Event("Checked health of " + tempName + "."));
        return health;
    }

    // Updates the list of fish
    // modifies: this, fish
    // effects: moves the fish
    private void moveFishList() {
        for (Fish next : fishList) {
            next.move();
        }
    }

    public int getSize() {
        return fishList.size();
    }

    // effects: prints a list of all fish names in the list of fish/ag
    public String getFishNames() {
        List<String> fishNames = new ArrayList<>();
        for (Fish f : fishList) {
            fishNames.add(f.getName());
        }
        EventLog.getInstance().logEvent(new Event("List of fish names printed to dialogue box."));
        return String.join(", ", fishNames);
    }

    // modeled from WorkRoom.toJson
    // EFFECTS: creates a json object with listOfFish
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("fishList", listOfFishToJson());
        return json;
    }

    // modeled from WorkRoom.thingiesToJson.
    // EFFECTS: returns fish in this aquarium as a JSON array
    private JSONArray listOfFishToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Fish f: fishList) {
            jsonArray.put(f.toJson());
        }
        return jsonArray;
    }

    // modeled after WorkRoomApp.saveWorkRoom
    // modifies: jw
    // EFFECTS: saves the aquarium to file
    public void saveAquarium() {
        try {
            jsonWriter.open();
            jsonWriter.write(this);
            jsonWriter.close();
            EventLog.getInstance().logEvent(new Event("Aquarium game saved."));
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_FILE);
        }
    }

    // modeled after WorkRoomApp.loadWorkRoom
    // MODIFIES: this, ap, f
    // EFFECTS: loads aquarium from file and adds loaded list to ap and this
    public void loadAquarium() {
        try {
            removeAllFish();
            AquariumGame game2;
            game2 = jsonReader.read();
            ArrayList<Fish> fishList = game2.getFishList();
            addToAquarium(fishList);
            EventLog.getInstance().logEvent(new Event("Aquarium game loaded."));
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_FILE);
        }
    }

    // modifies: this, f, ap
    // effects: replaces current aquarium with previously saved aquarium/list of fish
    private void addToAquarium(List<Fish> fishList) {
        int size = fishList.size();
        List<Fish> oldList = getFishList();
        if (oldList.isEmpty()) {
            for (int i = 0; i < size; i++) {
                addFish(fishList.get(i).getName(), fishList.get(i).getHealth());
            }
        }
    }
}

