package com.anura.model.player;

import com.anura.view.Helper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class NPC {
    // fields and attributes
    private String name;
    private String npc;
    private String[] dialog;


    public NPC(String npc) {
        this.npc = npc;
        this.name = "Character.json";
        this.dialog = readDialogFromJsonFile();
    }


    private String[] readDialogFromJsonFile(){
        Gson gson = new Gson();
        String content = Helper.readFromResourceFile(name);
        JsonObject jsonObject = gson.fromJson(content, JsonObject.class);

        if (!jsonObject.has(npc)){
            return new String[]{"\u001B[31mThat npc doesn't exist\u001B[0m"};
        }

        JsonArray dialogArray =  jsonObject.get(npc).getAsJsonObject().getAsJsonArray("dialog");
        String[] dialogOptions = new String[dialogArray.size()];
        for(int i = 0; i < dialogArray.size(); i++){
            dialogOptions[i] = dialogArray.get(i).getAsString();
        }
        return dialogOptions;
    }

    // Getter for the npc's
    public String getNpc() {
        return npc;
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