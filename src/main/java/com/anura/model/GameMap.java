package com.anura.model;

import com.anura.view.Helper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.fusesource.jansi.Ansi;

import java.util.HashSet;
import java.util.Map;

public class GameMap {

    private static final String[] VISUAL_MAP =
            Helper.readFromResourceFile("VisualMap.txt").split("\n");
    private static final Map<String, int[]> VISUAL_MAP_COORDS = Map.of(
            "pond",         new int[]{19, 1, 39, 10},
            "swamp",        new int[]{41, 2, 49, 6},
            "wetland",      new int[]{8, 12, 23, 16},
            "trail",        new int[]{23, 11, 56, 16},
            "empty plot",   new int[]{20, 17, 32, 22},
            "log",          new int[]{56, 12, 67, 16},
            "grasslands",   new int[]{52, 17, 65, 22},
            "stream",       new int[]{67, 8, 82, 16},
            "lake",         new int[]{61, 4, 82, 7},
            "dock",         new int[]{61, 0, 76, 3});

    public static JsonObject getMap(){
        Gson gson = new Gson();

        String fileReader = Helper.readFromResourceFile("location.json");
        return gson.fromJson(fileReader, JsonObject.class);
    }

    public static void printLocations(HashSet<String> visitedLocations) {
        for(String location : visitedLocations) {

            int[] coords = VISUAL_MAP_COORDS.get(location);
            for(int i = coords[1]; i <= coords[3]; i++){
                Helper.printColor(VISUAL_MAP[i].substring(coords[0], coords[2]),
                        Ansi.Color.GREEN, coords[0], i + 5);
            }
        }
    }
}