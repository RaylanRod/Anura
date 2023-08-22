package com.anura.model.readjsondata;

import com.anura.view.Helper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class LocationData {
    private String location;
    private JsonObject mapData;

    public LocationData(String location, JsonObject mapData) {
        this.location = location;
        this.mapData = mapData;
    }

    public List<String> getLocationItems() {
        try {
            Gson gson = new Gson();
            String contentFile = Helper.readFromResourceFile("location.json");
            JsonObject jsonObject = gson.fromJson(contentFile, JsonObject.class);

            JsonObject locationJson = mapData.getAsJsonObject(location);

            if (locationJson.has("item")) {
                JsonElement itemElement = locationJson.get("item");

                if (itemElement.isJsonPrimitive()) {
                    List<String> items = new ArrayList<>();
                    items.add(itemElement.getAsString());
                    return items;
                } else if (itemElement.isJsonArray()) {
                    JsonArray itemArray = itemElement.getAsJsonArray();
                    List<String> items = new ArrayList<>();
                    for (JsonElement item : itemArray) {
                        items.add(item.getAsString());
                    }
                    return items;
                }
            }

            return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}