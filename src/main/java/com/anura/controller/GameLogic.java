package com.anura.controller;

import com.anura.model.GameMap;
import com.anura.model.player.Player;
import com.anura.view.Helper;
import com.anura.view.Music;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class GameLogic {

    public void execute() throws IOException, URISyntaxException {

        Player player = new Player("frog");
        HashSet<String> visitedLocations = new HashSet<>();
        JsonObject mapData = null;

        Scanner scanner = new Scanner(System.in);

        // Welcome Banner & user instructions
        System.out.println(Ansi.ansi().eraseScreen());
        Helper.printFile("SplashPage.txt", Ansi.Color.GREEN);

        //Background music starts here
        Music.playBGMusic("ShumbaTest.wav");
        Music.setBGMVolume(0.3f);

        // ask for new game or saved game
        boolean newScreen = true;
        String userInput = null;
        while (true) {
            System.out.println("Enter " + Ansi.ansi().fgBrightGreen().a("[New]").reset() + " for new game or "
                    + Ansi.ansi().fgBlue().a("[Save]").reset() + " to load saved game:");
            userInput = scanner.nextLine().trim().toLowerCase();
            if (userInput.equals("new")) {

                // Create a new player and prompt for their name
                String playerName = Player.promptPlayerName();
                player.setName(playerName);
                // Start the game
                player.startGame();

                // get Map data
                mapData = GameMap.getMap();
                break;

            } else if (userInput.equals("save") || userInput.equals("load")) {
                // load saved json stat file
                Gson gson = new Gson();
                ClassLoader classLoader = getClass().getClassLoader();
                InputStream inputStream = classLoader.getResourceAsStream("game-stat.sav");

                if (inputStream == null) {
                    System.out.println("There is no save file, you'll need to start new game.");
                } else {
                    JsonObject loadedGameStat = gson.fromJson(Helper.getExternalFile("game-stat.sav"),
                            JsonObject.class);
                    mapData = gson.fromJson(loadedGameStat.get("mapData").getAsString(), JsonObject.class);
                    visitedLocations = gson.fromJson(loadedGameStat.get("visitedLocations").getAsString(), HashSet.class);
                    player.setCurrentLocation(loadedGameStat.get("currentLocation").getAsString());
                    player.setName(loadedGameStat.get("playerName").getAsString());

                    Type type = new TypeToken<HashMap<String, Integer>>() {
                    }.getType();
                    player.setInventory(gson.fromJson(loadedGameStat.get("inventory").getAsString(), type));
                    break;
                }
            }
        }

        label:
        while (!userInput.equals("quit")) {

            // update current location
            String currentLocation = player.getCurrentLocation();
            JsonObject updatedLocation = mapData.get(currentLocation).getAsJsonObject();
            boolean enemyPresent = updatedLocation.has("enemy");

            // add current location to visited locations list
            visitedLocations.add(currentLocation);

            // clear screen
            System.out.print(Ansi.ansi().eraseScreen());
            System.out.print(Ansi.ansi().cursor(5, 0));
            // print map of visited locations
            GameMap.printLocations(visitedLocations);
            System.out.println(Ansi.ansi().cursor(28, 0));

//            System.out.println("\nYou are at " + Ansi.ansi().fgBrightGreen().a(currentLocation).reset()
//                    + ". Here is the info of this location:");
//            Helper.printColor(updatedLocation.toString(), Ansi.Color.MAGENTA);

            System.out.println();
            LogicFunction.printStatusBoard(player, mapData);
            LogicFunction.printDescription(player);

            // move in the map
            System.out.println("\n\nPlease provide a command >");
            userInput = scanner.nextLine().toLowerCase();  // Prompt the player for their next move

            // Split the input into a command and an argument
            String[] moveInput = userInput.toLowerCase().split(" ", 2);

            if (enemyPresent) {
                boolean hidSuccessfully = LogicFunction.enemyShow(player, userInput, scanner, updatedLocation, mapData, currentLocation);

                if (!hidSuccessfully) {
                    break;
                }
            } else {
                if (moveInput.length != 2) {
                    switch (userInput) {
                        case "quit":
                        case "stop":
                            break label;
                        case "help":
                            Helper.printFile("Help.txt", Ansi.Color.GREEN);
                            break;
                        case "map":
                            Helper.printFile("VisualMap.txt", Ansi.Color.GREEN);
                            break;
                        case "inventory":
                            player.displayInventory();
                            break;
                        case "music":
                            LogicFunction.handleMusicControls(scanner);
                            break;
                        case "fx":
                            LogicFunction.handleFXControls(scanner);
                            break;
                        case "save":
                            LogicFunction.saveGame(mapData, visitedLocations, player);
                            break;
                        default:
                            Helper.printColor("\nInvalid input! Please enter one action and one direction(i.e. go south).\n",
                                    Ansi.Color.RED);
                            break;
                    }
                    System.out.println("\u001B[32mEnter to continue..\u001B[0m");
                    scanner.nextLine();
                } else if (userInput.toLowerCase().startsWith("look")) {
                    String[] inputParts = userInput.split(" ");
                    String itemType = inputParts[1];
                    player.look(itemType);
                    System.out.println("\u001B[32mEnter to continue..\u001B[0m");
                    scanner.nextLine();
                } else if (userInput.startsWith("get") || userInput.startsWith("pickup") || userInput.startsWith("take")) {
                    String[] inputParts = userInput.split(" ", 2);
                    if (inputParts.length == 2) {
                        String itemName = inputParts[1];
                        LogicFunction.get(player, itemName, mapData);
                    } else {
                        System.out.println("Please provided an item name after 'get'.");
                    }
                } else if (userInput.startsWith("drop") || userInput.startsWith("leave")) {
                    String[] inputParts = userInput.split(" ", 2);
                    String itemName = inputParts[1];
                    LogicFunction.drop(player, itemName, mapData);
                } else if (userInput.toLowerCase().startsWith("talk") || userInput.toLowerCase().startsWith("speak")) {
                    String[] inputParts = userInput.split(" ");
                    String npcName = inputParts[1];
                    player.talk(npcName);
                    if ("violet".equalsIgnoreCase(npcName)) {
                        if (player.hasItem("glass bead")) {
                            Helper.printColor("What a beautiful bead!  Is that a gift for me?", Ansi.Color.MAGENTA);
                            System.out.println();
                            Helper.printColor("You give violet the bead, and the two of you run off to start your life together...", Ansi.Color.GREEN);
                            System.out.println();
                            Helper.printColor("CONGRATULATIONS!  YOU WON!", Ansi.Color.GREEN);
                            break;
                        } else {
                            Helper.printColor("Violet seems to want a gift from you to prove that you love her...", Ansi.Color.YELLOW);
                        }
                    }
                    System.out.println("\u001B[32mEnter to continue...\u001B[0m");
                    scanner.nextLine();
                } else {
                    player.move(moveInput[1].toLowerCase(), mapData);

                    // Sound effect (FX) music starts here
                    Music.playFX("Good.wav"); // Play the sound effect
                }
            }
        }
        Helper.printColor("\nBye!", Ansi.Color.MAGENTA);
    }
}