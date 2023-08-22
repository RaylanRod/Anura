package com.anura.controller;

import com.anura.model.GameObject;
import com.anura.model.player.Player;
import com.anura.model.readjsondata.LocationData;
import com.anura.view.Helper;
import com.anura.view.Music;
import com.google.gson.*;
import org.fusesource.jansi.Ansi;

import java.util.*;

public class LogicFunction {
    public static boolean enemyShow(Player player, String userInput, Scanner scanner, JsonObject updatedLocation, JsonObject mapData, String currentLocation){
            System.out.println("Any enemy is here!  You must hide or die!");
            if (player.hasItem("leaf")) {  // Checking if the player has a leaf in the inventory
                System.out.println("You can use a leaf to hide! Enter 'hide' to hide or any other move to continue at your own risk.");

                userInput = scanner.nextLine().toLowerCase();

                if (userInput.equals("hide")) {
                    String enemyName = updatedLocation.get("enemy").getAsString();
                    boolean hidSuccessfully = player.hide(enemyName);

                    if (hidSuccessfully) {
                        System.out.println("You hide. It's safe to continue now...");
                        JsonObject locationInMapData = mapData.getAsJsonObject(currentLocation);
                        locationInMapData.remove("enemy");
                        mapData.add(currentLocation, locationInMapData);
                        return hidSuccessfully;
                    } else {
                        Helper.printColor("You couldn't hide in time...YOU DIED.", Ansi.Color.RED);
                        return false;
                    }
                } else {
                    Helper.printColor("You chose not to hide...YOU DIED.", Ansi.Color.RED);
                    return false;
                }
            } else {
                Helper.printColor("There's no place to hide without the leaf...YOU DIED.", Ansi.Color.RED);
                return false;
            }
    }

    public static void saveGame(JsonObject mapData, HashSet<String> visitedLocations, Player player){
        HashMap<String, String> savedData = new HashMap<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // pretty print JSON

        savedData.put("mapData", gson.toJson(mapData));
        savedData.put("visitedLocations", gson.toJson(visitedLocations));
        savedData.put("currentLocation", player.getCurrentLocation());
        savedData.put("playerName", player.getName());
        savedData.put("inventory", gson.toJson(player.getInventory()));

        Helper.writeToExternalFile(gson.toJson(savedData), "game-stat.sav");
    }

    public static void get(Player player, String itemName, JsonObject mapData) {
        itemName = itemName.toLowerCase();
        JsonObject locationData = mapData.get(player.getCurrentLocation()).getAsJsonObject();

        if (locationData.has("item")) {
            JsonArray itemsInLocation = locationData.getAsJsonArray("item");
            JsonArray updatedItems = new JsonArray();
            boolean itemFound = false;

            for (JsonElement itemElement : itemsInLocation) {
                String itemNameInLocation = itemElement.getAsString();

                if (!itemName.equals(itemNameInLocation)) {
                    updatedItems.add(itemElement);
                } else {
                    player.addToInventory(itemName, 1);
                    System.out.println("You picked up " + itemName + ".");
                    itemFound = true;
                }
            }
            if (itemFound) {
                locationData.add("item", updatedItems);
            } else {
                System.out.println("Cannot find " + itemName + " here.");
            }
        } else {
            System.out.println("There are no items to pick up here.");
        }
    }

    public static void drop(Player player, String itemName, JsonObject mapData) {
        itemName = itemName.toLowerCase();
        JsonObject locationData = mapData.get(player.getCurrentLocation()).getAsJsonObject();
        JsonArray newItems = locationData.getAsJsonArray("item");
        if (newItems == null) {
            newItems = new JsonArray();
            locationData.add("item", newItems); // Set the new JsonArray in the locationData
        }
        boolean itemFound = false;
        Map<String, Integer> inventory = player.getInventory();
        if (!inventory.containsKey(itemName)) {
            System.out.println("You do not have this item in inventory");
        } else {
            newItems.add(itemName);
            inventory.remove(itemName);
            itemFound = true;
        }
        if (itemFound) {
            locationData.add("item", newItems);
        }
    }

    public static void handleMusicControls(Scanner scanner) {
        System.out.println("What would you like to do with the background music? (start/stop/volume)\n ");
        String musicCommand = scanner.nextLine();

        if (musicCommand.equalsIgnoreCase("start")) {
            Music.playBGMusic("ShumbaTest.wav");
        } else if (musicCommand.equalsIgnoreCase("stop")) {
            Music.stopBackgroundMusic();
        } else if (musicCommand.equalsIgnoreCase("volume")) {
            System.out.println("Enter volume level (low = 0.0 - 1.0 = high):");
            float volume = Float.parseFloat(scanner.nextLine());
            Music.setBGMVolume(volume);
        } else {
            System.out.println("Invalid music command.");
        }
    }

    public static void handleFXControls(Scanner scanner) {
        System.out.println("What would you like to do with the sound effects? (on/off/volume)\n ");
        String musicCommand = scanner.nextLine();
        if (musicCommand.equalsIgnoreCase("on")) {
            Music.setFXVolume(0.5f); // Turn on sound effects
        } else if (musicCommand.equalsIgnoreCase("off")) {
            Music.setFXVolume(0.0f); // Turn off sound effects (via volume 0.0)
        } else if (musicCommand.equalsIgnoreCase("volume")) {
            System.out.println("Enter volume level (low = 0.0 - 1.0 = high):");
            float volume = Float.parseFloat(scanner.nextLine());
            Music.setFXVolume(volume); // Set sound effects volume
        } else {
            System.out.println("Invalid music command.");
        }
    }

    public static void printStatusBoard(Player player, JsonObject mapData) {

        // Print the status board with player information
        System.out.println("=== Status Board ===");
        System.out.println("Player: " + player.getName());
        System.out.println("Location: " + player.getCurrentLocation());

        // Print inventory
        System.out.println("Inventory:");
        String italicStyle = "\u001B[3m";
        String resetColor = "\u001B[0m";
        String magenta = "\u001B[35m";
        for (Map.Entry<String, Integer> entry : player.getInventory().entrySet()) {
            String itemName = entry.getKey();
            int itemCount = entry.getValue();
            System.out.printf(" - %-18s: %-2d%n", magenta + itemName + resetColor, itemCount);
        }

        // Print location items - more clearly without strange characters
        LocationData locationData = new LocationData(player.getCurrentLocation(), mapData);
        List<String> locationItems = locationData.getLocationItems();
        if (locationItems.isEmpty()) {
            locationItems.add("No Items");
            String item = locationItems.get(0);
            System.out.println("Location Items: " + item);
        } else {
            System.out.println("Location Items: " + locationItems);
        }

        System.out.println("====================");
    }

    public static void printDescription(Player player) {
        String currentLocation = player.getCurrentLocation();
        GameObject locationDesc = new GameObject(currentLocation);
        System.out.println("Location Description: " + locationDesc.getDescription());
    }

}
