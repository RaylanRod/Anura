package com.anura.view;

import com.anura.model.guientity.*;
import com.anura.model.monster.MON_crow;
import com.anura.model.monster.MON_snake;
import com.anura.model.monster.MON_bass;
import com.anura.model.object.OBJ_Backpack;
import com.anura.model.object.OBJ_BottleCap;
import com.anura.model.object.OBJ_GlassBead;
import com.anura.model.object.OBJ_Leaf;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void objectSettings(int index, int x, int y){
        gp.obj[index].worldX = x * gp.tileSize;
        gp.obj[index].worldY = y * gp.tileSize;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Backpack();
        objectSettings(0, 18, 32);

        gp.obj[1] = new OBJ_Leaf();
        objectSettings(1, 25, 27);
//        objectSettings(1, 18, 19);

        gp.obj[2] = new OBJ_GlassBead();
        objectSettings(2, 7, 29);

        gp.obj[3] = new OBJ_BottleCap();
        objectSettings(3, 54, 25);
    }

    public void npcSettings(int index, int x, int y){
        gp.npc[index].worldX = x * gp.tileSize;
        gp.npc[index].worldY = y * gp.tileSize;
    }
    public void monsterSetting(int index, int x, int y){
        gp.monster[index].worldX = x * gp.tileSize;
        gp.monster[index].worldY = y * gp.tileSize;
    }

    public void setNPC() {
        gp.npc[0] = new NPC_Toad(gp);
        npcSettings(0, 18, 26);
//        npcSettings(0, 21, 15);  // for testing purposes

        gp.npc[1] = new NPC_violet(gp);
        npcSettings(1, 55, 4);
//        npcSettings(1, 21, 14);  // for testing purposes

        gp.npc[2] = new NPC_Karen(gp);
        npcSettings(2, 60, 4);
//        npcSettings(2, 21, 13);  // for testing purposes

        gp.npc[3] = new NPC_Dawei(gp);
        npcSettings(3, 21, 16);

        gp.npc[4] = new NPC_Tallulah(gp);
        npcSettings(4, 41, 20);
//        npcSettings(4, 20, 17);  // for testing purposes

        gp.npc[5] = new NPC_Buckley(gp);
        npcSettings(5, 10, 26);
//        npcSettings(5, 19, 17);  // for testing purposes

        gp.npc[6] = new NPC_Triton(gp);
        npcSettings(6, 21, 42);
//        npcSettings(6, 18, 17);  // for testing purposes

        gp.npc[7] = new NPC_Sting(gp);
        npcSettings(7, 35, 38);
//        npcSettings(7, 17, 17);  // for testing purposes

        gp.npc[8] = new NPC_Destiny(gp);
        npcSettings(8, 50, 4);
//        npcSettings(8, 17, 15);  // for testing purposes

        gp.npc[9] = new NPC_Trevin(gp);
        npcSettings(9, 57, 26);
//        npcSettings(9, 17, 14);  // for testing purposes

    }
    public void setMonster() {
        gp.monster[0] = new MON_crow(gp);
        monsterSetting(0, 55, 7);

        gp.monster[1] = new MON_snake(gp);
        monsterSetting(1, 36, 27);

        gp.monster[2] = new MON_bass(gp);
        monsterSetting(2, 52, 28);
    }
}
