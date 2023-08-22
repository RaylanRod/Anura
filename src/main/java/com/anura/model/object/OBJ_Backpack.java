package com.anura.model.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Backpack extends SuperObject{
    public OBJ_Backpack() {
        collision = true;
        name = "backpack";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/backpack.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
