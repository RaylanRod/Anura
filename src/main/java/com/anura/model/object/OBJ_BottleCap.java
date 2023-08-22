package com.anura.model.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_BottleCap extends SuperObject {
    public OBJ_BottleCap() {

        name = "bottleCap";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/bottlecap.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
