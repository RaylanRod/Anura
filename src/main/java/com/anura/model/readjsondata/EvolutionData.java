package com.anura.model.readjsondata;

import com.anura.view.Helper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class EvolutionData {
    private String evolName;
    private List<String> abilities;

    public EvolutionData(String currentLevel) {
        // Read JSON data and extract the corresponding abilities based on the current level
        JsonArray jsonData = readJsonData();

        if (jsonData != null) {
            for (JsonElement element : jsonData) {
                JsonObject evolutionData = element.getAsJsonObject();
                String name = evolutionData.get("name").getAsString();

                if (name.equalsIgnoreCase(currentLevel)) {
                    this.evolName = name;
                    this.abilities = extractAbilities(evolutionData);
                    return;
                }
            }
        }
        // Set default values if JSON data is not available or invalid
        this.evolName = "Tadpole";
        this.abilities = new ArrayList<>();

    }

    // Method to read JSON data from the file
    public JsonArray readJsonData() {
        try {
            Gson gson = new Gson();

            // TODO: to be modified
            String fileReader = Helper.readFromResourceFile("Evolution.json");
            return gson.fromJson(fileReader, JsonArray.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to extract abilities from the JSON object
    private List<String> extractAbilities(JsonObject evolutionData) {
        List<String> abilities = new ArrayList<>();
        JsonObject abilitiesObject = evolutionData.getAsJsonObject("ability");

        for (String key : abilitiesObject.keySet()) {
            abilities.add(abilitiesObject.get(key).getAsString());
        }

        return abilities;
    }

    public String getEvolName() {
        return evolName;
    }

    public void setEvolName(String evolName) {
        this.evolName = evolName;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    // Other methods can be added here if needed
    @Override
    public String toString() {
        return evolName;
    }
}
