package com.anura.controller;
//  BY Lilly, stashing away for future reference
//  import org.json.JSONArray;
//  This is utlizing JSON-java lib: https://github.com/stleary/JSON-java
//  https://mvnrepository.com/artifact/org.json/json
//  gradle dependencies: implementation 'org.json:json:20230618'

//T0D0: my IntelliJ will not let me import the any of the jsons that i need (see above)..
// there is also a chunk of code below that i attempted to adapt to this file but i cannot confirm functionalitiy..


import com.anura.model.player.Player;
import com.anura.view.Helper;
import com.google.gson.JsonObject;
import org.fusesource.jansi.Ansi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Parser {

    public static String getInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a command: ");
        return scanner.nextLine().toLowerCase();
    }

    public static String[] parseInput(){
        String[] words = getInput().split(" ");
        String verb = "";
        String noun = "";
        verb = words[0];
        noun = words.length > 1 ? words[1] : "";

        if (words.length >= 2){
            noun = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
        }
        return new String[]{verb, noun};
    }

    public static void handleInput(String verb, String noun, Player player, JsonObject mapData, HashSet<String> visitedLocations){
        GameLogic gameLogic = new GameLogic();


        switch (verb) {
            case "quit":
                break;
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
//                handleMusicControls(scanner);  // TODO Move music commands out of GameLogic class and have music off/on
                break;
            case "fx":
//                handleFXControls(scanner);  // TODO move fx commands out of GameLogic class and have both commands in 1 line
            case "save":
                LogicFunction.saveGame(mapData, visitedLocations, player);
                break;
            default:
                System.out.println("Invalid command, try again.");
                verb = "";
                noun = "";
                break;
        }
    }

}