package com.anura.model.player;

import com.anura.view.Helper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Enemy {
    // fields and attributes
    private String name;
    private String enemy;
    private String[] dialog;


    public Enemy(String enemy) {
        this.enemy = enemy;
        this.name = "Enemy.json";
        this.dialog = readDialogFromJsonFile();
    }
    public String readEnemyDescriptionFromJson(){
        Gson gson = new Gson();
        String content = Helper.readFromResourceFile(name);
        JsonObject jsonObject = gson.fromJson(content, JsonObject.class);
        return jsonObject.get(enemy).getAsJsonObject().get("desc").getAsString();

    }

    private String[] readDialogFromJsonFile(){
        Gson gson = new Gson();
//            InputStream inputStream = new FileInputStream(name);
        String content = Helper.readFromResourceFile(name);
        JsonObject jsonObject = gson.fromJson(content, JsonObject.class);

        JsonArray dialogArray =  jsonObject.get(enemy).getAsJsonObject().getAsJsonArray("dialog");
        String[] dialogChoices = new String[dialogArray.size()];
        for(int i = 0; i < dialogArray.size(); i++){
            dialogChoices[i] = dialogArray.get(i).getAsString();
        }

        return dialogChoices;

    }


    // Getter for the npc's
    public String getEnemy() {
        return enemy;
    }

    public String[] getDialog() {
        return dialog;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}