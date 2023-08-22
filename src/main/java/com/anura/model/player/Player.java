package com.anura.model.player;

import com.anura.model.GameObject;
import com.anura.model.readjsondata.EvolutionData;
import com.anura.view.Helper;
import com.google.gson.JsonObject;
import org.fusesource.jansi.Ansi;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Player extends Character {
    // Additional attributes specific to the player
    private String playerName;
    private int exp;
    public Map<String, Integer> playerInventory;
    private EvolutionData currentEvol;
    private int currentHealth;
    private String currentLocation;

    // constructors
    public Player(String name) {
        this.playerName = name;
        this.currentEvol = new EvolutionData("tadpole");
        if (currentEvol.getEvolName().equals("tadpole")) {
            setMaxHealth(20);
        }
        this.currentHealth = getMaxHealth();
        this.exp = 0;
        this.playerInventory = new HashMap<>();
        this.currentLocation = "pond";
        //initializePlayer();
    }

    public void addToInventory(String item, int count) {
        playerInventory.put(item, playerInventory.getOrDefault(item, 0) + count);
    }

    public void useItem(String item, int count) {
        if (playerInventory.containsKey(item)) {
            int currentCount = playerInventory.get(item);
            if (currentCount >= count) {
                playerInventory.put(item, currentCount - count);
            } else {
                System.out.println("Not enough " + item + " in inventory!");
            }
        } else {
            System.out.println("Item " + item + " not found in inventory!");
        }
    }

    // Method to prompt the player to enter their name
    public static String promptPlayerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        return playerName.trim().isEmpty() ? "Frog" : playerName;
    }

    public void talk(String npcName) {
        NPC character = new NPC(npcName);
        String[] dialogOptions = character.getDialog();

        if (dialogOptions.length > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(dialogOptions.length);
            String selectedDialog = dialogOptions[randomIndex];

            System.out.println(selectedDialog);
        } else {
            System.out.println("The character can't talk!");
        }
    }

    public void look(String itemType) {
        Scanner scanner = new Scanner(System.in);
        if (itemType.equals("item") || itemType.equals("location") || itemType.equals("food")) {
            System.out.print("Enter the " + itemType + " you want to look at: ");
            String itemName = scanner.nextLine().trim().toLowerCase();

            GameObject gameObject = new GameObject(itemType, itemName);
            String description = gameObject.getDescription();

            System.out.println("Description: " + description);
        }else{
            System.out.println("\u001B[31mTo correctly use look, please type look item, look location or look food first\u001B[0m");
        }
    }

    public boolean hasItem(String itemName) {
        return playerInventory.containsKey(itemName);
    }

    public boolean hide(String enemyName) {
        if (playerInventory.containsKey("leaf")) {
            return true;
        } else {
            System.out.println("You need the leaf to hide!");
            return false;
        }
    }

    // Display's player's inventory
    public void displayInventory() {
        System.out.println("\n\n========================");
        System.out.println("|       INVENTORY       |");
        System.out.println("-------------------------");

        for (Map.Entry<String, Integer> entry : playerInventory.entrySet()) {
            String itemName = entry.getKey();
            int itemCount = entry.getValue();
            System.out.printf("| %-18s %-3d|%n", itemName, itemCount);
        }

        System.out.println("_________________________");
    }

    // Method to start the game
    public void startGame() {
        // Update the player's name based on the input
        setName(playerName);

        // Now you can use the player object with the entered name throughout the game
        System.out.println("Welcome, " + getName() + "!");
        System.out.println("Your Starting Level is: " + currentEvol.getEvolName());
        // Other game logic

    }

    public String move(String direction, JsonObject mapData) {

        JsonObject currentLocationJson = mapData.get(currentLocation).getAsJsonObject();

        if (currentLocationJson.has(direction)) {
            this.currentLocation = currentLocationJson.get(direction).getAsString();
            return currentLocation;
        } else {
            Helper.printColor("\nInvalid action, please try another one.\n" +
                    "Enter to continue..\n", Ansi.Color.RED);
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            return null;
        }
    }

    // Getter and setter methods

    @Override
    public void setName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String getName() {
        return this.playerName;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Map<String, Integer> getInventory() {
        return playerInventory;
    }

    public void setInventory(Map<String, Integer> inventory) {
        this.playerInventory = inventory;
    }
}





