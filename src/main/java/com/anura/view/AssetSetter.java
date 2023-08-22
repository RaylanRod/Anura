package com.anura.view;

import com.anura.model.guientity.NPC_Karen;
import com.anura.model.guientity.NPC_Toad;
import com.anura.model.guientity.NPC_violet;
import com.anura.model.object.OBJ_Backpack;
import com.anura.model.object.OBJ_BottleCap;
import com.anura.model.object.OBJ_GlassBead;
import com.anura.model.object.OBJ_Leaf;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

//    public void createObject(int index, int x, int y, Class class){
//
//    }

    public void setObject() {
        gp.obj[0] = new OBJ_Backpack();
        gp.obj[0].worldX = 18 * gp.tileSize;
        gp.obj[0].worldY = 20 * gp.tileSize;

        gp.obj[1] = new OBJ_Leaf();
        gp.obj[1].worldX = 40 * gp.tileSize;
        gp.obj[1].worldY = 29 * gp.tileSize;

        gp.obj[2] = new OBJ_GlassBead();
        gp.obj[2].worldX = 7 * gp.tileSize;
        gp.obj[2].worldY = 29 * gp.tileSize;

        gp.obj[3] = new OBJ_BottleCap();
        gp.obj[3].worldX = 54 * gp.tileSize;
        gp.obj[3].worldY = 23 * gp.tileSize;

    }

    public void setNPC() {
        gp.npc[0] = new NPC_Toad(gp);
        gp.npc[0].worldX = 50 * gp.tileSize;
        gp.npc[0].worldY = 4 * gp.tileSize;

        gp.npc[1] = new NPC_violet(gp);
        gp.npc[1].worldX = 55 * gp.tileSize;
        gp.npc[1].worldY = 4 * gp.tileSize;

        gp.npc[2] = new NPC_Karen(gp);
        gp.npc[2].worldX = 60 * gp.tileSize;
        gp.npc[2].worldY = 4 * gp.tileSize;
    }
}
